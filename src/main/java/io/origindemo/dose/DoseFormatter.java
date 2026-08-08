package io.origindemo.dose;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Formats computed doses for display.
 */
public final class DoseFormatter {

    private DoseFormatter() {
    }

    /**
     * Formats a dose in mg for display, rounded to one decimal place.
     */
    public static String formatDisplayDose(double doseMg) {
        BigDecimal rounded = BigDecimal.valueOf(doseMg).setScale(1, RoundingMode.HALF_UP);
        return rounded.toPlainString();
    }
}
