package com.rentools.agreement;

import java.util.Calendar;
import java.util.Date;

public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private Date checkoutDate;
    private Date dueDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private double preDiscountCharge;
    private int discountPercent;
    private double discountAmount;
    private double finalCharge;

    // Constructor
    public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays,
                           Date checkoutDate, double dailyRentalCharge, int chargeDays, int discountPercent) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.discountPercent = discountPercent;

        // Calculate due date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);
        calendar.add(Calendar.DAY_OF_YEAR, rentalDays);
        this.dueDate = calendar.getTime();

        // Calculate pre-discount charge
        this.preDiscountCharge = chargeDays * dailyRentalCharge;

        // Calculate discount amount
        this.discountAmount = preDiscountCharge * (discountPercent / 100.0);

        // Calculate final charge
        this.finalCharge = preDiscountCharge - discountAmount;
    }

    // Method to print rental agreement details
    public void printRentalAgreement() {
        System.out.println("******************************");
        System.out.println("******** Rental Agreement *****");
        System.out.println("******************************");
        System.out.printf("%-20s: %s%n", "Tool code", toolCode);
        System.out.printf("%-20s: %s%n", "Tool type", toolType);
        System.out.printf("%-20s: %s%n", "Tool brand", toolBrand);
        System.out.printf("%-20s: %d%n", "Rental days", rentalDays);
        System.out.printf("%-20s: %tD%n", "Checkout date", checkoutDate);
        System.out.printf("%-20s: %tD%n", "Due date", dueDate);
        System.out.printf("%-20s: $%.2f%n", "Daily rental charge", dailyRentalCharge);
        System.out.printf("%-20s: %d%n", "Charge days", chargeDays);
        System.out.printf("%-20s: $%.2f%n", "Pre-discount charge", preDiscountCharge);
        System.out.printf("%-20s: %d%%%n", "Discount percent", discountPercent);
        System.out.printf("%-20s: $%.2f%n", "Discount amount", discountAmount);
        System.out.printf("%-20s: $%.2f%n", "Final charge", finalCharge);
        System.out.println("******************************");
    }


}