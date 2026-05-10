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


import com.dao.JinqueziyuanDao;
import com.entity.JinqueziyuanEntity;
import com.service.JinqueziyuanService;
import com.entity.vo.JinqueziyuanVO;
import com.entity.view.JinqueziyuanView;

@Service("jinqueziyuanService")
public class JinqueziyuanServiceImpl extends ServiceImpl<JinqueziyuanDao, JinqueziyuanEntity> implements JinqueziyuanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JinqueziyuanEntity> page = this.selectPage(
                new Query<JinqueziyuanEntity>(params).getPage(),
                new EntityWrapper<JinqueziyuanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JinqueziyuanEntity> wrapper) {
		  Page<JinqueziyuanView> page =new Query<JinqueziyuanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<JinqueziyuanVO> selectListVO(Wrapper<JinqueziyuanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JinqueziyuanVO selectVO(Wrapper<JinqueziyuanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JinqueziyuanView> selectListView(Wrapper<JinqueziyuanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JinqueziyuanView selectView(Wrapper<JinqueziyuanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
