package com.programmer.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.VotingDao;
import com.programmer.entity.admin.Voting;
import com.programmer.service.admin.VotingService;

@Service
public class VotingServiceImpl implements VotingService {
	
	@Autowired
	private VotingDao votingDao;

	@Override
	public int add(Voting voting) {
		// TODO Auto-generated method stub
		return votingDao.add(voting);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return votingDao.delete(id);
	}

	@Override
	public List<Voting> findVotingByUserId(Long id) {
		// TODO Auto-generated method stub
		return votingDao.findVotingByUserId(id);
	}

}
