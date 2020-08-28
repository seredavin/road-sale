package ru.avokzal63.roadsale.domain;

public enum TickerType {
    PASSENGER("Пассажирский"),
    BAGGAGE("Багажный");

    private String title;

    TickerType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

