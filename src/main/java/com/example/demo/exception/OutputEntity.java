/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.exception;

import static com.example.demo.util.Response.INTERNALERROR;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author marco-romero
 */
@Data
public class OutputEntity<T> {

    @JsonIgnore
    private HttpStatus code;
    private ArrayList<String> messages = new ArrayList<>();
    private Integer error = 0;
    private T data;

    public OutputEntity<T> succes(Integer code, String message, T data) {
        this.code = HttpStatus.valueOf(code); // Convierte el int a HttpStatus
        this.messages.add(message);
        this.data = data;
        return this;
    }

    public OutputEntity<T> failed(Integer code, String message, T data) {
        this.error = 1;
        this.code = HttpStatus.valueOf(code);
        this.messages.add(message);
        this.data = data;
        return this;
    }

    public OutputEntity<T> failed(Integer code, ArrayList<String> message, T data) {
        this.error = 1;
        this.code = HttpStatus.valueOf(code);
        this.messages = (message);
        this.data = data;
        return this;
    }

    public OutputEntity<T> error() {
        this.error = 1;
        this.code = HttpStatus.valueOf(500);
        this.messages.add(INTERNALERROR.getKey());
        this.data = null;
        return this;
    }

}
