package com.tain;

import java.math.BigDecimal;
import java.util.List;

public class State {

    private BigDecimal balance;
    private Integer gameRoundId;
    private Integer totalBet;
    private Integer totalWin;

    private String[][] symbols;
    private List<LineResult> lineResults;

    private String errorCode;

    public State(BigDecimal balance, Integer gameRoundId, Integer totalBet, Integer totalWin, String[][] symbols, List<LineResult> lineResults, String errorCode) {
        this.balance = balance;
        this.gameRoundId = gameRoundId;
        this.totalBet = totalBet;
        this.totalWin = totalWin;
        this.symbols = symbols;
        this.lineResults = lineResults;
        this.errorCode = errorCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Integer getGameRoundId() {
        return gameRoundId;
    }

    public Integer getTotalBet() {
        return totalBet;
    }

    public Integer getTotalWin() {
        return totalWin;
    }

    public String[][] getSymbols() {
        return symbols;
    }

    public List<LineResult> getLineResults() {
        return lineResults;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
