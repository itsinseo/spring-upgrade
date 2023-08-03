package com.sparta.springreview.exception;

import java.io.Serial;

public class PatternMismatchException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5769927660170952889L;

    public PatternMismatchException(String message) {
        super(message);
    }
}
