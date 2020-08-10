package com.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.programmer.entity.admin.VoteProject;

/**
 * 投票项目Dao类
 * 
 * @author 聂峻民
 *
 */
@Service
public interface VoteProjectService {
	public VoteProject findVoteProjectById(Long voteProjectId);
	public List<VoteProject>findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(VoteProject voteProject);
	public int edit(VoteProject voteProject);
	public int delete(Long voteProjectId);
}
