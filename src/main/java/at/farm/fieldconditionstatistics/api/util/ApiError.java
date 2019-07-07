package at.farm.fieldconditionstatistics.api.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

class ApiError {

    private int httpStatus;
    private String message;
    private ZonedDateTime timestamp = ZonedDateTime.now(ZoneOffset.UTC);

    public ApiError() {
    }

    ApiError(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
