package com.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 投票选项类
 * 
 * @author 聂峻民
 *
 */
@Component
public class VoteOption {
	private Long id;//选项Id
	private Long topicId;//题目Id
	private String content;//选项内容
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
