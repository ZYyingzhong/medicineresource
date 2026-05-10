package com.entity.view;

import com.entity.MessagesEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 反馈建议
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-04-26 14:40:36
 */
@TableName("messages")
public class MessagesView  extends MessagesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public MessagesView(){
	}
 
 	public MessagesView(MessagesEntity messagesEntity){
 	try {
			BeanUtils.copyProperties(this, messagesEntity);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
