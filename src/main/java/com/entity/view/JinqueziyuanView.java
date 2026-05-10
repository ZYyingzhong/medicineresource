package com.entity.view;

import com.entity.JinqueziyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 紧缺资源
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
@TableName("jinqueziyuan")
public class JinqueziyuanView  extends JinqueziyuanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JinqueziyuanView(){
	}
 
 	public JinqueziyuanView(JinqueziyuanEntity jinqueziyuanEntity){
 	try {
			BeanUtils.copyProperties(this, jinqueziyuanEntity);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
