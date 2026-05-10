package com.dao;

import com.entity.DiscussyiliaoziyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussyiliaoziyuanVO;
import com.entity.view.DiscussyiliaoziyuanView;


/**
 * 医疗资源评论
 * 
 * @author 
 * @email 
 * @date 2026-04-26 14:40:36
 */
public interface DiscussyiliaoziyuanDao extends BaseMapper<DiscussyiliaoziyuanEntity> {
	
	List<DiscussyiliaoziyuanVO> selectListVO(@Param("ew") Wrapper<DiscussyiliaoziyuanEntity> wrapper);
	
	DiscussyiliaoziyuanVO selectVO(@Param("ew") Wrapper<DiscussyiliaoziyuanEntity> wrapper);
	
	List<DiscussyiliaoziyuanView> selectListView(@Param("ew") Wrapper<DiscussyiliaoziyuanEntity> wrapper);

	List<DiscussyiliaoziyuanView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussyiliaoziyuanEntity> wrapper);

	
	DiscussyiliaoziyuanView selectView(@Param("ew") Wrapper<DiscussyiliaoziyuanEntity> wrapper);
	

}
