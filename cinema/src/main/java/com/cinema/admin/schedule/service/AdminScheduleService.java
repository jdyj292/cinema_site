package com.cinema.admin.schedule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cinema.admin.schedule.vo.ScheduleVO;


public interface AdminScheduleService {
	public void  addNewSchedule(ScheduleVO scheduleVO) throws Exception;
	public List<ScheduleVO> listNewSchedule(Map condMap) throws Exception;
	public int listNewScheduleSize(Map condMap) throws Exception;
	public ScheduleVO scheduleDetail(int schedule_id) throws Exception;
	public void modifyScheduleInfo(Map scheduleMap) throws Exception;
	
}
