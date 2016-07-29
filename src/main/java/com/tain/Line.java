package com.tain;


import java.util.List;

public class Line {

    private Integer id;
    private List<Cell> cells;

    public Line(Integer id, List<Cell> cells) {
        this.id = id;
        this.cells = cells;
    }

    public Integer getId() {
        return id;
    }

    public List<Cell> getCells() {
        return cells;
    }
}
