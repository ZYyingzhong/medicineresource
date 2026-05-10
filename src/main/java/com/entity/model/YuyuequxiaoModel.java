package com.entity.model;

import com.entity.YuyuequxiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 预约取消
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public class YuyuequxiaoModel  implements Serializable {
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
	 * 设置：取消数量
	 */
	 
	public void setZiyuanshuliang(Integer ziyuanshuliang) {
		this.ziyuanshuliang = ziyuanshuliang;
	}
	
	/**
	 * 获取：取消数量
	 */
	public Integer getZiyuanshuliang() {
		return ziyuanshuliang;
	}
				
	
	/**
	 * 设置：取消金额
	 */
	 
	public void setTuidingjine(Double tuidingjine) {
		this.tuidingjine = tuidingjine;
	}
	
	/**
	 * 获取：取消金额
	 */
	public Double getTuidingjine() {
		return tuidingjine;
	}
				
	
	/**
	 * 设置：取消原因
	 */
	 
	public void setQuxiaoyuanyin(String quxiaoyuanyin) {
		this.quxiaoyuanyin = quxiaoyuanyin;
	}
	
	/**
	 * 获取：取消原因
	 */
	public String getQuxiaoyuanyin() {
		return quxiaoyuanyin;
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
	 * 设置：取消时间
	 */
	 
	public void setQuxiaoshijian(Date quxiaoshijian) {
		this.quxiaoshijian = quxiaoshijian;
	}
	
	/**
	 * 获取：取消时间
	 */
	public Date getQuxiaoshijian() {
		return quxiaoshijian;
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
