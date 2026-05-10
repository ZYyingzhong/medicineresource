package com.dao;

import com.entity.YiliaoziyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YiliaoziyuanVO;
import com.entity.view.YiliaoziyuanView;


/**
 * 医疗资源
 * 
 * @author 
 * @email 
 * @date 2026-04-26 14:40:34
 */
public interface YiliaoziyuanDao extends BaseMapper<YiliaoziyuanEntity> {
	
	List<YiliaoziyuanVO> selectListVO(@Param("ew") Wrapper<YiliaoziyuanEntity> wrapper);
	
	YiliaoziyuanVO selectVO(@Param("ew") Wrapper<YiliaoziyuanEntity> wrapper);
	
	List<YiliaoziyuanView> selectListView(@Param("ew") Wrapper<YiliaoziyuanEntity> wrapper);

	List<YiliaoziyuanView> selectListView(Pagination page,@Param("ew") Wrapper<YiliaoziyuanEntity> wrapper);

	
	YiliaoziyuanView selectView(@Param("ew") Wrapper<YiliaoziyuanEntity> wrapper);
	

}
