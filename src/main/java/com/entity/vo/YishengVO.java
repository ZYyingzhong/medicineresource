package com.entity.vo;

import com.entity.YishengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 医生
 * @author 
 * @email 
 * @date 2026-04-26 14:40:34
 */
public class YishengVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
				
	
	/**
	 * 设置：密码
	 */
	 
	public void setMima(String mima) {
		this.mima = mima;
	}
	
	/**
	 * 获取：密码
	 */
	public String getMima() {
		return mima;
	}
				
	
	/**
	 * 设置：医生姓名
	 */
	 
	public void setYishengxingming(String yishengxingming) {
		this.yishengxingming = yishengxingming;
	}
	
	/**
	 * 获取：医生姓名
	 */
	public String getYishengxingming() {
		return yishengxingming;
	}
				
	
	/**
	 * 设置：头像
	 */
	 
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	
	/**
	 * 获取：头像
	 */
	public String getTouxiang() {
		return touxiang;
	}
				
	
	/**
	 * 设置：执行资格证
	 */
	 
	public void setZhixingzigezheng(String zhixingzigezheng) {
		this.zhixingzigezheng = zhixingzigezheng;
	}
	
	/**
	 * 获取：执行资格证
	 */
	public String getZhixingzigezheng() {
		return zhixingzigezheng;
	}
				
	
	/**
	 * 设置：科室名称
	 */
	 
	public void setKeshimingcheng(String keshimingcheng) {
		this.keshimingcheng = keshimingcheng;
	}
	
	/**
	 * 获取：科室名称
	 */
	public String getKeshimingcheng() {
		return keshimingcheng;
	}
				
	
	/**
	 * 设置：职称
	 */
	 
	public void setZhicheng(String zhicheng) {
		this.zhicheng = zhicheng;
	}
	
	/**
	 * 获取：职称
	 */
	public String getZhicheng() {
		return zhicheng;
	}
				
	
	/**
	 * 设置：服务时间
	 */
	 
	public void setFuwushijian(String fuwushijian) {
		this.fuwushijian = fuwushijian;
	}
	
	/**
	 * 获取：服务时间
	 */
	public String getFuwushijian() {
		return fuwushijian;
	}
				
	
	/**
	 * 设置：擅长领域
	 */
	 
	public void setShanzhanglingyu(String shanzhanglingyu) {
		this.shanzhanglingyu = shanzhanglingyu;
	}
	
	/**
	 * 获取：擅长领域
	 */
	public String getShanzhanglingyu() {
		return shanzhanglingyu;
	}
				
	
	/**
	 * 设置：职业履历
	 */
	 
	public void setZhiyelvli(String zhiyelvli) {
		this.zhiyelvli = zhiyelvli;
	}
	
	/**
	 * 获取：职业履历
	 */
	public String getZhiyelvli() {
		return zhiyelvli;
	}
				
	
	/**
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
				
	
	/**
	 * 设置：密保问题
	 */
	 
	public void setPquestion(String pquestion) {
		this.pquestion = pquestion;
	}
	
	/**
	 * 获取：密保问题
	 */
	public String getPquestion() {
		return pquestion;
	}
				
	
	/**
	 * 设置：密保答案
	 */
	 
	public void setPanswer(String panswer) {
		this.panswer = panswer;
	}
	
	/**
	 * 获取：密保答案
	 */
	public String getPanswer() {
		return panswer;
	}
			
}
