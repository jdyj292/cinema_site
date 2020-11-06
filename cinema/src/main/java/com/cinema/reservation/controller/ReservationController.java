package com.cinema.reservation.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



public interface ReservationController {
	public ModelAndView choiceScheduleForm(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ResponseEntity searchScheduleDay( @RequestParam("movies_id") String movies_id,
            HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ResponseEntity searchScheduleTime( @RequestParam("movies_id") String movies_id,@RequestParam("schedule_day") String schedule_day,
            HttpServletRequest request, HttpServletResponse response) throws Exception;

}
