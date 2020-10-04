package com.drissamri.client.service;

import com.drissamri.client.LambdaHandler;
import com.drissamri.client.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;

import java.util.HashMap;
import java.util.Map;

public class ClientService {
    private static final Logger LOG = LogManager.getLogger(LambdaHandler.class);
    private final DynamoDbAsyncClient dynamoDbClient;

    public ClientService(DynamoDbAsyncClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void create(Client client) {
        final Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("pk", AttributeValue.builder().s(client.getName()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(System.getenv("CLIENT_TABLE"))
                .item(itemValues)
                .build();

        try {
            PutItemResponse result = this.dynamoDbClient.putItem(request).get();
            LOG.info("Put: {}", result);
        } catch (Exception e) {
            LOG.warn(e.getMessage());
            throw new RuntimeException("Database update failed!");
        }
    }
}
