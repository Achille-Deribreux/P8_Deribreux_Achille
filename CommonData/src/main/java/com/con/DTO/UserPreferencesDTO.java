package com.con.DTO;


import java.util.Objects;


public class UserPreferencesDTO {
    private int attractionProximity = Integer.MAX_VALUE;
    private String currency = "USD";
    private int lowerPricePoint = 1;
    private int highPricePoint = 2;
    private int tripDuration = 1;
    private int ticketQuantity = 1;
    private int numberOfAdults = 1;
    private int numberOfChildren = 0;

    public UserPreferencesDTO() {
    }

    public UserPreferencesDTO(int attractionProximity, String currency, int lowerPricePoint, int highPricePoint, int tripDuration, int ticketQuantity, int numberOfAdults, int numberOfChildren) {
        this.attractionProximity = attractionProximity;
        this.currency = currency;
        this.lowerPricePoint = lowerPricePoint;
        this.highPricePoint = highPricePoint;
        this.tripDuration = tripDuration;
        this.ticketQuantity = ticketQuantity;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
    }

    public int getAttractionProximity() {
        return attractionProximity;
    }

    public void setAttractionProximity(int attractionProximity) {
        this.attractionProximity = attractionProximity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getLowerPricePoint() {
        return lowerPricePoint;
    }

    public void setLowerPricePoint(int lowerPricePoint) {
        this.lowerPricePoint = lowerPricePoint;
    }

    public int getHighPricePoint() {
        return highPricePoint;
    }

    public void setHighPricePoint(int highPricePoint) {
        this.highPricePoint = highPricePoint;
    }

    public int getTripDuration() {
        return tripDuration;
    }

    public void setTripDuration(int tripDuration) {
        this.tripDuration = tripDuration;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPreferencesDTO that = (UserPreferencesDTO) o;
        return attractionProximity == that.attractionProximity && lowerPricePoint == that.lowerPricePoint && highPricePoint == that.highPricePoint && tripDuration == that.tripDuration && ticketQuantity == that.ticketQuantity && numberOfAdults == that.numberOfAdults && numberOfChildren == that.numberOfChildren && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attractionProximity, currency, lowerPricePoint, highPricePoint, tripDuration, ticketQuantity, numberOfAdults, numberOfChildren);
    }
}
