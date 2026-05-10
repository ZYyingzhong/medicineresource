package com.dao;

import com.entity.DiscussjinqueziyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussjinqueziyuanVO;
import com.entity.view.DiscussjinqueziyuanView;


/**
 * 紧缺资源评论
 * 
 * @author 
 * @email 
 * @date 2026-04-26 14:40:36
 */
public interface DiscussjinqueziyuanDao extends BaseMapper<DiscussjinqueziyuanEntity> {
	
	List<DiscussjinqueziyuanVO> selectListVO(@Param("ew") Wrapper<DiscussjinqueziyuanEntity> wrapper);
	
	DiscussjinqueziyuanVO selectVO(@Param("ew") Wrapper<DiscussjinqueziyuanEntity> wrapper);
	
	List<DiscussjinqueziyuanView> selectListView(@Param("ew") Wrapper<DiscussjinqueziyuanEntity> wrapper);

	List<DiscussjinqueziyuanView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussjinqueziyuanEntity> wrapper);

	
	DiscussjinqueziyuanView selectView(@Param("ew") Wrapper<DiscussjinqueziyuanEntity> wrapper);
	

}
