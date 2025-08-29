package com.helpdesk.supportapi.dto.users.response;

import java.time.LocalDateTime;

/**
 * Classe para formatar e deixar o erro mais legivel e amigável
 * Class to format and make the error more readable and user-friendly
 */
public class ResponseError {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ResponseError(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
