package com.diit.common.pojo;

/**
 * 自定义响应结构
 */
public class DiitResult {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public static DiitResult build(Integer code, String message, Object data) {
        return new DiitResult(code, message, data);
    }

    public static DiitResult ok(Object data) {
        return new DiitResult(data);
    }

    public static DiitResult ok() {
        return new DiitResult(null);
    }

    public DiitResult() {

    }

    public static DiitResult build(Integer code, String message) {
        return new DiitResult(code, message, null);
    }

    public DiitResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public DiitResult(Object data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }

    public Boolean isSuccess() {
        return this.code == 200;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
