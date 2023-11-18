package com.example.demo.exception;

import com.example.demo.model.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpRequest;

@RestControllerAdvice
public class ServerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ServerExceptionHandler.class);

   @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                        HttpServletRequest request) {
       log.error("'{} doesn't not support request {} ", request.getRequestURI(), e.getMethod());
       return Response.fail(e.getMessage());
   }

    @ExceptionHandler(RuntimeException.class)
    public Response handleRuntimeException(RuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("request at {} has unknown error {}", requestURI, e);
        return Response.fail(e.getMessage());
    }

}
