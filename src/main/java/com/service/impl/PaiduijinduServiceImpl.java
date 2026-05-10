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


import com.dao.PaiduijinduDao;
import com.entity.PaiduijinduEntity;
import com.service.PaiduijinduService;
import com.entity.vo.PaiduijinduVO;
import com.entity.view.PaiduijinduView;

@Service("paiduijinduService")
public class PaiduijinduServiceImpl extends ServiceImpl<PaiduijinduDao, PaiduijinduEntity> implements PaiduijinduService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PaiduijinduEntity> page = this.selectPage(
                new Query<PaiduijinduEntity>(params).getPage(),
                new EntityWrapper<PaiduijinduEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<PaiduijinduEntity> wrapper) {
		  Page<PaiduijinduView> page =new Query<PaiduijinduView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<PaiduijinduVO> selectListVO(Wrapper<PaiduijinduEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public PaiduijinduVO selectVO(Wrapper<PaiduijinduEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<PaiduijinduView> selectListView(Wrapper<PaiduijinduEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PaiduijinduView selectView(Wrapper<PaiduijinduEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
