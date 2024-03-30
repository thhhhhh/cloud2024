package com.ceesing.cloud.exp;

import com.ceesing.cloud.resp.ResultData;
import com.ceesing.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // 枚举 INTERNAL_SERVER_ERROR(500, HttpStatus.Series.SERVER_ERROR, "Internal Server Error"),
    public ResultData<String> exceptionHandler(Exception e) {
        log.error("全局异常信息：{}", e.getMessage());
        e.printStackTrace();
        // 注意：状态码使用自定义的枚举类设置
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }
}