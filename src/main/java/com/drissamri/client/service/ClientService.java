package com.drissamri.client.service;

import com.drissamri.client.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

public class ClientService {
    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);
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
            long startTime = System.currentTimeMillis();
            this.dynamoDbClient.putItem(request);
            long endTime = System.currentTimeMillis();
            LOG.info("DynamoDB: " + (endTime - startTime) + " milliseconds");
        } catch (Exception e) {
            LOG.warn(e.getMessage());
            throw new RuntimeException("Database update failed!");
        }
    }
}
