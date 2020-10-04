package com.drissamri.client.core.http;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.TreeMap;

public class HttpResponses {
    public static APIGatewayProxyResponseEvent withStatusCode(HttpStatusCode status) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(status.code);
        response.setHeaders(new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
        return response;
    }

    public static APIGatewayProxyResponseEvent withStatusCodeAndBody(HttpStatusCode code, String body) {
        APIGatewayProxyResponseEvent response = withStatusCode(code);
        response.setBody(body);
        return response;
    }

    public static APIGatewayProxyResponseEvent ok() {
        return withStatusCode(HttpStatusCode.OK_200);
    }

    public static APIGatewayProxyResponseEvent notFound() {
        return withStatusCode(HttpStatusCode.NOT_FOUND_404);
    }

    public static APIGatewayProxyResponseEvent internalServerError() {
        return withStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR_500);
    }

    public static APIGatewayProxyResponseEvent badRequest() {
        return withStatusCode(HttpStatusCode.BAD_REQUEST_400);
    }

    public static APIGatewayProxyResponseEvent unprocessableEntity(String body) {
        APIGatewayProxyResponseEvent response = withStatusCode(HttpStatusCode.UNPROCESSABLE_ENTITY_422);
        response.setBody(body);
        return response;
    }

    public static APIGatewayProxyResponseEvent ok(String jsonBody) {
        APIGatewayProxyResponseEvent response = ok();
        response.setBody(jsonBody);
        response.getHeaders().put("content-type", "application/json");
        return response;
    }
}

