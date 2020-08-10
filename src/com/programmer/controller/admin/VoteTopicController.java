package com.programmer.controller.admin;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programmer.entity.admin.VoteOption;
import com.programmer.entity.admin.VoteProject;
import com.programmer.entity.admin.VoteTopic;
import com.programmer.page.admin.Page;
import com.programmer.service.admin.VoteOptionService;
import com.programmer.service.admin.VoteProjectService;
import com.programmer.service.admin.VoteTopicService;
import com.sun.org.apache.xpath.internal.operations.Mod;

/**
 * 
 * 
 * @author 聂峻民
 *
 */
@Controller
@RequestMapping(value = "/admin/votetopic")
public class VoteTopicController {

	@Autowired
	private VoteTopicService voteTopicService;

	@Autowired
	private VoteProjectService voteProjectService;

	@Autowired
	private VoteOptionService VoteOptionService;

	/**
	 * 进入投票项目界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addAttribute("VoteProject", voteProjectService.findList(queryMap));
		return "/votetopic/list";
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
	public Map<String, Object> getVoteTopicList(Page page,
			@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", voteTopicService.findList(queryMap));
		ret.put("total", voteTopicService.getTotal(queryMap));
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
	public Map<String, String> addVoteTopic(VoteTopic voteTopic) {
		Map<String, String> ret = new HashMap<String, String>();
		if (voteTopic == null || voteTopic.getProjectId() == null || voteTopic.getTopicName() == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的投票项目信息。");
			return ret;
		}
		if (voteTopicService.add(voteTopic) <= 0) {
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
	public Map<String, String> editVoteTopic(VoteTopic voteTopic) {
		Map<String, String> ret = new HashMap<String, String>();
		if (voteTopic == null || voteTopic.getProjectId() == null || voteTopic.getTopicName() == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的投票项目信息。");
			return ret;
		}
		if (voteTopicService.edit(voteTopic) <= 0) {
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
		if (voteTopicService.delete(id) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功。");
		return ret;
	}

	@RequestMapping(value = "/get_all_option", method = RequestMethod.POST)
	@ResponseBody
	public List<VoteOption> get_all_option(@RequestParam(name = "id", required = true) Long topicId) {
		return VoteOptionService.findListByVoteTopicId(topicId);
	}

	@RequestMapping(value = "/addoption", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOption(HttpServletRequest request,
			@RequestParam(name = "projectId", required = true) Long projectId,
			@RequestParam(name = "id", required = true) Long id) {
		Map<String, String> ret = new HashMap<String, String>();
		Map<String, String[]> contentList = request.getParameterMap();
		String value[]=contentList.get("name");
		List<VoteOption>voteOptions=new ArrayList<VoteOption>();
		for(String v:value) {
			if(v==null) {
				ret.put("type", "error");
				ret.put("msg", "请填写正确的选项信息。");
				return ret;
			}
			VoteOption voteOption=new VoteOption();
			voteOption.setTopicId(id);
			voteOption.setContent(v);
			voteOptions.add(voteOption);
		}
		VoteOptionService.delete(id);
		for(VoteOption v:voteOptions) {
			VoteOptionService.add(v);
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		return ret;
	}
}
