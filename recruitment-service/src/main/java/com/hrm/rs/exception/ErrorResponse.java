package com.hrm.rs.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}