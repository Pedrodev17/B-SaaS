package org.example.bsaas.exception;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters e setters
    public Date getTimestamp() { return timestamp; }
    public String getMessage() { return message; }
    public String getDetails() { return details; }

    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public void setMessage(String message) { this.message = message; }
    public void setDetails(String details) { this.details = details; }
}