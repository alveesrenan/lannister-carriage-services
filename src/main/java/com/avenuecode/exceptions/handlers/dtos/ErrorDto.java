package com.avenuecode.exceptions.handlers.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDto {
    private String message;
}
