package com.wzd.orderservice.client;

import com.wzd.commonutils.vo.order.CourseWebOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName EduClient
 * @Author lofxve
 * @Date 2021/3/6 14:49
 * @Version 1.0
 */
@FeignClient("service-edu")
@Component
public interface EduClient {
    @ApiOperation(value = "根据id获取课程详情")
    @PostMapping("/eduservice/coursefront/getCourseInfoById/{courseId}")
    public CourseWebOrder getCourseInfoById(@PathVariable("courseId") String courseId);
}
