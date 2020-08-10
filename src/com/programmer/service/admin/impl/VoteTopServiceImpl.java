package com.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.VoteTopicDao;
import com.programmer.entity.admin.VoteProject;
import com.programmer.entity.admin.VoteTopic;
import com.programmer.service.admin.VoteTopicService;

@Service
public class VoteTopServiceImpl implements VoteTopicService {

	@Autowired
	private VoteTopicDao voteTopicDao;
	
	@Override
	public VoteTopic findVoteTopicById(VoteTopic voteTopicId) {
		// TODO Auto-generated method stub
		return voteTopicDao.findVoteTopicById(voteTopicId);
	}

	

	@Override
	public List<VoteTopic> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return voteTopicDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return voteTopicDao.getTotal(queryMap);
	}

	@Override
	public int add(VoteTopic voteTopic) {
		// TODO Auto-generated method stub
		return voteTopicDao.add(voteTopic);
	}

	@Override
	public int edit(VoteTopic voteTopic) {
		// TODO Auto-generated method stub
		return voteTopicDao.edit(voteTopic);
	}

	@Override
	public int delete(Long voteTopicId) {
		// TODO Auto-generated method stub
		return voteTopicDao.delete(voteTopicId);
	}



	@Override
	public List<VoteTopic> findVoteTopicByVoteProject(Long voteProjectId) {
		// TODO Auto-generated method stub
		return voteTopicDao.findVoteTopicByVoteProject(voteProjectId);
	}

}
