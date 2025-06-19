package com.erbalkan.blog_project.core.result.error;

import com.erbalkan.blog_project.core.result.DataResult;

public class ErrorDataResult<T> extends DataResult<T> {

    public ErrorDataResult(T data, String message) {
        super(false, message, data);
    }

    public ErrorDataResult(String message) {
        super(false, message, null);
    }

    public ErrorDataResult(T data) {
        super(data,false);
    }
}
