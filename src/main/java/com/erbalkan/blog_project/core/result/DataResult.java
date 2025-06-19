package com.erbalkan.blog_project.core.result;

public class DataResult<T> extends Result{

    private final T data;
    public T getData() {
        return data;
    }

    public DataResult(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }

    public DataResult(T data, boolean success) {
        super(success);
        this.data = data;
    }
}
