package io.origindemo.dose;

/**
 * Raised when a request fails validation; callers translate this into a
 * client error rather than a server fault.
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
