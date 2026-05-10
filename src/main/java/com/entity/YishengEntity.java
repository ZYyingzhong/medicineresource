package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 医生
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-26 14:40:34
 */
@TableName("yisheng")
@Data
public class YishengEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public YishengEntity() {
		
	}
	
	public YishengEntity(T t) {
		try {
			BeanUtils.copyProperties(t, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 主键id
	 */
    @TableId
	private Long id;
	/**
	 * 医生工号
	 */
	private String yishenggonghao;
	
	/**
	 * 密码
	 */
	private String mima;
	
	/**
	 * 医生姓名
	 */
	private String yishengxingming;
	
	/**
	 * 头像
	 */
	private String touxiang;
	
	/**
	 * 执行资格证
	 */
	private String zhixingzigezheng;
	
	/**
	 * 科室名称
	 */
	private String keshimingcheng;
	
	/**
	 * 职称
	 */
	private String zhicheng;
	
	/**
	 * 服务时间
	 */
	private String fuwushijian;
	
	/**
	 * 擅长领域
	 */
	private String shanzhanglingyu;
	
	/**
	 * 职业履历
	 */
	private String zhiyelvli;
	
	/**
	 * 是否审核
	 */
	private String sfsh;
	
	/**
	 * 审核回复
	 */
	private String shhf;
	
	/**
	 * 密保问题
	 */
	private String pquestion;
	
	/**
	 * 密保答案
	 */
	private String panswer;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
