package com.entity.vo;

import com.entity.ZiyuanshenqingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 资源申请
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public class ZiyuanshenqingVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
	 * 申请数量
	 */
	
	private Integer ziyuanshuliang;
		
	/**
	 * 申请费用
	 */
	
	private Double shenqingfeiyong;
		
	/**
	 * 申请原因
	 */
	
	private String shenqingyuanyin;
		
	/**
	 * 用户账号
	 */
	
	private String yonghuzhanghao;
		
	/**
	 * 医生工号
	 */
	
	private String yishenggonghao;
		
	/**
	 * 申请时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date shenqingshijian;
		
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
				
	
	/**
	 * 设置：资源编号
	 */
	 
	public void setZiyuanbianhao(String ziyuanbianhao) {
		this.ziyuanbianhao = ziyuanbianhao;
	}
	
	/**
	 * 获取：资源编号
	 */
	public String getZiyuanbianhao() {
		return ziyuanbianhao;
	}
				
	
	/**
	 * 设置：资源名称
	 */
	 
	public void setZiyuanmingcheng(String ziyuanmingcheng) {
		this.ziyuanmingcheng = ziyuanmingcheng;
	}
	
	/**
	 * 获取：资源名称
	 */
	public String getZiyuanmingcheng() {
		return ziyuanmingcheng;
	}
				
	
	/**
	 * 设置：资源图片
	 */
	 
	public void setZiyuantupian(String ziyuantupian) {
		this.ziyuantupian = ziyuantupian;
	}
	
	/**
	 * 获取：资源图片
	 */
	public String getZiyuantupian() {
		return ziyuantupian;
	}
				
	
	/**
	 * 设置：资源分类
	 */
	 
	public void setZiyuanfenlei(String ziyuanfenlei) {
		this.ziyuanfenlei = ziyuanfenlei;
	}
	
	/**
	 * 获取：资源分类
	 */
	public String getZiyuanfenlei() {
		return ziyuanfenlei;
	}
				
	
	/**
	 * 设置：资源价格
	 */
	 
	public void setZiyuanjiage(Double ziyuanjiage) {
		this.ziyuanjiage = ziyuanjiage;
	}
	
	/**
	 * 获取：资源价格
	 */
	public Double getZiyuanjiage() {
		return ziyuanjiage;
	}
				
	
	/**
	 * 设置：申请数量
	 */
	 
	public void setZiyuanshuliang(Integer ziyuanshuliang) {
		this.ziyuanshuliang = ziyuanshuliang;
	}
	
	/**
	 * 获取：申请数量
	 */
	public Integer getZiyuanshuliang() {
		return ziyuanshuliang;
	}
				
	
	/**
	 * 设置：申请费用
	 */
	 
	public void setShenqingfeiyong(Double shenqingfeiyong) {
		this.shenqingfeiyong = shenqingfeiyong;
	}
	
	/**
	 * 获取：申请费用
	 */
	public Double getShenqingfeiyong() {
		return shenqingfeiyong;
	}
				
	
	/**
	 * 设置：申请原因
	 */
	 
	public void setShenqingyuanyin(String shenqingyuanyin) {
		this.shenqingyuanyin = shenqingyuanyin;
	}
	
	/**
	 * 获取：申请原因
	 */
	public String getShenqingyuanyin() {
		return shenqingyuanyin;
	}
				
	
	/**
	 * 设置：用户账号
	 */
	 
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
				
	
	/**
	 * 设置：医生工号
	 */
	 
	public void setYishenggonghao(String yishenggonghao) {
		this.yishenggonghao = yishenggonghao;
	}
	
	/**
	 * 获取：医生工号
	 */
	public String getYishenggonghao() {
		return yishenggonghao;
	}
				
	
	/**
	 * 设置：申请时间
	 */
	 
	public void setShenqingshijian(Date shenqingshijian) {
		this.shenqingshijian = shenqingshijian;
	}
	
	/**
	 * 获取：申请时间
	 */
	public Date getShenqingshijian() {
		return shenqingshijian;
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
	 * 设置：是否支付
	 */
	 
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	
	/**
	 * 获取：是否支付
	 */
	public String getIspay() {
		return ispay;
	}
			
}
