package com.wzd.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.commonutils.JwtUtils;
import com.wzd.commonutils.R;
import com.wzd.commonutils.vo.commentvo.UcenterMemberVo;
import com.wzd.eduservice.client.UcenterClient;
import com.wzd.eduservice.entity.EduComment;
import com.wzd.eduservice.service.EduCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CommentFrontController
 * @Author lofxve
 * @Date 2021/3/4 19:33
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
@Api("前端评论页面")
public class CommentFrontController {
    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UcenterClient ucenterClientl;

    @ApiOperation("保存评论")
    @PostMapping("auth/save")
    public R save(@RequestBody EduComment comment, HttpServletRequest request) {
        // 是否有获取会员id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }
        comment.setMemberId(memberId);

        // 远程调用
        UcenterMemberVo ucenterInfo = ucenterClientl.getInfoUc(memberId);

        comment.setNickname(ucenterInfo.getNickname());
        comment.setAvatar(ucenterInfo.getAvatar());

        commentService.save(comment);
        return R.ok();
    }
    @ApiOperation(value = "分页查询所有评论")
    @GetMapping("getCommentList/{page}/{limit}")
    public R getCommentList(@PathVariable long page,@PathVariable long limit,String courseId) {
        Page<EduComment> pageParam = new Page<>(page,limit);

        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);

        commentService.page(pageParam,wrapper);
        List<EduComment> commentList = pageParam.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());

        return R.ok().data(map);
    }

}
