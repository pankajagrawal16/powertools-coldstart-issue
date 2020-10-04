package com.drissamri.client;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.drissamri.client.model.Client;
import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class LambdaHandlerTest {
    LambdaHandler handler = new LambdaHandler();


    @Test
    public void handlerTest() throws IOException {
        Client client = new Client("playsports", "This is PLAY SPORTS");
        APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent()
                .withBody(JSON.std.asString(client));

        APIGatewayProxyResponseEvent response = handler.handleRequest(input, null);

        assertThat(response.getStatusCode()).isEqualTo(200);
    }

}