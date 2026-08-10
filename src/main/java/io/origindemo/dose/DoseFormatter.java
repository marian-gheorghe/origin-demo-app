package io.origindemo.dose;

import java.util.Locale;

/**
 * Formats computed doses for display.
 */
public final class DoseFormatter {

    private DoseFormatter() {
    }

    /**
     * Formats a dose in mg for display with one decimal place.
     */
    public static String formatDisplayDose(double doseMg) {
        java.math.BigDecimal rounded = java.math.BigDecimal.valueOf(doseMg)
                .setScale(1, java.math.RoundingMode.HALF_UP);
        return String.format(Locale.ROOT, "%.1f", rounded.doubleValue());
    }
}
