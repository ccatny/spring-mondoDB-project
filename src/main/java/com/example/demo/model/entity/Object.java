package com.example.demo.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("object")
public class Object implements Serializable {
    @Id
    private int id;

    private String name;

    private List<ObjectItem> objectItems;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ObjectItem> getObjectItems() {
        return objectItems;
    }

    public void setObjectItems(List<ObjectItem> objectItems) {
        this.objectItems = objectItems;
    }
}
