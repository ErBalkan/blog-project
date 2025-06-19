package com.erbalkan.blog_project.core.result.success;

import com.erbalkan.blog_project.core.result.Result;

public class SuccessResult extends Result {
    public SuccessResult(String message) {
        super(true, message);
    }

    public SuccessResult() {
        super(true);
    }
}

