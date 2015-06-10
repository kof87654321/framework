package com.zl.common.util.enums;

public enum ResultCode {

    SUCCESS(1, "成功");

    /**
     * 编码
     */
    private int    code;

    /**
     * 描述
     */
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
