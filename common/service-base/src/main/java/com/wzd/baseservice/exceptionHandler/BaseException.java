package com.wzd.baseservice.exceptionHandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 *
 * @ClassName BaseException
 * @Author lofxve
 * @Date 2021/1/30 17:33
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;

    @Override
    public String toString() {
        return "BaseException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }
}