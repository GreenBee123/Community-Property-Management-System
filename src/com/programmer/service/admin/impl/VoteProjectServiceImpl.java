package com.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.VoteProjectDao;
import com.programmer.entity.admin.VoteProject;
import com.programmer.service.admin.VoteProjectService;

/**
 * 投票项目service类
 * 
 * @author 聂峻民
 *
 */
@Service
public class VoteProjectServiceImpl implements VoteProjectService {

	@Autowired
	private VoteProjectDao voteProjectDao;
	
	@Override
	public VoteProject findVoteProjectById(Long voteProjectId) {
		// TODO Auto-generated method stub
		return voteProjectDao.findVoteProjectById(voteProjectId);
	}

	@Override
	public List<VoteProject> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return voteProjectDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return voteProjectDao.getTotal(queryMap);
	}

	@Override
	public int add(VoteProject voteProject) {
		// TODO Auto-generated method stub
		return voteProjectDao.add(voteProject);
	}

	@Override
	public int edit(VoteProject voteProject) {
		// TODO Auto-generated method stub
		return voteProjectDao.edit(voteProject);
	}

	@Override
	public int delete(Long voteProjectId) {
		// TODO Auto-generated method stub
		return voteProjectDao.delete(voteProjectId);
	}

}
