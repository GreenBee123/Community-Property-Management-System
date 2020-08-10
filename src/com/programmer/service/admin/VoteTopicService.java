package com.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.programmer.entity.admin.VoteProject;
import com.programmer.entity.admin.VoteTopic;

/**
 * 投票题目Dao类
 * 
 * @author 聂峻民
 *
 */
@Service
public interface VoteTopicService {
	public VoteTopic findVoteTopicById(VoteTopic voteTopicId);
	public List<VoteTopic>findVoteTopicByVoteProject(Long voteProjectId);
	public List<VoteTopic>findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(VoteTopic voteTopic);
	public int edit(VoteTopic voteTopic);
	public int delete(Long voteTopicId);
}
