package com.drissamri.client.core.http;

public enum HttpStatusCode {
    OK_200(200),
    ACCEPTED_202(202),
    FORBIDDEN_403(403),
    NOT_FOUND_404(404),
    BAD_REQUEST_400(400),
    UNPROCESSABLE_ENTITY_422(422),
    INTERNAL_SERVER_ERROR_500(500),
    SERVICE_UNAVAILABLE_503(503);

    public final int code;

    HttpStatusCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("HttpStatusCode{name=%s,code=%d}", name(), code);
    }
}