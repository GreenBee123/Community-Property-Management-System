package com.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 
 * 投票题目类
 * 
 * @author 聂峻民
 *
 */
@Component
public class VoteTopic {
	private Long id;//题目id
	private Long projectId;//改题目的项目id
	private String topicName;//题目名称
	private	int category;//类别
	private int topicVoteNumber;//投票数
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getTopicVoteNumber() {
		return topicVoteNumber;
	}
	public void setTopicVoteNumber(int topicVoteNumber) {
		this.topicVoteNumber = topicVoteNumber;
	}
	
	
}
