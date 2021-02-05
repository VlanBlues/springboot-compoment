package com.example.component.util;

/**
 * Result
 * @author dolyw.com
 * @date 2018/8/30 11:39
 */
public class Result {
    /**
     * HTTP状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success(String msg, Object data) {
        return new Result(200,msg,data);
    }

    public static Result success(String msg) {
        return new Result(200,msg);
    }

    public static Result success(Object data) {
        return new Result(200,null,data);
    }

    public static Result fail(Integer code, String msg, Object data) {
        return new Result(code,msg,data);
    }

    public static Result fail(Integer code, Object data) {
        return new Result(code,null,data);
    }

    public static Result fail(Integer code, String msg) {
        return new Result(code,msg); 
    }
    
    public static Result fail(String msg) {
        return new Result(400,msg);
    }
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
