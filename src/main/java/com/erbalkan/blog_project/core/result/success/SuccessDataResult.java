package com.erbalkan.blog_project.core.result.success;

import com.erbalkan.blog_project.core.result.DataResult;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(T data, String message) {
        super(true, message, data);
    }

    public SuccessDataResult(T data) {
        super(data, true);
    }
}
