package io.origindemo.dose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoseFormatterTest {

    /**
     * Requirement: "The displayed dose shall be rounded to one decimal
     * place."
     */
    @Test
    void displaysTheDoseRoundedToOneDecimalPlace() {
        assertEquals("12.4", DoseFormatter.formatDisplayDose(12.36));
    }

    @Test
    void keepsValuesAlreadyAtOneDecimalPlace() {
        assertEquals("12.3", DoseFormatter.formatDisplayDose(12.3));
    }
}
