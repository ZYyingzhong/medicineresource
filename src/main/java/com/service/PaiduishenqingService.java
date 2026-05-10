package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.PaiduishenqingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.PaiduishenqingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.PaiduishenqingView;


/**
 * 排队申请
 *
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public interface PaiduishenqingService extends IService<PaiduishenqingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<PaiduishenqingVO> selectListVO(Wrapper<PaiduishenqingEntity> wrapper);
   	
   	PaiduishenqingVO selectVO(@Param("ew") Wrapper<PaiduishenqingEntity> wrapper);
   	
   	List<PaiduishenqingView> selectListView(Wrapper<PaiduishenqingEntity> wrapper);
   	
   	PaiduishenqingView selectView(@Param("ew") Wrapper<PaiduishenqingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<PaiduishenqingEntity> wrapper);

   	

}

