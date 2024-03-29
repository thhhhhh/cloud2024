package com.ceesing.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
//@Accessors(chain = true) 主要作用是改变Lombok为类生成getter和setter方法的行为，使其支持链式调用（Chainable setters）
// 添加之后setter方法会返回当前对象的引用（即this），而非默认的void，User user = new User().setName("Alice").setAge(25);
@Accessors(chain = true)
public class ResultData<T> {
    // 状态码
    private String code;
    // 状态信息
    private String message;
    // 数据
    private T data;
    // 时间戳
    private Long timestamp;

    public ResultData() {
        // 每次获取时，只需要初始化调用时间即可，其他的都需要根据执行结果来设置
        this.timestamp = System.currentTimeMillis();
    }

    // 成功
    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }
    // 失败
    // 失败的时候不会返回数据，所以直接设置数据为null，并返回失败状态码和状态信息
    public static <T> ResultData<T> fail(String code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }
}