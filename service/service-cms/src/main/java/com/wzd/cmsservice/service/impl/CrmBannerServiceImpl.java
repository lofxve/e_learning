package com.wzd.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzd.cmsservice.entity.CrmBanner;
import com.wzd.cmsservice.mapper.CrmBannerMapper;
import com.wzd.cmsservice.service.CrmBannerService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author wzd
 * @since 2021-02-28
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CrmBanner> getAllBanner() {
        // 获取banner
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        // 根据id倒叙排列
        queryWrapper.orderByDesc("id");
        // last拼接，获取前两条记录
        queryWrapper.last("LIMIT 2");

        return baseMapper.selectList(queryWrapper);
    }

    @CacheEvict(value = "banner", allEntries = true)
    @Override
    public boolean save(CrmBanner banner) {
        int insert = baseMapper.insert(banner);
        if (insert > 0) {
            return true;
        } else {
            return false;
        }
    }

    @CacheEvict(value = "banner", allEntries = true)
    @Override
    public boolean updateById(CrmBanner banner) {
        int update = baseMapper.updateById(banner);
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @CacheEvict(value = "banner", allEntries = true)
    public void deleteById(String id) {
        baseMapper.deleteById(id);
    }
}
