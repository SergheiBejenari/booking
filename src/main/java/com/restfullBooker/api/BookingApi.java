package com.restfullBooker.api;

import com.restfullBooker.payloads.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingApi {
    protected static final String BASE_URL = "https://restful-booker.herokuapp.com/booking/";

    public static Response getBookingById(int id, String mediaType){
        return given()
                .header("Accept", mediaType)
                .get(BASE_URL + Integer.toString(id));
    }

    public static Response createNewBookingNegative(int id, Booking payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(BASE_URL + Integer.toString(id));
    }

    public static Response createNewBooking(Booking payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(BASE_URL);
    }
}