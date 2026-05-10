package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YiliaoziyuanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YiliaoziyuanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YiliaoziyuanView;


/**
 * 医疗资源
 *
 * @author 
 * @email 
 * @date 2026-04-26 14:40:34
 */
public interface YiliaoziyuanService extends IService<YiliaoziyuanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YiliaoziyuanVO> selectListVO(Wrapper<YiliaoziyuanEntity> wrapper);
   	
   	YiliaoziyuanVO selectVO(@Param("ew") Wrapper<YiliaoziyuanEntity> wrapper);
   	
   	List<YiliaoziyuanView> selectListView(Wrapper<YiliaoziyuanEntity> wrapper);
   	
   	YiliaoziyuanView selectView(@Param("ew") Wrapper<YiliaoziyuanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YiliaoziyuanEntity> wrapper);

   	

}

