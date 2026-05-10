package com.dao;

import com.entity.PaiduishenqingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.PaiduishenqingVO;
import com.entity.view.PaiduishenqingView;


/**
 * 排队申请
 * 
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public interface PaiduishenqingDao extends BaseMapper<PaiduishenqingEntity> {
	
	List<PaiduishenqingVO> selectListVO(@Param("ew") Wrapper<PaiduishenqingEntity> wrapper);
	
	PaiduishenqingVO selectVO(@Param("ew") Wrapper<PaiduishenqingEntity> wrapper);
	
	List<PaiduishenqingView> selectListView(@Param("ew") Wrapper<PaiduishenqingEntity> wrapper);

	List<PaiduishenqingView> selectListView(Pagination page,@Param("ew") Wrapper<PaiduishenqingEntity> wrapper);

	
	PaiduishenqingView selectView(@Param("ew") Wrapper<PaiduishenqingEntity> wrapper);
	

}
