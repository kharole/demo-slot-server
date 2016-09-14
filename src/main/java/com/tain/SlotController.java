package com.tain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.math.BigDecimal.valueOf;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("slot")
@CrossOrigin(origins = "*")
public class SlotController {

    private static String[] allSymbols = new String[]{"A", "B", "C"};

    private Random random = new Random();
    private Config config = new Config();

    private int gameRoundId = 0;
    private BigDecimal balance = new BigDecimal("1000.00");
    private String[][] symbols = randomSymbols();

    private State state = new State(balance, null, null, null, symbols, emptyList(), null);

    @RequestMapping(value = "/spin")
    @ResponseBody
    public State spin(
            @RequestParam("lineBet") Integer lineBet,
            @RequestParam("linesCount") Integer linesCount
    ) {
        int totalBet = lineBet * linesCount;
        BigDecimal newBalance = balance.subtract(config.getCoinValue().multiply(valueOf(totalBet)));
        if (newBalance.signum() < 0) {
            return new State(balance, null, null, null, symbols, emptyList(), "NOT_ENOUGH_CREDITS");
        }
        balance = newBalance;
        symbols = randomSymbols();

        List<LineResult> lineResults = IntStream.range(0, linesCount)
                .mapToObj(i -> config.getLines().get(i))
                .map(l -> calculateWin(l, lineBet, symbols))
                .collect(toList());
        int totalWin = lineResults.stream().mapToInt(LineResult::getWin).sum();

        balance = balance.add(config.getCoinValue().multiply(valueOf(totalWin)));
        state = new State(balance, gameRoundId++, totalBet, totalWin, symbols, lineResults, null);
        return state;
    }

    @RequestMapping(value = "/state")
    @ResponseBody
    public State getState() {
        return state;
    }

    @RequestMapping(value = "/config")
    @ResponseBody
    public Config getConfig() {
        return config;
    }

    @RequestMapping(value = "/reset")
    @ResponseBody
    public State getReset() {
        state = new State(balance, null, null, null, symbols, emptyList(), null);
        return state;
    }

    private LineResult calculateWin(Line line, Integer bet, String[][] symbols) {
        if (line.getCells().stream().map(c -> symbols[c.getRow()][c.getReel()]).distinct().count() == 1)
            return new LineResult(line.getId(), bet, bet * 3);
        else
            return new LineResult(line.getId(), bet, 0);
    }

    private String[][] randomSymbols() {
        String[][] result = new String[config.getRows()][config.getReels()];
        for (int i = 0; i < config.getRows(); i++) {
            for (int j = 0; j < config.getReels(); j++) {
                result[i][j] = allSymbols[random.nextInt(allSymbols.length)];
            }
        }
        return result;
    }


}
