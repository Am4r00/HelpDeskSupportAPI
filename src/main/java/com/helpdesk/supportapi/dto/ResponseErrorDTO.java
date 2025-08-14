package com.helpdesk.supportapi.dto;

import java.time.LocalDateTime;

/**
 * Classe para formatar e deixar o erro mais legivel e amigável
 * Class to format and make the error more readable and user-friendly
 */
public class ResponseErrorDTO {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ResponseErrorDTO(int status, String message, LocalDateTime timestamp) {
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
