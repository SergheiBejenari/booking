package com.restfullBooker.payloads;

import org.codehaus.jackson.annotate.JsonProperty;

public class BookingResponse {

    @JsonProperty("bookingid")
    private int bookingid;

    @JsonProperty
    private Booking booking;

    public int getBookingid() {
        return bookingid;
    }

    public Booking getBooking() {
        return booking;
    }
}