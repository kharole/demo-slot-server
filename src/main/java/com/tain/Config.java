package com.tain;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

public class Config {

    private BigDecimal coinValue;
    private Integer minCoins;
    private Integer maxCoins;
    private Integer reels;
    private Integer rows;
    private List<Line> lines;

    public Config() {
        this.coinValue = new BigDecimal("0.10");
        this.minCoins = 3;
        this.maxCoins = 10;
        this.reels = 3;
        this.rows = 3;

        Line line1 = new Line(1, asList(new Cell(1, 0), new Cell(1, 1), new Cell(1, 2)));
        Line line2 = new Line(2, asList(new Cell(0, 0), new Cell(1, 1), new Cell(2, 2)));
        Line line3 = new Line(3, asList(new Cell(2, 0), new Cell(1, 1), new Cell(0, 2)));

        this.lines = asList(line1, line2, line3);
    }

    public BigDecimal getCoinValue() {
        return coinValue;
    }

    public Integer getMinCoins() {
        return minCoins;
    }

    public Integer getMaxCoins() {
        return maxCoins;
    }

    public Integer getReels() {
        return reels;
    }

    public Integer getRows() {
        return rows;
    }

    public List<Line> getLines() {
        return lines;
    }
}
