package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussyiliaoziyuanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussyiliaoziyuanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussyiliaoziyuanView;


/**
 * 医疗资源评论
 *
 * @author 
 * @email 
 * @date 2026-04-26 14:40:36
 */
public interface DiscussyiliaoziyuanService extends IService<DiscussyiliaoziyuanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussyiliaoziyuanVO> selectListVO(Wrapper<DiscussyiliaoziyuanEntity> wrapper);
   	
   	DiscussyiliaoziyuanVO selectVO(@Param("ew") Wrapper<DiscussyiliaoziyuanEntity> wrapper);
   	
   	List<DiscussyiliaoziyuanView> selectListView(Wrapper<DiscussyiliaoziyuanEntity> wrapper);
   	
   	DiscussyiliaoziyuanView selectView(@Param("ew") Wrapper<DiscussyiliaoziyuanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussyiliaoziyuanEntity> wrapper);

   	

}

