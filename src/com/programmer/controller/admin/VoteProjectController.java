package com.programmer.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programmer.entity.admin.House;
import com.programmer.entity.admin.User;
import com.programmer.entity.admin.VoteOption;
import com.programmer.entity.admin.VoteProject;
import com.programmer.entity.admin.VoteTopic;
import com.programmer.entity.admin.Voting;
import com.programmer.page.admin.Page;
import com.programmer.service.admin.VoteOptionService;
import com.programmer.service.admin.VoteProjectService;
import com.programmer.service.admin.VoteTopicService;
import com.programmer.service.admin.VotingService;

/**
 * 
 * 投票项目Controller类
 * 
 * @author 聂峻民
 *
 */
@Controller
@RequestMapping(value = "/admin/voteproject")
public class VoteProjectController {

	@Autowired
	private VoteProjectService voteProjectService;

	@Autowired
	private VoteTopicService voteTopicService;

	@Autowired
	private VoteOptionService voteOptionService;
	
	@Autowired
	private VotingService votingSerive;

	/**
	 * 进入投票项目界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "/voteproject/list";
	}

	/**
	 * 获取住房列表
	 * 
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getVoteProjectList(Page page,
			@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());

		ret.put("rows", voteProjectService.findList(queryMap));
		ret.put("total", voteProjectService.getTotal(queryMap));
		return ret;
	}

	/**
	 * 添加投票项目的信息
	 * 
	 * @param menu
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addVoteProject(
			@RequestParam(name = "projectName", required = true, defaultValue = "") String projectName,
			@RequestParam(name = "id", required = true, defaultValue = "") Long id,
			@RequestParam(name = "startTime", required = true, defaultValue = "") String startTime,
			@RequestParam(name = "endTime", required = true, defaultValue = "") String endTime) throws ParseException {
		Map<String, String> ret = new HashMap<String, String>();
		if (projectName == null || startTime == null || endTime == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的投票项目信息。");
			return ret;
		}
		VoteProject voteProject = new VoteProject();
		voteProject.setProjectName(projectName);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = simpleDateFormat.parse(startTime);
		Date end = simpleDateFormat.parse(endTime);
		voteProject.setStartTime(start);
		voteProject.setEndTime(end);
		voteProject.setId(id);
		if (voteProjectService.add(voteProject) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		return ret;
	}

	/**
	 * 修改住房信息
	 * 
	 * @param menu
	 *            传过去的指定住房
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> editVoteProject(@RequestParam(name = "id", required = true, defaultValue = "") Long id,
			@RequestParam(name = "projectName", required = true, defaultValue = "") String projectName,
			@RequestParam(name = "startTime", required = true, defaultValue = "") String startTime,
			@RequestParam(name = "endTime", required = true, defaultValue = "") String endTime) throws ParseException {
		Map<String, String> ret = new HashMap<String, String>();
		if (projectName == null || startTime == null || endTime == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的投票项目。");
			return ret;
		}
		VoteProject voteProject = new VoteProject();
		voteProject.setId(id);
		voteProject.setProjectName(projectName);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = simpleDateFormat.parse(startTime);
		Date end = simpleDateFormat.parse(endTime);
		voteProject.setStartTime(start);
		voteProject.setEndTime(end);
		if (voteProjectService.edit(voteProject) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		return ret;
	}

	/**
	 * 删除住房信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteVoteProject(@RequestParam(name = "id", required = true) Long id) {
		Map<String, String> ret = new HashMap<String, String>();
		if (id == null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的信息。");
			return ret;
		}
		if (voteProjectService.delete(id) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功。");
		return ret;
	}

	/**
	 * 获取题目
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get_voting_option", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get_voting_project(@RequestParam(name = "id", required = true) Long id) {
		Map<String, Object> ret = new HashMap<String, Object>();
		VoteProject voteProject = voteProjectService.findVoteProjectById(id);
		Date startTime = voteProject.getStartTime();
		Date endTime = voteProject.getEndTime();
		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		Date date = new Date();
		if (startTime.getTime() > date.getTime() || endTime.getTime() < date.getTime()) {
			ret.put("type", "error");
			ret.put("msg", "投票已结束或未开始。");
			return ret;
		}

		List<VoteTopic> voteTopics = voteTopicService.findVoteTopicByVoteProject(id);
		List<VoteOption> voteOptions = new ArrayList<VoteOption>();
		for (VoteTopic v : voteTopics) {
			// VoteOption voteOption=new VoteOption();
			voteOptions.addAll(voteOptionService.findListByVoteTopicId(v.getId()));
		}
		ret.put("VoteTopic", voteTopics);
		ret.put("VoteOption", voteOptions);

		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		return ret;
	}

	/**
	 * 添加投票信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addvoting", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addoption(HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		User user = (User) request.getSession().getAttribute("admin");
		Map<String, String[]> contentList = request.getParameterMap();
		for (String s : contentList.keySet()) {
			String[] o=contentList.get(s);
			Voting voting=new Voting();
			voting.setUserId(user.getId());
			voting.setOptionId(Long.valueOf(o[0]));
			voting.setVotingTime(new Date());
			if(votingSerive.add(voting)<=0) {
				ret.put("type", "error");
				ret.put("msg", "添加失败，请联系管理员。");
				return ret;
			}
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		return ret;
	}
}
