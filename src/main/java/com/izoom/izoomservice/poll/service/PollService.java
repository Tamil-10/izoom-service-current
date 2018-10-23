package com.izoom.izoomservice.poll.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.izoom.izoomservice.poll.dao.PollDAO;
import com.izoom.izoomservice.poll.pojo.GroupVO;
import com.izoom.izoomservice.poll.pojo.PollVO;
import com.izoom.izoomservice.poll.pojo.PollVoteVO;

@RestController
@RequestMapping(value = "/api/poll")
public class PollService {

	private static Logger LOGGER = LoggerFactory.getLogger(PollService.class);

	@Autowired
	PollDAO pollDAO;

	@RequestMapping(method = RequestMethod.POST, value = "save")
	public PollVO save(@RequestBody PollVO pollVO) throws Exception {
		LOGGER.info("save enter");
		PollVO result = null;
		try {
			pollVO.setCreated_by(pollVO.getUser_name());
			pollVO.setCreated_date(new Date());
			result = pollDAO.save(pollVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("save exit");
		return result;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "updatepoll")
	public PollVO updatePoll(@RequestBody PollVO pollVO) throws Exception {
		LOGGER.info("updatePoll enter");
		PollVO result = null;
		try {
			pollVO.setModified_by(pollVO.getUser_name());
			pollVO.setModified_date(new Date());
			result = pollDAO.save(pollVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("updatePoll exit");
		return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "deletePoll")
	public PollVO deletePoll(@RequestBody PollVO pollVO) throws Exception {
		LOGGER.info("deletePoll enter");
		PollVO result = null;
		try {		
			result = pollDAO.delete(pollVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("deletePoll exit");
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "retrievePoll/{pollId}")
	public PollVO retrievePoll(@PathVariable(value = "pollId") String pollId) throws Exception {
		LOGGER.info("retrievePoll enter");
		PollVO result = null;
		try {
			result = pollDAO.retrievePoll(pollId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("retrievePoll exit");
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "retrievePollListByUserId/{userId}/{status}")
	public List<PollVO> retrievePollListByUserId(@PathVariable(value = "userId") String userId,
			@PathVariable(value = "status") String status) throws Exception {
		LOGGER.info("retrievePollListByUserId enter::" + userId);
		List<PollVO> result = null;
		try {
			result = pollDAO.retrievePollListByUserId(userId, status);
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("retrievePollListByUserId exit");
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "retrievePublishedPollListByContactId/{contactId}")
	public List<PollVO> retrievePublishedPollListByContactId(@PathVariable(value = "contactId") String contactId)
			throws Exception {
		LOGGER.info("retrievePublishedPollListByContactId enter::" + contactId);
		List<PollVO> result = pollDAO.retrievePublishedPollListByContactId(contactId);
		LOGGER.info("retrievePublishedPollListByContactId exit");
		return result;
	}

	@RequestMapping(method = RequestMethod.POST, value = "pollVote")
	public PollVoteVO pollVote(@RequestBody PollVoteVO pollVoteVO) throws Exception {
		LOGGER.info("pollVote enter");
		PollVoteVO result = null;
		try {
			pollVoteVO.setCreated_date(new Date());
			pollVoteVO.setCreated_by(pollVoteVO.getUser_id());
			result = pollDAO.pollVote(pollVoteVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("pollVote exit");
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "retrievePollResult/{poll_Id}")
	public String retrievePollResult(@PathVariable(value = "poll_Id") String poll_Id) throws Exception {
		LOGGER.info("retrievePollResult enter::" + poll_Id);
		String result = null;
		try {
			result = pollDAO.retrievePollResult(poll_Id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("retrievePollResult exit::" + result);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "retrieveUserGroupList/{userId}")
	public List<GroupVO> retrieveUserGroupList(@PathVariable(value = "userId") String userId) throws Exception {
		LOGGER.info("retrieveUserGroupList enter::" + userId);
		List<GroupVO> result = pollDAO.retrieveUserGroupList(userId);
		LOGGER.info("retrieveUserGroupList exit");
		return result;
	}
}
