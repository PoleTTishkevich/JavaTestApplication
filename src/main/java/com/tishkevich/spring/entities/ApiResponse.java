package com.tishkevich.spring.entities;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
public class ApiResponse <T> implements Serializable {

    private int status;
    private String message;
    private T[] result;

    public ApiResponse(){

    }

    public ApiResponse(int status, String message, T[] result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
