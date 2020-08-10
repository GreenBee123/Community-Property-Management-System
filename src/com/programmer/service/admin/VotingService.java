package com.programmer.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.programmer.entity.admin.Voting;

/**
 * 
 * 投票Service类
 * 
 * @author 聂峻民
 *
 */
@Service
public interface VotingService {
	public int add(Voting voting);
	public int delete(Long id);
	public List<Voting>findVotingByUserId(Long id);
}
