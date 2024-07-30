package com.own.cyberpunk.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ValidationError {
    private List<CustomFieldError> fieldErrors = new ArrayList<>();

    public void addFieldError(String field, String message) {
        CustomFieldError error = new CustomFieldError(field, message);
        fieldErrors.add(error);
    }

    public List<CustomFieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<CustomFieldError> customFieldErrors) {
        this.fieldErrors = customFieldErrors;
    }

    private static class CustomFieldError {

        private String field;
        private String message;

        public CustomFieldError(String field, String message) {
        }
    }
}

