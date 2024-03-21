package com.rentools.test;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author rameshthumati - test cases writted that requires unit testing jars
 */
public class TestToolRental {

    @Test
    public void testRentalAgreement() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date checkoutDate = dateFormat.parse("09/03/2015");
        RentalAgreement agreement = new RentalAgreement("JAKR", "Jackhammer", "Ridgid",
                5, checkoutDate, 2.99, 5, 0);

        assertEquals("JAKR", agreement.getToolCode());
        assertEquals("Jackhammer", agreement.getToolType());
        assertEquals("Ridgid", agreement.getToolBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals(checkoutDate, agreement.getCheckoutDate());
        assertEquals(5, agreement.getChargeDays());
        assertEquals(14.95, agreement.getPreDiscountCharge());
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0, agreement.getDiscountAmount());
        assertEquals(14.95, agreement.getFinalCharge());

        // Test with discount
        RentalAgreement agreementWithDiscount = new RentalAgreement("LADW", "Ladder", "Werner",
                6, checkoutDate, 1.99, 6, 20);
        assertEquals(20, agreementWithDiscount.getDiscountPercent());
        assertEquals(11.96, agreementWithDiscount.getFinalCharge());
    }

    @Test
    public void testCheckout() throws ParseException {
        Checkout checkout = new Checkout();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        RentalAgreement agreement1 = checkout.checkout("JAKR", 5, 0, dateFormat.parse("09/03/2015"));
        RentalAgreement agreement2 = checkout.checkout("LADW", 6, 10, dateFormat.parse("07/20/2015"));

        assertEquals("JAKR", agreement1.getToolCode());
        assertEquals("LADW", agreement2.getToolCode());
    }

    @Test
    public void testTools() {
        Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false);

        assertEquals("JAKR", tool.getToolCode());
        assertEquals("Jackhammer", tool.getType());
        assertEquals("Ridgid", tool.getBrand());
        assertEquals(2.99, tool.getDailyCharge());
        assertTrue(tool.isWeekdayCharge());
        assertFalse(tool.isWeekendCharge());
        assertFalse(tool.isHolidayCharge());
    }

    @Test
    public void testHoliday() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        assertTrue(Holiday.isHoliday(dateFormat.parse("07/04/2024"))); // Independence Day
        assertTrue(Holiday.isHoliday(dateFormat.parse("09/07/2024"))); // Labor Day
        assertFalse(Holiday.isHoliday(dateFormat.parse("06/01/2024"))); // Non-holiday date
    }
}

