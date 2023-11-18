package com.example.demo.controller;


import com.example.demo.model.entity.Object;
import com.example.demo.model.response.Response;
import com.example.demo.service.IObjectService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

@RestController
@RequestMapping("/object")
public class ObjectController {

    @Resource
    private IObjectService objectService;

    @PostMapping("")
    public Response update(@RequestBody Object object) {
        return Response.restResponse(objectService.saveObject(object));
    }

    @GetMapping("/{objectId}")
    public Response getObjectById(@PathVariable("objectId") Integer id) {
        Object object = objectService.getObjectById(id);
        if (object == null) {
            return Response.restResponse("Object does not exist");
        } else {
            return Response.restResponse(object);
        }
    }

    @PutMapping("")
    public Response updateObject(@RequestBody Object object) {
        return Response.restResponse(objectService.updateObject(object));
    }
}
