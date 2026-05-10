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


import com.dao.DiscussyiliaoziyuanDao;
import com.entity.DiscussyiliaoziyuanEntity;
import com.service.DiscussyiliaoziyuanService;
import com.entity.vo.DiscussyiliaoziyuanVO;
import com.entity.view.DiscussyiliaoziyuanView;

@Service("discussyiliaoziyuanService")
public class DiscussyiliaoziyuanServiceImpl extends ServiceImpl<DiscussyiliaoziyuanDao, DiscussyiliaoziyuanEntity> implements DiscussyiliaoziyuanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussyiliaoziyuanEntity> page = this.selectPage(
                new Query<DiscussyiliaoziyuanEntity>(params).getPage(),
                new EntityWrapper<DiscussyiliaoziyuanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussyiliaoziyuanEntity> wrapper) {
		  Page<DiscussyiliaoziyuanView> page =new Query<DiscussyiliaoziyuanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussyiliaoziyuanVO> selectListVO(Wrapper<DiscussyiliaoziyuanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussyiliaoziyuanVO selectVO(Wrapper<DiscussyiliaoziyuanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussyiliaoziyuanView> selectListView(Wrapper<DiscussyiliaoziyuanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussyiliaoziyuanView selectView(Wrapper<DiscussyiliaoziyuanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
