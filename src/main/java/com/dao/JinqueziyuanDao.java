package com.dao;

import com.entity.JinqueziyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JinqueziyuanVO;
import com.entity.view.JinqueziyuanView;


/**
 * 紧缺资源
 * 
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public interface JinqueziyuanDao extends BaseMapper<JinqueziyuanEntity> {
	
	List<JinqueziyuanVO> selectListVO(@Param("ew") Wrapper<JinqueziyuanEntity> wrapper);
	
	JinqueziyuanVO selectVO(@Param("ew") Wrapper<JinqueziyuanEntity> wrapper);
	
	List<JinqueziyuanView> selectListView(@Param("ew") Wrapper<JinqueziyuanEntity> wrapper);

	List<JinqueziyuanView> selectListView(Pagination page,@Param("ew") Wrapper<JinqueziyuanEntity> wrapper);

	
	JinqueziyuanView selectView(@Param("ew") Wrapper<JinqueziyuanEntity> wrapper);
	

}
