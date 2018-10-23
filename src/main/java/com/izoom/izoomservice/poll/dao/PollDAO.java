package com.izoom.izoomservice.poll.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.izoom.izoomservice.poll.pojo.GroupVO;
import com.izoom.izoomservice.poll.pojo.PollContactAssoc;
import com.izoom.izoomservice.poll.pojo.PollGroupAssoc;
import com.izoom.izoomservice.poll.pojo.PollGroupVO;
import com.izoom.izoomservice.poll.pojo.PollVO;
import com.izoom.izoomservice.poll.pojo.PollVoteVO;
import com.izoom.izoomservice.poll.repository.GroupRespository;
import com.izoom.izoomservice.poll.repository.PollAnswerRespository;
import com.izoom.izoomservice.poll.repository.PollGroupRespository;
import com.izoom.izoomservice.poll.repository.PollRespository;

@Repository
public class PollDAO {

	private static Logger LOGGER = LoggerFactory.getLogger(PollDAO.class);

	@Autowired
	PollRespository pollRespository;
	@Autowired
	PollGroupRespository pollGroupRespository;
	@Autowired
	PollAnswerRespository pollAnswerRespository;
	@Autowired
	GroupRespository groupRespository;
	@Autowired
	JdbcTemplate jdbcTemplate;

