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


import com.dao.PaiduishenqingDao;
import com.entity.PaiduishenqingEntity;
import com.service.PaiduishenqingService;
import com.entity.vo.PaiduishenqingVO;
import com.entity.view.PaiduishenqingView;

@Service("paiduishenqingService")
public class PaiduishenqingServiceImpl extends ServiceImpl<PaiduishenqingDao, PaiduishenqingEntity> implements PaiduishenqingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PaiduishenqingEntity> page = this.selectPage(
                new Query<PaiduishenqingEntity>(params).getPage(),
                new EntityWrapper<PaiduishenqingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<PaiduishenqingEntity> wrapper) {
		  Page<PaiduishenqingView> page =new Query<PaiduishenqingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<PaiduishenqingVO> selectListVO(Wrapper<PaiduishenqingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public PaiduishenqingVO selectVO(Wrapper<PaiduishenqingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<PaiduishenqingView> selectListView(Wrapper<PaiduishenqingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PaiduishenqingView selectView(Wrapper<PaiduishenqingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
