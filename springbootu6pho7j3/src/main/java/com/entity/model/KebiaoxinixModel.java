package com.entity.model;

import com.entity.KebiaoxinixEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 课表信息
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-04-19 00:31:48
 */
public class KebiaoxinixModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 节数
	 */
	
	private String sectionnum;
		
	/**
	 * 日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date riqi;
		
	/**
	 * 星期
	 */
	
	private Integer week;
		
	/**
	 * 场地
	 */
	
	private String changdi;
		
	/**
	 * 姓名
	 */
	
	private String xingming;
		
	/**
	 * 工号
	 */
	
	private String gonghao;
		
	/**
	 * 教练姓名
	 */
	
	private String jiaolianxingming;
				
	
	/**
	 * 设置：节数
	 */
	 
	public void setSectionnum(String sectionnum) {
		this.sectionnum = sectionnum;
	}
	
	/**
	 * 获取：节数
	 */
	public String getSectionnum() {
		return sectionnum;
	}
				
	
	/**
	 * 设置：日期
	 */
	 
	public void setRiqi(Date riqi) {
		this.riqi = riqi;
	}
	
	/**
	 * 获取：日期
	 */
	public Date getRiqi() {
		return riqi;
	}
				
	
	/**
	 * 设置：星期
	 */
	 
	public void setWeek(Integer week) {
		this.week = week;
	}
	
	/**
	 * 获取：星期
	 */
	public Integer getWeek() {
		return week;
	}
				
	
	/**
	 * 设置：场地
	 */
	 
	public void setChangdi(String changdi) {
		this.changdi = changdi;
	}
	
	/**
	 * 获取：场地
	 */
	public String getChangdi() {
		return changdi;
	}
				
	
	/**
	 * 设置：姓名
	 */
	 
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
	}
				
	
	/**
	 * 设置：工号
	 */
	 
	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}
	
	/**
	 * 获取：工号
	 */
	public String getGonghao() {
		return gonghao;
	}
				
	
	/**
	 * 设置：教练姓名
	 */
	 
	public void setJiaolianxingming(String jiaolianxingming) {
		this.jiaolianxingming = jiaolianxingming;
	}
	
	/**
	 * 获取：教练姓名
	 */
	public String getJiaolianxingming() {
		return jiaolianxingming;
	}
			
}
