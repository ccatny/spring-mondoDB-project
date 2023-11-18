package com.example.demo.service;

import com.example.demo.model.entity.Object;

public interface IObjectService {

    String createObject(Object object);

    Object getObjectById(int id);

    Boolean updateObject(Object object);

}
