package com.restfullBooker.payloads;

import org.codehaus.jackson.annotate.JsonProperty;

public class BookingDates {

    @JsonProperty
    private String checkin;

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @JsonProperty
    private String checkout;

    public String getCheckin() {
        return checkin.toString();
    }

    public String getCheckout() {
        return checkout.toString();
    }
}
