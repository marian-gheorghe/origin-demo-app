package io.origindemo.dose;

import java.util.Locale;

/**
 * Formats computed doses for display.
 */
public final class DoseFormatter {

    private DoseFormatter() {
    }

    /**
     * Formats a dose in mg for display, rounded to one decimal place.
     *
     * <p>Rounding uses the standard half-up algorithm (Java's {@code
     * Formatter} default for {@code %f}), so e.g. 12.36 displays as 12.4
     * rather than being truncated to 12.3.
     */
    public static String formatDisplayDose(double doseMg) {
        return String.format(Locale.ROOT, "%.1f", doseMg);
    }
}
