package com.sheep.sheepoj.utils;

public class JsonResult<T>{
    Integer code;
    String msg;
    T data;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(Integer code, String msg) {
        this(code,msg,null);
    }

    public JsonResult(T data)
    {
        this(JsonResponseStatus.SUCCESS.getCode(), "请求成功",data);
    }

}
