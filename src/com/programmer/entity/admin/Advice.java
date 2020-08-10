package com.programmer.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;

public class Advice {
	private Long id;//装修id
	private String  name;//申请人信息
	private Date date;//申请日治其
	private String content;//保证金
	private int statu;//装修状态
	private Date approvalTime;//审批日期
	private String examiner;//审批人角色信息；
	private String remark;//标识信息
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public Date getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	public String getExaminer() {
		return examiner;
	}
	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Advice(Long id, String name, Date date, String content, int statu, Date approvalTime, String examiner,
			String remark) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.content = content;
		this.statu = statu;
		this.approvalTime = approvalTime;
		this.examiner = examiner;
		this.remark = remark;
	}

	
}
