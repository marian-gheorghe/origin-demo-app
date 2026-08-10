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
        return weightKg * mgPerKg;
    }
}
