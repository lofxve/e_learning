package com.wzd.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzd.baseservice.exceptionHandler.BaseException;
import com.wzd.commonutils.ConstantUtil;
import com.wzd.commonutils.JwtUtils;
import com.wzd.commonutils.MD5;
import com.wzd.ucenterservice.entity.UcenterMember;
import com.wzd.ucenterservice.entity.vo.RegisterVo;
import com.wzd.ucenterservice.mapper.UcenterMemberMapper;
import com.wzd.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        //校验参数
        if (StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new BaseException(20001, "error");
        }

        // 验证码判断
        String rediscode = redisTemplate.opsForValue().get(ConstantUtil.SMS_CODE + mobile);
        if (StringUtils.isEmpty(rediscode) || !rediscode.equals(code)) {
            throw new BaseException(20001, "验证码错误");
        }

        // 查询数据库中是否存在相同的手机号码
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BaseException(20001, "该号码已经被注册");
        }

        //添加注册信息到数据库
        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("https://e-learning-lofxve.oss-cn-beijing.aliyuncs.com/2021/02/20/f6571a35edf54068907b23f70f0c4b69file.png");
        this.save(member);
    }

    @Override
    public UcenterMember getByOpenid(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);

        UcenterMember member = baseMapper.selectOne(queryWrapper);
        return member;
    }

    @Override
    public Integer countRegisterDay(String day) {
        Integer integer = baseMapper.countRegisterDay(day);
        return integer;
    }
}
