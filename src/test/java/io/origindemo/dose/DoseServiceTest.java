package io.origindemo.dose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DoseServiceTest {

    private final DoseService service = new DoseService();

    /**
     * Requirement: "The system shall reject a null request body before
     * dereferencing it."
     */
    @Test
    void rejectsNullRequestBodyBeforeDereferencingIt() {
        ValidationException ex = assertThrows(ValidationException.class,
                () -> service.computeDoseMg(null));
        assertEquals("request body must not be null", ex.getMessage());
    }

    @Test
    void computesTheDoseForAValidRequest() {
        assertEquals(150.0, service.computeDoseMg(new DoseRequest(75.0, 2.0)), 1e-9);
    }

    @Test
    void rejectsAMissingPatientWeight() {
        assertThrows(ValidationException.class,
                () -> service.computeDoseMg(new DoseRequest(null, 2.0)));
    }

    @Test
    void rejectsANonPositivePatientWeight() {
        assertThrows(ValidationException.class,
                () -> service.computeDoseMg(new DoseRequest(0.0, 2.0)));
    }

    /**
     * Requirement: REQ-ORIGIN-CC-ROUNDTRIP-CLEAN-3 (v2) - "The dose service
     * shall reject a negative dose value by throwing a ValidationException
     * before formatting."
     *
     * <p>The input-range checks above already prevent a negative patient
     * weight or mg-per-kg from reaching this point, so the negative-dose
     * guard is exercised directly here as an independent, defense-in-depth
     * safety check on the computed dose value.
     */
    @Test
    void rejectsANegativeDoseValue() {
        ValidationException ex = assertThrows(ValidationException.class,
                () -> DoseService.validateDoseRange(-0.1));
        assertEquals("dose must not be negative", ex.getMessage());
    }

    /**
     * Requirement: REQ-ORIGIN-CC-ROUNDTRIP-CLEAN-4 (v2) - "The dose service
     * shall reject a dose value greater than 1000 by throwing a
     * ValidationException before formatting."
     */
    @Test
    void rejectsADoseValueGreaterThan1000() {
        ValidationException ex = assertThrows(ValidationException.class,
                () -> service.computeDoseMg(new DoseRequest(500.0, 3.0)));
        assertEquals("dose must not exceed 1000", ex.getMessage());
    }

    @Test
    void allowsADoseValueOfExactly1000() {
        assertEquals(1000.0, service.computeDoseMg(new DoseRequest(500.0, 2.0)), 1e-9);
    }
}
