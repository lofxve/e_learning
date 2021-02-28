package com.wzd.cmsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzd.cmsservice.entity.CrmBanner;
import com.wzd.cmsservice.mapper.CrmBannerMapper;
import com.wzd.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
