package com.wzd.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.commonutils.JwtUtils;
import com.wzd.commonutils.MD5;
import com.wzd.ucenterservice.entity.UcenterMember;
import com.wzd.ucenterservice.mapper.UcenterMemberMapper;
import com.wzd.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author wzd
 * @since 2021-03-01
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public String login(UcenterMember member) {
        // 为空校验
        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new BaseException(20001, "登录失败，用户名或者密码为空");
        }

        // 是否存在
        QueryWrapper<UcenterMember> mobilQuueryWrapper = new QueryWrapper<>();
        mobilQuueryWrapper.eq("mobile", mobile);
        UcenterMember mobileMember = baseMapper.selectOne(mobilQuueryWrapper);
        if (mobileMember == null) {
            throw new BaseException(20001, "手机号为空");
        }
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new BaseException(20001, "密码错误");
        }
        if (mobileMember.getIsDeleted()) {
            throw new BaseException(20001, "用户被禁用");
        }

        // 生成token
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());

        return jwtToken;
    }
}
