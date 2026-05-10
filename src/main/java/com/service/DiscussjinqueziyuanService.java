package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussjinqueziyuanEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussjinqueziyuanVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussjinqueziyuanView;


/**
 * 紧缺资源评论
 *
 * @author 
 * @email 
 * @date 2026-04-26 14:40:36
 */
public interface DiscussjinqueziyuanService extends IService<DiscussjinqueziyuanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussjinqueziyuanVO> selectListVO(Wrapper<DiscussjinqueziyuanEntity> wrapper);
   	
   	DiscussjinqueziyuanVO selectVO(@Param("ew") Wrapper<DiscussjinqueziyuanEntity> wrapper);
   	
   	List<DiscussjinqueziyuanView> selectListView(Wrapper<DiscussjinqueziyuanEntity> wrapper);
   	
   	DiscussjinqueziyuanView selectView(@Param("ew") Wrapper<DiscussjinqueziyuanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussjinqueziyuanEntity> wrapper);

   	

}

