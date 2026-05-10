package com.entity.vo;

import com.entity.PaiduijinduEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 排队进度
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public class PaiduijinduVO  implements Serializable {
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
	 * 排队序号
	 */
	
	private Integer paiduixuhao;
		
	/**
	 * 目前排序
	 */
	
	private Integer muqianpaixu;
		
	/**
	 * 用户账号
	 */
	
	private String yonghuzhanghao;
		
	/**
	 * 医生工号
	 */
	
	private String yishenggonghao;
		
	/**
	 * 更新时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date gengxinshijian;
				
	
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
	 * 设置：排队序号
	 */
	 
	public void setPaiduixuhao(Integer paiduixuhao) {
		this.paiduixuhao = paiduixuhao;
	}
	
	/**
	 * 获取：排队序号
	 */
	public Integer getPaiduixuhao() {
		return paiduixuhao;
	}
				
	
	/**
	 * 设置：目前排序
	 */
	 
	public void setMuqianpaixu(Integer muqianpaixu) {
		this.muqianpaixu = muqianpaixu;
	}
	
	/**
	 * 获取：目前排序
	 */
	public Integer getMuqianpaixu() {
		return muqianpaixu;
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
	 * 设置：更新时间
	 */
	 
	public void setGengxinshijian(Date gengxinshijian) {
		this.gengxinshijian = gengxinshijian;
	}
	
	/**
	 * 获取：更新时间
	 */
	public Date getGengxinshijian() {
		return gengxinshijian;
	}
			
}
