package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZiyuanshenqingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZiyuanshenqingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZiyuanshenqingView;


/**
 * 资源申请
 *
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public interface ZiyuanshenqingService extends IService<ZiyuanshenqingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZiyuanshenqingVO> selectListVO(Wrapper<ZiyuanshenqingEntity> wrapper);
   	
   	ZiyuanshenqingVO selectVO(@Param("ew") Wrapper<ZiyuanshenqingEntity> wrapper);
   	
   	List<ZiyuanshenqingView> selectListView(Wrapper<ZiyuanshenqingEntity> wrapper);
   	
   	ZiyuanshenqingView selectView(@Param("ew") Wrapper<ZiyuanshenqingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZiyuanshenqingEntity> wrapper);

   	

}

