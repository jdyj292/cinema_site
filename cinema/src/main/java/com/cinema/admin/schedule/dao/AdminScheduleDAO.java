package com.cinema.admin.schedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.cinema.movies.vo.MoviesVO;
import com.cinema.admin.schedule.vo.ScheduleVO;
import com.cinema.movies.vo.ImageFileVO;


public interface AdminScheduleDAO {
	public void insertNewSchedule(ScheduleVO scheduleVO) throws DataAccessException;
	public void updateMoviesStatus(ScheduleVO scheduleVO) throws DataAccessException;
	public List<ScheduleVO>selectNewScheduleList(Map condMap) throws DataAccessException;
	public int selectNewScheduleListSize(Map condMap) throws DataAccessException;
	public ScheduleVO selectScheduleDetail(int schedule_id) throws DataAccessException;
	public void updateScheduleInfo(Map scheduleMap) throws DataAccessException;


	
}
