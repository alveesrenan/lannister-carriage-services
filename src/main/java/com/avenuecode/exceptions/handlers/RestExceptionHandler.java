package com.avenuecode.exceptions.handlers;

import com.avenuecode.exceptions.GraphCreationException;
import com.avenuecode.exceptions.GraphNotFoundException;
import com.avenuecode.exceptions.NoSuchRouteException;
import com.avenuecode.exceptions.handlers.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto exceptionHandle(Exception exception) {
        return buildErrorObject(exception);
    }

    @ExceptionHandler(GraphCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto graphCreateExceptionHandle(GraphCreationException exception) {
        return buildErrorObject(exception);
    }

    @ExceptionHandler(GraphNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDto graphNotFoundExceptionHandle(GraphNotFoundException exception) {
        return buildErrorObject(exception);
    }

    @ExceptionHandler(NoSuchRouteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto noSuchRouteExceptionHanle(NoSuchRouteException exception) {
        return buildErrorObject(exception);
    }

    private ErrorDto buildErrorObject(Exception exception) {
        return ErrorDto.builder()
                .message(exception.getMessage())
                .build();
    }
}
