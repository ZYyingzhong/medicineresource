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


import com.dao.YiliaoziyuanDao;
import com.entity.YiliaoziyuanEntity;
import com.service.YiliaoziyuanService;
import com.entity.vo.YiliaoziyuanVO;
import com.entity.view.YiliaoziyuanView;

@Service("yiliaoziyuanService")
public class YiliaoziyuanServiceImpl extends ServiceImpl<YiliaoziyuanDao, YiliaoziyuanEntity> implements YiliaoziyuanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YiliaoziyuanEntity> page = this.selectPage(
                new Query<YiliaoziyuanEntity>(params).getPage(),
                new EntityWrapper<YiliaoziyuanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YiliaoziyuanEntity> wrapper) {
		  Page<YiliaoziyuanView> page =new Query<YiliaoziyuanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YiliaoziyuanVO> selectListVO(Wrapper<YiliaoziyuanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YiliaoziyuanVO selectVO(Wrapper<YiliaoziyuanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YiliaoziyuanView> selectListView(Wrapper<YiliaoziyuanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YiliaoziyuanView selectView(Wrapper<YiliaoziyuanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
