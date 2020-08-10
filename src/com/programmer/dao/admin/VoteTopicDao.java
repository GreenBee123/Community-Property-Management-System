package com.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.programmer.entity.admin.VoteProject;
import com.programmer.entity.admin.VoteTopic;

/**
 * 投票题目Dao类
 * 
 * @author 聂峻民
 *
 */
@Repository
public interface VoteTopicDao {
	public VoteTopic findVoteTopicById(VoteTopic voteTopicId);
	public List<VoteTopic>findVoteTopicByVoteProject(Long voteProjectId);
	public List<VoteTopic>findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(VoteTopic voteTopic);
	public int edit(VoteTopic voteTopic);
	public int delete(Long voteTopicId);
}
