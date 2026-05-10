package com.dao;

import com.entity.PaiduijinduEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.PaiduijinduVO;
import com.entity.view.PaiduijinduView;


/**
 * 排队进度
 * 
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public interface PaiduijinduDao extends BaseMapper<PaiduijinduEntity> {
	
	List<PaiduijinduVO> selectListVO(@Param("ew") Wrapper<PaiduijinduEntity> wrapper);
	
	PaiduijinduVO selectVO(@Param("ew") Wrapper<PaiduijinduEntity> wrapper);
	
	List<PaiduijinduView> selectListView(@Param("ew") Wrapper<PaiduijinduEntity> wrapper);

	List<PaiduijinduView> selectListView(Pagination page,@Param("ew") Wrapper<PaiduijinduEntity> wrapper);

	
	PaiduijinduView selectView(@Param("ew") Wrapper<PaiduijinduEntity> wrapper);
	

}
