package com.restfullBooker;


import com.restfullBooker.api.BookingApi;
import com.restfullBooker.payloads.Booking;
import com.restfullBooker.payloads.BookingDates;
import com.restfullBooker.payloads.BookingResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ApiTests {

    BookingResponse bookingPostResponseObj;
    Response postResponseJson;
    Booking bookingObj;
    String fullName;

    @Test
    public void negative_createNewBookingFails() throws IOException {
        BookingDates dates = new BookingDates();
        dates.setCheckin("2013-02-23");
        dates.setCheckout("2014-02-23");

        Booking booking = new Booking();
        booking.setFirstname("Stivin");
        booking.setLastname("Sigal");
        booking.setTotalprice(200);
        booking.setDepositpaid(true);
        booking.setBookingdates(dates);
        booking.setAdditionalneeds("No");

        Response response = BookingApi.createNewBookingNegative(1, booking);
        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void testCreateBookingReturns200() {
        //Create bookingObj
        BookingDates dates = new BookingDates();
        dates.setCheckin("2013-02-23");
        dates.setCheckout("2014-02-23");

        bookingObj = new Booking();
        bookingObj.setFirstname("Stivin");
        bookingObj.setLastname("Sigal");
        bookingObj.setTotalprice(200);
        bookingObj.setDepositpaid(true);
        bookingObj.setBookingdates(dates);
        bookingObj.setAdditionalneeds("No");

        postResponseJson = BookingApi.createNewBooking(bookingObj);

//        parse postResponseJson to object bookingPostResponseObj
        bookingPostResponseObj = postResponseJson.as(BookingResponse.class);

//        Validate that the postResponseJson contains the same values in the tags that were provided as part of the POST
        assertEquals(bookingObj.getFirstname(), bookingPostResponseObj.getBooking().getFirstname());
        assertEquals(bookingObj.getLastname(), bookingPostResponseObj.getBooking().getLastname());
        assertEquals(bookingObj.getTotalprice(), bookingPostResponseObj.getBooking().getTotalprice());
        assertEquals(bookingObj.isDepositpaid(), bookingPostResponseObj.getBooking().isDepositpaid());
        assertEquals(bookingObj.getBookingdates().getCheckin(), bookingPostResponseObj.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingObj.getBookingdates().getCheckout(), bookingPostResponseObj.getBooking().getBookingdates().getCheckout());
        assertEquals(bookingObj.getAdditionalneeds(), bookingPostResponseObj.getBooking().getAdditionalneeds());

//        Validate that the postResponseJson status code is as expected
        assertEquals(200, postResponseJson.getStatusCode());

//       5. Save the "firstname" and "lastname" values to a new variable called "fullName"
        fullName = bookingObj.getFirstname() + " " + bookingObj.getLastname();
    }

    @Test(dependsOnMethods = "testCreateBookingReturns200")
    public void getBookingById() {
        Response bookingGetResponseJson = BookingApi.getBookingById(bookingPostResponseObj.getBookingid(), "application/json");

//        Parse GetResponseJson to object bookingGetResponseObj
        BookingResponse bookingGetResponseObj = postResponseJson.as(BookingResponse.class);
//        Validate that the postResponseJson contains the same values in the tags that were provided as part of the POST
        assertEquals(bookingObj.getFirstname(), bookingGetResponseObj.getBooking().getFirstname());
        assertEquals(bookingObj.getLastname(), bookingGetResponseObj.getBooking().getLastname());
        assertEquals(bookingObj.getTotalprice(), bookingGetResponseObj.getBooking().getTotalprice());
        assertEquals(bookingObj.isDepositpaid(), bookingGetResponseObj.getBooking().isDepositpaid());
        assertEquals(bookingObj.getBookingdates().getCheckin(), bookingGetResponseObj.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingObj.getBookingdates().getCheckout(), bookingGetResponseObj.getBooking().getBookingdates().getCheckout());
        assertEquals(bookingObj.getAdditionalneeds(), bookingGetResponseObj.getBooking().getAdditionalneeds());

//        Validate that the postResponseJson status code is as expected
        assertEquals(bookingGetResponseJson.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "testCreateBookingReturns200")
    public void isPresentFullName() {
        //BookingPage bookingPage = new BookingPage(Driver.getDriver());
        // bookingPage.setupWebDriver();
        //  bookingPage.isPresentFullName();
    }

}