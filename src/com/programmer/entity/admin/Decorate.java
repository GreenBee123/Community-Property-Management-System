package com.programmer.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 装修信息类
 * 
 * @author 聂峻民
 *
 */

public class Decorate {
	private Long id;//装修id
	private User user;//申请人信息
	private House house;//住房信息
	private Date applicationDate;//申请日治其
	private double bail;//保证金
	private double penalty;//违约金
	private String decorationContent;//装修内容
	private int statu;//装修状态
	private Date approvalTime;//审批日期
	private Role examiner;//审批人角色信息；
	private String remark;//标识信息
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public double getBail() {
		return bail;
	}
	public void setBail(double bail) {
		this.bail = bail;
	}
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public Role getExaminer() {
		return examiner;
	}
	public void setExaminer(Role examiner) {
		this.examiner = examiner;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	public String getDecorationContent() {
		return decorationContent;
	}
	public void setDecorationContent(String decorationContent) {
		this.decorationContent = decorationContent;
	}
	
	
}
