package com.restfullBooker;

import com.restfullBooker.api.BookingApi;
import com.restfullBooker.driver.Driver;
import com.restfullBooker.payloads.Booking;
import com.restfullBooker.payloads.BookingDates;
import com.restfullBooker.payloads.BookingResponse;
import com.restfullBooker.utils.Wait;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        bookingPostResponseObj = postResponseJson.as(BookingResponse.class);

        assertEquals(bookingObj.getFirstname(), bookingPostResponseObj.getBooking().getFirstname());
        assertEquals(bookingObj.getLastname(), bookingPostResponseObj.getBooking().getLastname());
        assertEquals(bookingObj.getTotalprice(), bookingPostResponseObj.getBooking().getTotalprice());
        assertEquals(bookingObj.isDepositpaid(), bookingPostResponseObj.getBooking().isDepositpaid());
        assertEquals(bookingObj.getBookingdates().getCheckin(), bookingPostResponseObj.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingObj.getBookingdates().getCheckout(), bookingPostResponseObj.getBooking().getBookingdates().getCheckout());
        assertEquals(bookingObj.getAdditionalneeds(), bookingPostResponseObj.getBooking().getAdditionalneeds());
        assertEquals(200, postResponseJson.getStatusCode());

        fullName = bookingObj.getFirstname() + " " + bookingObj.getLastname();
    }

    @Test(dependsOnMethods = "testCreateBookingReturns200")
    public void getBookingById() {
        Response bookingGetResponseJson = BookingApi.getBookingById(bookingPostResponseObj.getBookingid(), "application/json");
        BookingResponse bookingGetResponseObj = postResponseJson.as(BookingResponse.class);
        assertEquals(bookingObj.getFirstname(), bookingGetResponseObj.getBooking().getFirstname());
        assertEquals(bookingObj.getLastname(), bookingGetResponseObj.getBooking().getLastname());
        assertEquals(bookingObj.getTotalprice(), bookingGetResponseObj.getBooking().getTotalprice());
        assertEquals(bookingObj.isDepositpaid(), bookingGetResponseObj.getBooking().isDepositpaid());
        assertEquals(bookingObj.getBookingdates().getCheckin(), bookingGetResponseObj.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingObj.getBookingdates().getCheckout(), bookingGetResponseObj.getBooking().getBookingdates().getCheckout());
        assertEquals(bookingObj.getAdditionalneeds(), bookingGetResponseObj.getBooking().getAdditionalneeds());
        assertEquals(bookingGetResponseJson.getStatusCode(), 200);
    }


    @Test(dependsOnMethods = "testCreateBookingReturns200")
    public void isPresentFullName() {
        WebDriver driver = new Driver().getDriver();
        Wait wait = new Wait(driver);
        wait.sendKeys(driver.findElement(By.xpath("//input[@class='form-control search']")), fullName);

        String bodyText = driver.findElement(By.tagName("body")).getText();
        if (bodyText.contains("restful-booker")) {
            System.out.println("Text Found");
        } else
            System.out.println("Text Not Found");

        assertTrue("Text Not Found", bodyText.contains("restful-booker"));
    }
}