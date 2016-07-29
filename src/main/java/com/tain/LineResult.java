package com.tain;

public class LineResult {

    private Integer lineId;
    private Integer bet;
    private Integer win;

    public LineResult(Integer lineId, Integer bet, Integer win) {
        this.lineId = lineId;
        this.bet = bet;
        this.win = win;
    }

    public Integer getLineId() {
        return lineId;
    }

    public Integer getBet() {
        return bet;
    }

    public Integer getWin() {
        return win;
    }
}
