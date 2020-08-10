package com.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.programmer.entity.admin.VoteOption;

@Service
public interface VoteOptionService {
	
	public List<VoteOption>findListByVoteTopicId(Long topicId);
	public int add(VoteOption voteOption);
	public int edit(VoteOption voteOption);
	public int delete(Long voteOptionId);
}
