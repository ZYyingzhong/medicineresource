package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.PaiduijinduEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.PaiduijinduVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.PaiduijinduView;


/**
 * 排队进度
 *
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public interface PaiduijinduService extends IService<PaiduijinduEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<PaiduijinduVO> selectListVO(Wrapper<PaiduijinduEntity> wrapper);
   	
   	PaiduijinduVO selectVO(@Param("ew") Wrapper<PaiduijinduEntity> wrapper);
   	
   	List<PaiduijinduView> selectListView(Wrapper<PaiduijinduEntity> wrapper);
   	
   	PaiduijinduView selectView(@Param("ew") Wrapper<PaiduijinduEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<PaiduijinduEntity> wrapper);

   	

}

