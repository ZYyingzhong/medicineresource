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
 * 预约取消
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
@TableName("yuyuequxiao")
@Data
public class YuyuequxiaoEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public YuyuequxiaoEntity() {
		
	}
	
	public YuyuequxiaoEntity(T t) {
		try {
			BeanUtils.copyProperties(t, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 订单编号
	 */
	private String shenqingbianhao;
	
	/**
	 * 资源编号
	 */
	private String ziyuanbianhao;
	
	/**
	 * 资源名称
	 */
	private String ziyuanmingcheng;
	
	/**
	 * 资源图片
	 */
	private String ziyuantupian;
	
	/**
	 * 资源分类
	 */
	private String ziyuanfenlei;
	
	/**
	 * 资源价格
	 */
	private Double ziyuanjiage;
	
	/**
	 * 取消数量
	 */
	private Integer ziyuanshuliang;
	
	/**
	 * 取消金额
	 */
	private Double tuidingjine;
	
	/**
	 * 取消原因
	 */
	private String quxiaoyuanyin;
	
	/**
	 * 用户账号
	 */
	private String yonghuzhanghao;
	
	/**
	 * 医生工号
	 */
	private String yishenggonghao;
	
	/**
	 * 取消时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date quxiaoshijian;
	
	/**
	 * 是否审核
	 */
	private String sfsh;
	
	/**
	 * 审核回复
	 */
	private String shhf;
	
	/**
	 * 是否支付
	 */
	private String ispay;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
