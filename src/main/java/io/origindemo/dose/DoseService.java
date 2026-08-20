package io.origindemo.dose;

/**
 * Computes doses from incoming request bodies.
 */
public class DoseService {

    /**
     * Computes the dose in mg for the given request body.
     *
     * <p>Invalid input must surface as a {@link ValidationException}, never
     * as a server fault such as a {@link NullPointerException}.
     */
    public double computeDoseMg(DoseRequest requestBody) {
        if (requestBody == null) {
            throw new ValidationException("request body must not be null");
        }
        if (requestBody.patientWeightKg() == null) {
            throw new ValidationException("patientWeightKg must not be null");
        }
        if (requestBody.mgPerKg() == null) {
            throw new ValidationException("mgPerKg must not be null");
        }
        double weightKg = requestBody.patientWeightKg();
        double mgPerKg = requestBody.mgPerKg();
        if (weightKg <= 0) {
            throw new ValidationException("patientWeightKg must be positive");
        }
        if (mgPerKg <= 0) {
            throw new ValidationException("mgPerKg must be positive");
        }
        double doseMg = weightKg * mgPerKg;
        validateDoseRange(doseMg);
        return doseMg;
    }

    /**
     * Validates that a computed dose is within the safe, displayable range
     * before it is handed to {@link DoseFormatter} for formatting.
     *
     * <p>This is a defense-in-depth check on the output of the dose
     * computation, independent of the input-range checks above, so that a
     * negative or excessively large dose can never reach formatting or
     * display.
     */
    public static void validateDoseRange(double doseMg) {
        if (doseMg < 0) {
            throw new ValidationException("dose must not be negative");
        }
        if (doseMg > 1000) {
            throw new ValidationException("dose must not exceed 1000");
        }
    }
}
