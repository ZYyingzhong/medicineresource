package com.entity.model;

import com.entity.JinqueziyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 紧缺资源
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2026-04-26 14:40:35
 */
public class JinqueziyuanModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
	 * 排队人数
	 */
	
	private Integer paiduirenshu;
		
	/**
	 * 资源用途
	 */
	
	private String ziyuanyongtu;
		
	/**
	 * 资源简介
	 */
	
	private String ziyuanjianjie;
		
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
	 * 设置：注意事项
	 */
	 
	public void setZhuyishixiang(String zhuyishixiang) {
		this.zhuyishixiang = zhuyishixiang;
	}
	
	/**
	 * 获取：注意事项
	 */
	public String getZhuyishixiang() {
		return zhuyishixiang;
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
	 * 设置：资源用途
	 */
	 
	public void setZiyuanyongtu(String ziyuanyongtu) {
		this.ziyuanyongtu = ziyuanyongtu;
	}
	
	/**
	 * 获取：资源用途
	 */
	public String getZiyuanyongtu() {
		return ziyuanyongtu;
	}
				
	
	/**
	 * 设置：资源简介
	 */
	 
	public void setZiyuanjianjie(String ziyuanjianjie) {
		this.ziyuanjianjie = ziyuanjianjie;
	}
	
	/**
	 * 获取：资源简介
	 */
	public String getZiyuanjianjie() {
		return ziyuanjianjie;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
