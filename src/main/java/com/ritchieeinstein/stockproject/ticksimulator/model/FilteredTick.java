package com.ritchieeinstein.stockproject.ticksimulator.model;

import java.util.Date;
import java.util.UUID;

public class FilteredTick {

    private UUID id;
    private long instrumentToken;
    private double lastTradedPrice;
    private Date tickTimestamp;
    private Date lastTradedTimestamp;
    private double lastTradedQuantity;

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public long getInstrumentToken() {
        return instrumentToken;
    }

    public void setInstrumentToken(long instrumentToken) {
        this.instrumentToken = instrumentToken;
    }

    public double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public void setLastTradedPrice(double lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }

    public Date getTickTimestamp() {
        return tickTimestamp;
    }

    public void setTickTimestamp(Date tickTimestamp) {
        this.tickTimestamp = tickTimestamp;
    }

    public Date getLastTradedTimestamp() {
        return lastTradedTimestamp;
    }

    public void setLastTradedTimestamp(Date lastTradedTimestamp) {
        this.lastTradedTimestamp = lastTradedTimestamp;
    }

    public double getLastTradedQuantity() {
        return lastTradedQuantity;
    }

    public void setLastTradedQuantity(double lastTradedQuantity) {
        this.lastTradedQuantity = lastTradedQuantity;
    }

    @Override
    public String toString() {
        return "FilteredTick{" +
                "id=" + id +
                ", instrumentToken=" + instrumentToken +
                ", lastTradedPrice=" + lastTradedPrice +
                ", tickTimestamp=" + tickTimestamp +
                ", lastTradedTimestamp=" + lastTradedTimestamp +
                ", lastTradedQuantity=" + lastTradedQuantity +
                '}';
    }
}
