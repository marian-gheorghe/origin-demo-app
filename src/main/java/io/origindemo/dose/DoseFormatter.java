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
        double scaled = Math.floor(doseMg * 10.0) / 10.0;
        return String.format(Locale.ROOT, "%.1f", scaled);
    }
}
