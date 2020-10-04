package com.drissamri.client.model;

public class Client {
    String name;
    String description;

    public Client(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Client() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
