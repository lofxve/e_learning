package com.wzd.baseservice.exceptionHandler;

import com.wzd.commonutils.R;
import com.wzd.exceptionutils.Exceptionutils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @ClassName GlobalExceptionHandler
 * @Author lofxve
 * @Date 2021/1/30 17:08
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        log.error(Exceptionutils.getMessage(e));
        e.printStackTrace();
        return R.error().message(Exceptionutils.getMessage(e));
    }

    /**
     * @return com.wzd.commonutils.R
     * @Author lofxve
     * @Description // 自定义异常处理方法
     * @Date 17:36 2021/1/30
     * @Param [e]
     **/
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public R error(BaseException e) {
        log.error(Exceptionutils.getMessage(e));
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
