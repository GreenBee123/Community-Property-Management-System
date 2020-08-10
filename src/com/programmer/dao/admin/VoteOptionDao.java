package com.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.programmer.entity.admin.VoteOption;
import com.programmer.entity.admin.VoteProject;
import com.programmer.entity.admin.VoteTopic;

/**
 * 投票选项Dao
 * 
 * @author 聂峻民
 *
 */
@Repository
public interface VoteOptionDao {
	//public VoteProject findVoteTopicByVoteProject(VoteProject voteProject);
	
	public List<VoteOption>findListByVoteTopicId(Long topicId);
	//public int getTotal(Map<String, Object>queryMap);
	public int add(VoteOption voteOption);
	public int edit(VoteOption voteOption);
	public int delete(Long topicId);
}
