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
 * 医疗资源
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-26 14:40:34
 */
@TableName("yiliaoziyuan")
@Data
public class YiliaoziyuanEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public YiliaoziyuanEntity() {
		
	}
	
	public YiliaoziyuanEntity(T t) {
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
	 * 注意事项
	 */
	private String zhuyishixiang;
	
	/**
	 * 资源价格
	 */
	private Double ziyuanjiage;
	
	/**
	 * 资源数量
	 */
	private Integer ziyuanshuliang;
	
	/**
	 * 资源用途
	 */
	private String ziyuanyongtu;
	
	/**
	 * 资源简介
	 */
	private String ziyuanjianjie;
	
	/**
	 * 最近点击时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date clicktime;
	
	/**
	 * 点击次数
	 */
	private Integer clicknum;
	
	/**
	 * 评论数
	 */
	private Integer discussnum;
	
	/**
	 * 收藏数
	 */
	private Integer storeupnum;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
