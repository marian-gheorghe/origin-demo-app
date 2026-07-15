package io.origindemo.dose;

/**
 * The request body for a dose computation.
 */
public record DoseRequest(Double patientWeightKg, Double mgPerKg) {
}
