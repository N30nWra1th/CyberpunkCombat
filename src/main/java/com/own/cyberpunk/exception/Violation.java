package com.own.cyberpunk.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Violation {

    private String field;
    private String errorMessage;
}
