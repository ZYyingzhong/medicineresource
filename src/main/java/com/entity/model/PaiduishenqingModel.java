package com.entity.model;

import com.entity.PaiduishenqingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 排队申请
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public class PaiduishenqingModel  implements Serializable {
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
	 * 排队人数
	 */
	
	private Integer paiduirenshu;
		
	/**
	 * 排队序号
	 */
	
	private Integer paiduixuhao;
		
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
	 * 设置：排队人数
	 */
	 
	public void setPaiduirenshu(Integer paiduirenshu) {
		this.paiduirenshu = paiduirenshu;
	}
	
	/**
	 * 获取：排队人数
	 */
	public Integer getPaiduirenshu() {
		return paiduirenshu;
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
			
}
