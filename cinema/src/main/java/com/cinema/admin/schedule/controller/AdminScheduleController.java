package com.cinema.admin.schedule.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.admin.schedule.vo.ScheduleVO;

public interface AdminScheduleController {
	public ModelAndView adminScheduleMain(@RequestParam Map<String, String> dateMap,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ResponseEntity addNewSchedule(@ModelAttribute("schedule") ScheduleVO schedule, HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ResponseEntity modifyScheduleInfo( @RequestParam("schedule_id") String schedule_id,
                                 @RequestParam("mod_type") String mod_type,
                                 @RequestParam("value") String value,
			                     HttpServletRequest request, HttpServletResponse response)  throws Exception;
	
	
	
}
