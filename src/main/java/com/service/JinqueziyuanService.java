package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JinqueziyuanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JinqueziyuanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JinqueziyuanView;


/**
 * 紧缺资源
 *
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public interface JinqueziyuanService extends IService<JinqueziyuanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JinqueziyuanVO> selectListVO(Wrapper<JinqueziyuanEntity> wrapper);
   	
   	JinqueziyuanVO selectVO(@Param("ew") Wrapper<JinqueziyuanEntity> wrapper);
   	
   	List<JinqueziyuanView> selectListView(Wrapper<JinqueziyuanEntity> wrapper);
   	
   	JinqueziyuanView selectView(@Param("ew") Wrapper<JinqueziyuanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JinqueziyuanEntity> wrapper);

   	

}

