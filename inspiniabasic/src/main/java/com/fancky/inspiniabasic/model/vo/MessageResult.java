package com.fancky.inspiniabasic.model.vo;

public class MessageResult<T> {

    /**
     * 执行结果（true:成功，false:失败）
     */
    private Boolean success;
    private String message;
    private T data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}