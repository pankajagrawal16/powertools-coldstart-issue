package com.drissamri.client;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.drissamri.client.http.HttpResponses;
import com.drissamri.client.model.Client;
import com.fasterxml.jackson.jr.ob.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(LambdaHandler.class);

    public LambdaHandler() {

    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        LOG.info("Input: {}", event);

        try {
            Client client = JSON.std.beanFrom(Client.class, event.getBody());
            LOG.info("Client: {}", client);
            return HttpResponses.ok();
        } catch (IOException e) {
            return HttpResponses.unprocessableEntity("Invalid input");
        }
    }
}
