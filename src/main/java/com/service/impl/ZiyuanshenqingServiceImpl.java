package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.ZiyuanshenqingDao;
import com.entity.ZiyuanshenqingEntity;
import com.service.ZiyuanshenqingService;
import com.entity.vo.ZiyuanshenqingVO;
import com.entity.view.ZiyuanshenqingView;

@Service("ziyuanshenqingService")
public class ZiyuanshenqingServiceImpl extends ServiceImpl<ZiyuanshenqingDao, ZiyuanshenqingEntity> implements ZiyuanshenqingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZiyuanshenqingEntity> page = this.selectPage(
                new Query<ZiyuanshenqingEntity>(params).getPage(),
                new EntityWrapper<ZiyuanshenqingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZiyuanshenqingEntity> wrapper) {
		  Page<ZiyuanshenqingView> page =new Query<ZiyuanshenqingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ZiyuanshenqingVO> selectListVO(Wrapper<ZiyuanshenqingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZiyuanshenqingVO selectVO(Wrapper<ZiyuanshenqingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZiyuanshenqingView> selectListView(Wrapper<ZiyuanshenqingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZiyuanshenqingView selectView(Wrapper<ZiyuanshenqingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
