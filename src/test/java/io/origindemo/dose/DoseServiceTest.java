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
}
