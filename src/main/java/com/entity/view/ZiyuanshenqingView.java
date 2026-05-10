package com.entity.view;

import com.entity.ZiyuanshenqingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 资源申请
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
@TableName("ziyuanshenqing")
public class ZiyuanshenqingView  extends ZiyuanshenqingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ZiyuanshenqingView(){
	}
 
 	public ZiyuanshenqingView(ZiyuanshenqingEntity ziyuanshenqingEntity){
 	try {
			BeanUtils.copyProperties(this, ziyuanshenqingEntity);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
