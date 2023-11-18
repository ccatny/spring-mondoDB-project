package com.example.demo.controller;


import com.example.demo.model.entity.Object;
import com.example.demo.model.response.Response;
import com.example.demo.service.IObjectService;
import com.example.demo.service.impl.Status;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/object")
public class ObjectController {

    @Resource
    private IObjectService objectService;

    @PostMapping("")
    public Response createNewObject(@RequestBody Object object) {
        return Response.success(null, objectService.createObject(object));
    }

    @GetMapping("/{objectId}")
    public Response getObjectById(@PathVariable("objectId") Integer id) {
        Object object = objectService.getObjectById(id);
        if (object != null) {
            return Response.success(object, Status.OBJECT_FOUND);
        } else {
            return Response.fail(Status.OBJECT_NOT_FOUND);
        }
    }

    @PutMapping("")
    public Response updateObject(@RequestBody Object object) {
        boolean result = objectService.updateObject(object);
        if (result) {
            return Response.success(null, Status.UPDATE_SUCCESSFULLY);
        } else {
            return Response.fail(Status.OBJECT_NOT_FOUND);
        }
    }
}
