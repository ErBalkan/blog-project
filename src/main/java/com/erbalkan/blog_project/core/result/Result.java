package com.erbalkan.blog_project.core.result;

import lombok.Getter;

@Getter
public class Result {
    private final boolean success;
    private final String message;

    public Result(boolean success) {
        this.success = success;
        this.message = null;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

