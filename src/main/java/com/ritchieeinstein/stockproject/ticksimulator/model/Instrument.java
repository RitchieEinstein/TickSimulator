package com.ritchieeinstein.stockproject.ticksimulator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static java.lang.Math.round;

public class Instrument {

    private long instrumentToken;
    private String symbol;
    private Trend trend;
    private double basePrice;

    private static final Random random =  new Random();


    public Instrument(long instrumentToken, String symbol){
        this.instrumentToken = instrumentToken;
        this.symbol = symbol;
        trend = Trend.UP;
        basePrice = round( 1 + (10000 - 10) * random.nextDouble(),2);
    }

    public long getInstrumentToken() {
        return instrumentToken;
    }

    public void setInstrumentToken(long instrumentToken) {
        this.instrumentToken = instrumentToken;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Trend getTrend() {
        return trend;
    }

    public void setTrend(Trend trend) {
        this.trend = trend;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
