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


import com.dao.DiscussjinqueziyuanDao;
import com.entity.DiscussjinqueziyuanEntity;
import com.service.DiscussjinqueziyuanService;
import com.entity.vo.DiscussjinqueziyuanVO;
import com.entity.view.DiscussjinqueziyuanView;

@Service("discussjinqueziyuanService")
public class DiscussjinqueziyuanServiceImpl extends ServiceImpl<DiscussjinqueziyuanDao, DiscussjinqueziyuanEntity> implements DiscussjinqueziyuanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussjinqueziyuanEntity> page = this.selectPage(
                new Query<DiscussjinqueziyuanEntity>(params).getPage(),
                new EntityWrapper<DiscussjinqueziyuanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussjinqueziyuanEntity> wrapper) {
		  Page<DiscussjinqueziyuanView> page =new Query<DiscussjinqueziyuanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussjinqueziyuanVO> selectListVO(Wrapper<DiscussjinqueziyuanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussjinqueziyuanVO selectVO(Wrapper<DiscussjinqueziyuanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussjinqueziyuanView> selectListView(Wrapper<DiscussjinqueziyuanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussjinqueziyuanView selectView(Wrapper<DiscussjinqueziyuanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
