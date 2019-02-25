package cn.iocoder.common.framework.vo;

public class RestResult {

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public static RestResult error(Integer code, String message) {
        RestResult result = new RestResult();
        result.code = code;
        result.message = message;
        return result;
    }

    public static RestResult ok(Object data) {
        RestResult result = new RestResult();
        result.code = 0;
        result.data = data;
        result.message = "";
        return result;
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