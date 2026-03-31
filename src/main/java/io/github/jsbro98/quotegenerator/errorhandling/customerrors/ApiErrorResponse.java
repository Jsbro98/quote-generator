package io.github.jsbro98.quotegenerator.errorhandling.customerrors;

public record ApiErrorResponse(
        int status,
        String message,
        long timestamp
) {
}
