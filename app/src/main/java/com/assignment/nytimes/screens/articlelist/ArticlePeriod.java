package com.assignment.nytimes.screens.articlelist;

public enum ArticlePeriod {
    TODAY(1),
    WEEKLY(7),
    MONTHLY(30);

    private int period;

    ArticlePeriod(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }
}
