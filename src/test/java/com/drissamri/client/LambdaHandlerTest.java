package com.drissamri.client;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.drissamri.client.model.Client;
import com.drissamri.client.service.ClientService;
import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class LambdaHandlerTest {


    @Test
    public void handlerTest() throws IOException {
        ClientService clientServiceMock = Mockito.mock(ClientService.class);
        LambdaHandler handler = new LambdaHandler(clientServiceMock);

        Client client = new Client("playsports", "This is PLAY SPORTS");
        APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent()
                .withBody(JSON.std.asString(client));

        APIGatewayProxyResponseEvent response = handler.handleRequest(input, null);

        assertThat(response.getStatusCode()).isEqualTo(200);
    }

}