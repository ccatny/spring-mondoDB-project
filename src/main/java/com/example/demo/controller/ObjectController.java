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
    public Response createNewObject(@RequestBody Object object) {
        return Response.success(null, objectService.saveObject(object));
    }

    @GetMapping("/{objectId}")
    public Response getObjectById(@PathVariable("objectId") Integer id) {
        Object object = objectService.getObjectById(id);
        if (object != null) {
            return Response.success(object, "Object found");
        } else {
            return Response.fail("Object does not exist");
        }
    }

    @PutMapping("")
    public Response updateObject(@RequestBody Object object) {
        boolean result = objectService.updateObject(object);
        if (result) {
            return Response.success(null, "Object update successfully");
        } else {
            return Response.fail("Object not found");
        }
    }
}
