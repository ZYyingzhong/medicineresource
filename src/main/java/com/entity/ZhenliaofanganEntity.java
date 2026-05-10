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
 * 诊疗方案
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-26 14:40:34
 */
@TableName("zhenliaofangan")
@Data
public class ZhenliaofanganEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ZhenliaofanganEntity() {
		
	}
	
	public ZhenliaofanganEntity(T t) {
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
	 * 就诊卡号
	 */
	private String jiuzhenkahao;
	
	/**
	 * 用户账号
	 */
	private String yonghuzhanghao;
	
	/**
	 * 用户姓名
	 */
	private String yonghuxingming;
	
	/**
	 * 病人主诉
	 */
	private String bingrenzhusu;
	
	/**
	 * 既往史
	 */
	private String jiwangshi;
	
	/**
	 * 药敏史
	 */
	private String yaominshi;
	
	/**
	 * 诊断结果
	 */
	private String zhenduanjieguo;
	
	/**
	 * 治疗方案
	 */
	private String zhiliaofangan;
	
	/**
	 * 健康建议
	 */
	private String jiankangjianyi;
	
	/**
	 * 处方信息
	 */
	private String chufangxinxi;
	
	/**
	 * 医嘱
	 */
	private String yizhu;
	
	/**
	 * 医生工号
	 */
	private String yishenggonghao;
	
	/**
	 * 医生姓名
	 */
	private String yishengxingming;
	
	/**
	 * 头像
	 */
	private String touxiang;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
