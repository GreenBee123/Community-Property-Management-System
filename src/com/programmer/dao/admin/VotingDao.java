package com.programmer.dao.admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.programmer.entity.admin.Voting;

/**
 * 用户投票Dao类
 * 
 * @author 聂峻民
 *
 */
@Repository
public interface VotingDao {
	public int add(Voting voting);
	public int delete(Long id);
	public List<Voting>findVotingByUserId(Long id);
}
