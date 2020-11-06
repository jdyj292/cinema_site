package com.cinema.admin.schedule.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.cinema.admin.schedule.dao.AdminScheduleDAO;
import com.cinema.admin.schedule.vo.ScheduleVO;





@Service("adminScheduleService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminScheduleServiceImpl implements AdminScheduleService {
	@Autowired
	AdminScheduleDAO adminScheduleDAO;
	
	@Override
	public void addNewSchedule(ScheduleVO scheduleVO) throws Exception{
		adminScheduleDAO.insertNewSchedule(scheduleVO);
		adminScheduleDAO.updateMoviesStatus(scheduleVO);
			
	}
	
	@Override
	public List<ScheduleVO> listNewSchedule(Map condMap) throws Exception{
		return adminScheduleDAO.selectNewScheduleList(condMap);
	}
	
	@Override	
	public int listNewScheduleSize(Map condMap) throws Exception{
		return adminScheduleDAO.selectNewScheduleListSize(condMap);
	}
	
	@Override
	public ScheduleVO scheduleDetail(int schedule_id) throws Exception {
		
		ScheduleVO scheduleVO=adminScheduleDAO.selectScheduleDetail(schedule_id);
		
		return scheduleVO;
	}

	
	@Override
	public void modifyScheduleInfo(Map scheduleMap) throws Exception{
		adminScheduleDAO.updateScheduleInfo(scheduleMap);
		
	}	

			


	
}
