package com.erbalkan.blog_project.core.result.error;

import com.erbalkan.blog_project.core.result.Result;

public class ErrorResult extends Result {
    public ErrorResult(String message) {
        super(false, message);
    }

    public ErrorResult() {
        super(false);
    }
}

