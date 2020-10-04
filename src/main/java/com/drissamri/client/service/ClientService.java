package com.drissamri.client.service;

import com.drissamri.client.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ClientService {
    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);
    private final DynamoDbAsyncClient dynamoDbClient;

    public ClientService(DynamoDbAsyncClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void create(Client client) {
        final Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("pk", AttributeValue.builder().s(client.getName()).build());
        itemValues.put("uuid", AttributeValue.builder().s(UUID.randomUUID().toString()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(System.getenv("CLIENT_TABLE"))
                .item(itemValues)
                .build();

        try {
            this.dynamoDbClient.putItem(request).get();
        } catch (Exception e) {
            LOG.warn(e.getMessage());
            throw new RuntimeException("Database update failed!");
        }
    }
}
