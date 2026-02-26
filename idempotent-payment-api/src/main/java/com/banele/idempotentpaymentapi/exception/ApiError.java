package com.banele.idempotentpaymentapi.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    // New field for structured validation errors

    private Map<String, String> validationErrors;

    public ApiError(LocalDateTime timestamp,
                    int status,
                    String error,
                    String message,
                    String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // Overloaded constructor for validation errors
    public ApiError(LocalDateTime timestamp,
                    int status,
                    String error,
                    String message,
                    String path,
                    Map<String, String> validationErrors) {
        this(timestamp, status, error, message, path);
        this.validationErrors = validationErrors;
    }

    // Getters

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    public Map<String, String> getValidationErrors() { return validationErrors; }
}
