package com.programmer.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 投票类
 * 
 * @author 聂峻民
 *
 */
@Component
public class VoteProject {
	
	private Long id;//投票项目id
	private String projectName;//投票项目名称
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
