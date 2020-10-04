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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
