package com.wzd.eduservice.client;

import com.wzd.commonutils.vo.commentvo.UcenterMemberVo;
import com.wzd.eduservice.client.impl.UcenterFileDegradeFeginClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName UcenterClient
 * @Author lofxve
 * @Date 2021/3/4 19:29
 * @Version 1.0
 */
@FeignClient(name = "service-ucenter",fallback = UcenterFileDegradeFeginClient.class)
@Component
public interface UcenterClient {
    @PostMapping("/educenter/member/getInfoUc/{id}")
    public UcenterMemberVo getInfoUc(@PathVariable("id") String id);
}

