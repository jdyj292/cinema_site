package com.cinema.admin.schedule.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cinema.admin.schedule.vo.ScheduleVO;



@Repository("adminScheduleDAO")
public class AdminScheduleDAOImpl  implements AdminScheduleDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertNewSchedule(ScheduleVO scheduleVO) throws DataAccessException {
		sqlSession.insert("mapper.admin.schedule.insertNewSchedule",scheduleVO);
	
	}
	public void updateMoviesStatus(ScheduleVO scheduleVO) throws DataAccessException{
		sqlSession.update("mapper.admin.schedule.updateMoviesStatus",scheduleVO);
	}
		
	@Override
	public List<ScheduleVO>selectNewScheduleList(Map condMap) throws DataAccessException {
		ArrayList<ScheduleVO>  scheduleList=(ArrayList)sqlSession.selectList("mapper.admin.schedule.selectNewScheduleList",condMap);
		return scheduleList;
	}
	
	@Override
	public int selectNewScheduleListSize(Map condMap) throws DataAccessException{
		int scheduleListSize = sqlSession.selectOne("mapper.admin.schedule.selectNewScheduleListSize",
				condMap);
		return scheduleListSize;
	}
	
	@Override
	public ScheduleVO selectScheduleDetail(int schedule_id) throws DataAccessException{
		ScheduleVO scheduleBean = new ScheduleVO();
		scheduleBean=(ScheduleVO)sqlSession.selectOne("mapper.admin.schedule.selectScheduleDetail",schedule_id);
		return scheduleBean;
	}
	

	
	@Override
	public void updateScheduleInfo(Map scheduleMap) throws DataAccessException{
		sqlSession.update("mapper.admin.schedule.updateScheduleInfo",scheduleMap);
	}
	






	

}
