package com.rentools.checkout;

import com.rentools.agreement.RentalAgreement;
import com.rentools.model.Tool;

import java.util.Calendar;
import java.util.Date;

/**
 * @author rameshthumati
 */
public class Checkout {
    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, Date checkoutDate) {
        Tool tool = getTool(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid tool code: " + toolCode);
        }

        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }

        // Calculate charge days based on tool type and checkout date
        int chargeDays = calculateChargeDays(checkoutDate, rentalDays, tool);

        // Create rental agreement
        RentalAgreement agreement = new RentalAgreement(toolCode, tool.getType(), tool.getBrand(),
                rentalDays, checkoutDate, tool.getDailyCharge(), chargeDays, discountPercent);

        return agreement;
    }

    private Tool getTool(String toolCode) {
        // Retrieve tool information based on the tool code
        switch (toolCode) {
            case "LADW":
                return new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false);
            case "CHNS":
                return new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true);
            case "JAKD":
                return new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false);
            case "JAKR":
                return new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false);
            default:
                return null;
        }
    }

    private int calculateChargeDays(Date checkoutDate, int rentalDays, Tool tool) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);
        int chargeDays = 0;

        for (int i = 0; i < rentalDays; i++) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            boolean isWeekend = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
            boolean isHoliday = isIndependenceDay(calendar) || isLaborDay(calendar);

            if ((dayOfWeek == Calendar.SATURDAY && tool.isWeekendCharge()) ||
                    (dayOfWeek == Calendar.SUNDAY && tool.isWeekendCharge()) ||
                    (!isWeekend && tool.isWeekdayCharge()) ||
                    (isHoliday && tool.isHolidayCharge())) {
                chargeDays++;
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1); // Move to the next day
        }

        return chargeDays;
    }

    private boolean isIndependenceDay(Calendar calendar) {
        return calendar.get(Calendar.MONTH) == Calendar.JULY &&
                calendar.get(Calendar.DAY_OF_MONTH) == 4;
    }

    private boolean isLaborDay(Calendar calendar) {
        return calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER &&
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY &&
                calendar.get(Calendar.DAY_OF_MONTH) <= 7; // First Monday in September
    }
}