	public PollVO save(PollVO pollVO) throws Exception {
		LOGGER.info("save enter");
		try {
			List<String> groupIds = pollVO.getGroupId();
			pollRespository.save(pollVO);
			LOGGER.info("poll_id::::" + pollVO.getId());
			if (groupIds != null && !groupIds.isEmpty()) {
				PollGroupAssoc pollGroupAssoc = new PollGroupAssoc();
				pollGroupAssoc.setPoll_id(pollVO.getId());
				groupIds.forEach(groupId -> {
					pollGroupAssoc.setGroup_id(Long.parseLong(groupId));
					PollGroupVO pollGroupVO = new PollGroupVO();
					pollGroupVO.setPollGroupAssoc(pollGroupAssoc);
					pollGroupRespository.save(pollGroupVO);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("poll id:::" + pollVO.getId());
		LOGGER.info("save exit");
		return pollVO;
	}

	public PollVO delete(PollVO pollVO) throws Exception {
		LOGGER.info("delete enter");
		try {
			LOGGER.info("poll_id::::" + pollVO.getId());
			jdbcTemplate.update("update poll set status = 'Deleted' where id=?", new Object[] { pollVO.getId() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("poll id:::" + pollVO.getId());
		LOGGER.info("delete exit");
		return pollVO;
	}

	public PollVO retrievePoll(String pollId) throws Exception {
		LOGGER.info("retrievePoll enter");
		Optional<PollVO> pollVO = null;
		try {
			pollVO = pollRespository.findById(Long.parseLong(pollId));
			List<String> grpList = jdbcTemplate.queryForList("select group_id from poll_group_assoc where poll_id=?",
					String.class, new Object[] { pollId });
			pollVO.get().setGroupId(grpList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("poll id:::" + pollVO.get().getId());
		LOGGER.info("retrievePoll exit");
		return pollVO.get();
	}

	public List<PollVO> retrievePollListByUserId(String userId, String status) throws Exception {
		LOGGER.info("retrievePollListByUserId enter");
		List<PollVO> pollList = new ArrayList<>();
		try {
			String pollSql = "select * from POLL where user_id=?  and status =?";
			pollList.addAll((List<PollVO>) jdbcTemplate.query(pollSql, (rs, rowNum) -> {
				PollVO poll = new PollVO();
				poll.setId(rs.getLong("ID"));
				poll.setUser_id(rs.getLong("USER_ID"));
				poll.setQuestion(rs.getString("QUESTION"));
				poll.setAnswer1(rs.getString("ANSWER1"));
				poll.setAnswer2(rs.getString("ANSWER2"));
				poll.setAnswer3(rs.getString("ANSWER3"));
				poll.setAnswer4(rs.getString("ANSWER4"));
				poll.setPublish_time_limit(rs.getLong("PUBLISH_TIME_LIMIT"));
				poll.setPublish_start_time(rs.getDate("PUBLISH_START_TIME"));
				poll.setPublish_end_time(rs.getDate("PUBLISH_END_TIME"));
				poll.setStatus(rs.getString("STATUS"));
				poll.setCreated_by(rs.getString("CREATED_BY"));
				poll.setCreated_date(rs.getDate("CREATED_DATE"));
				poll.setModified_by(rs.getString("MODIFIED_BY"));
				poll.setModified_date(rs.getDate("MODIFIED_DATE"));
				return poll;
			}, new Object[] { userId, status }));

			pollList.forEach(poll -> System.out.println(poll.getQuestion()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("retrievePollListByUserId exit");
		return pollList;
	}

	public List<PollVO> retrievePublishedPollListByContactId(String contact_id) throws Exception {
		LOGGER.info("retrievePublishedPollListByContactId enter");
		List<PollVO> pollList = new ArrayList<>();
		try {
			String sql = "select poll_id from POLL_GROUP_ASSOC where group_id in ( select distinct group_id from GROUP_CONTACT_ASSOC where contact_id=? )";
			List<String> pollids = jdbcTemplate.queryForList(sql, String.class, new Object[] { contact_id });
			pollids.forEach(System.out::println);
			pollids.forEach(pollid -> {
				String pollSql = "select * from POLL where id=?  and status =?";
				pollList.addAll((List<PollVO>) jdbcTemplate.query(pollSql, (rs, rowNum) -> {
					PollVO poll = new PollVO();
					poll.setId(rs.getLong("ID"));
					poll.setUser_id(rs.getLong("USER_ID"));
					poll.setQuestion(rs.getString("QUESTION"));
					poll.setAnswer1(rs.getString("ANSWER1"));
					poll.setAnswer2(rs.getString("ANSWER2"));
					poll.setAnswer3(rs.getString("ANSWER3"));
					poll.setAnswer4(rs.getString("ANSWER4"));
					poll.setPublish_time_limit(rs.getLong("PUBLISH_TIME_LIMIT"));
					poll.setPublish_start_time(rs.getDate("PUBLISH_START_TIME"));
					poll.setPublish_end_time(rs.getDate("PUBLISH_END_TIME"));
					poll.setStatus(rs.getString("STATUS"));
					poll.setCreated_by(rs.getString("CREATED_BY"));
					poll.setCreated_date(rs.getDate("CREATED_DATE"));
					poll.setModified_by(rs.getString("MODIFIED_BY"));
					poll.setModified_date(rs.getDate("MODIFIED_DATE"));
					return poll;
				}, new Object[] { pollid, "Published" }));
			});

			pollList.forEach(poll -> System.out.println(poll.getQuestion()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("retrievePublishedPollListByContactId exit");
		return pollList;
	}

	public PollVoteVO pollVote(PollVoteVO pollVoteVO) throws Exception {
		LOGGER.info("pollVote enter");
		try {
			Optional<PollVoteVO> expollVoteVO = pollAnswerRespository.findById(pollVoteVO.getPollContactAssoc());
			if (!expollVoteVO.isPresent()) {
				pollAnswerRespository.save(pollVoteVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("pollVote exit");
		return pollVoteVO;
	}

	public String retrievePollResult(String poll_Id) throws Exception {
		LOGGER.info("retrievePollResult enter");

		StringBuilder sb = new StringBuilder();
		try {
			Optional<PollVO> pollVo = pollRespository.findById(Long.parseLong(poll_Id));
			List<PollVoteVO> pollVoteList = new ArrayList<>();

			String pollSql = "select * from POLL_ANSWER where poll_id=? ";
			pollVoteList.addAll((List<PollVoteVO>) jdbcTemplate.query(pollSql, (rs, rowNum) -> {
				PollVoteVO poll = new PollVoteVO();
				PollContactAssoc pollContactAssoc = new PollContactAssoc();
				pollContactAssoc.setPoll_id(rs.getLong("POLL_ID"));
				pollContactAssoc.setResponser_id(rs.getLong("RESPONDER_ID"));
				poll.setPollContactAssoc(pollContactAssoc);
				poll.setAnswer(rs.getString("ANSWER"));
				return poll;
			}, new Object[] { poll_Id }));

			long totalcnt = pollVoteList.size();
			long ans1cnt = pollVoteList.stream()
					.filter(pv -> pv.getAnswer().equalsIgnoreCase(pollVo.get().getAnswer1())).count();
			long ans2cnt = pollVoteList.stream()
					.filter(pv -> pv.getAnswer().equalsIgnoreCase(pollVo.get().getAnswer2())).count();
			long ans3cnt = pollVoteList.stream()
					.filter(pv -> pv.getAnswer().equalsIgnoreCase(pollVo.get().getAnswer3())).count();
			long ans4cnt = pollVoteList.stream()
					.filter(pv -> pv.getAnswer().equalsIgnoreCase(pollVo.get().getAnswer4())).count();

			long ans1percentage = (ans1cnt * 100) / totalcnt;
			long ans2percentage = (ans2cnt * 100) / totalcnt;
			long ans3percentage = (ans3cnt * 100) / totalcnt;
			long ans4percentage = (ans4cnt * 100) / totalcnt;

			sb.append("{");
			sb.append("\"ans1cnt\":\"" + ans1cnt + "\"");
			sb.append(", \"ans2cnt\":\"" + ans2cnt + "\"");
			sb.append(", \"ans3cnt\":\"" + ans3cnt + "\"");
			sb.append(", \"ans4cnt\":\"" + ans4cnt + "\"");

			sb.append(", \"ans1percentage\":\"" + ans1percentage + "\"");
			sb.append(", \"ans2percentage\":\"" + ans2percentage + "\"");
			sb.append(", \"ans3percentage\":\"" + ans3percentage + "\"");
			sb.append(", \"ans4percentage\":\"" + ans4percentage + "\"");

			sb.append("}");

			LOGGER.info("result--->" + sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("retrievePollResult exit");
		return sb.toString();
	}

	public List<GroupVO> retrieveUserGroupList(String user_id) throws Exception {
		LOGGER.info("retrieveUserGroupList enter");
		List<GroupVO> pollList = new ArrayList<>();
		try {

			String pollSql = "select * from GROUP_USER where user_id=?";
			pollList.addAll((List<GroupVO>) jdbcTemplate.query(pollSql, (rs, rowNum) -> {
				GroupVO groupVO = new GroupVO();
				groupVO.setId(rs.getLong("ID"));
				groupVO.setUser_id(rs.getLong("USER_ID"));
				groupVO.setGroup_name(rs.getString("GROUP_NAME"));
				return groupVO;
			}, new Object[] { user_id }));

			pollList.forEach(group -> System.out.println(group.getGroup_name()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("retrieveUserGroupList exit");
		return pollList;
	}

}
