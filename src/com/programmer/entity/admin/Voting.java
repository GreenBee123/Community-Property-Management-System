package com.programmer.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 用户投票类
 * 
 * 
 * @author 聂峻民
 *
 */
@Component
public class Voting {
	private Long idLong;//投票信息id
	private Long userId;//用户信息
	private Long optionId;//选项Id;
	private Date votingTime;//投票时间
	public Long getIdLong() {
		return idLong;
	}
	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public Date getVotingTime() {
		return votingTime;
	}
	public void setVotingTime(Date votingTime) {
		this.votingTime = votingTime;
	}
	
	
}
