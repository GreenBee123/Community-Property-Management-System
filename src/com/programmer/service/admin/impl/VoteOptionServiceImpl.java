package com.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.VoteOptionDao;
import com.programmer.entity.admin.VoteOption;
import com.programmer.service.admin.VoteOptionService;

@Service
public class VoteOptionServiceImpl implements VoteOptionService{

	@Autowired
	private VoteOptionDao voteOpionDao;
	
	@Override
	public List<VoteOption> findListByVoteTopicId(Long topicId) {
		// TODO Auto-generated method stub
		return voteOpionDao.findListByVoteTopicId(topicId);
	}

	@Override
	public int add(VoteOption voteOption) {
		// TODO Auto-generated method stub
		return voteOpionDao.add(voteOption);
	}

	@Override
	public int edit(VoteOption voteOption) {
		// TODO Auto-generated method stub
		return voteOpionDao.edit(voteOption);
	}

	@Override
	public int delete(Long voteOptionId) {
		// TODO Auto-generated method stub
		return voteOpionDao.delete(voteOptionId);
	}

}
