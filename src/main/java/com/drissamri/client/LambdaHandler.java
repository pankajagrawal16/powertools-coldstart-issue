package com.drissamri.client;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.drissamri.client.core.AppConfig;
import com.drissamri.client.core.http.HttpResponses;
import com.drissamri.client.model.Client;
import com.drissamri.client.service.ClientService;
import com.fasterxml.jackson.jr.ob.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(LambdaHandler.class);
    private final ClientService clientService;

    public LambdaHandler() {
        this(AppConfig.clientService());
    }

    public LambdaHandler(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        LOG.info("Input: {}", event);

        try {
            Client client = JSON.std.beanFrom(Client.class, event.getBody());
            LOG.info("Client: {}", client);
            clientService.create(client);
            return HttpResponses.ok(JSON.std.asString(client));
        } catch (IOException e) {
            return HttpResponses.unprocessableEntity("Invalid input");
        }
    }
}
