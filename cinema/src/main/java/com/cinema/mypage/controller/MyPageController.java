package com.cinema.mypage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface MyPageController {
	public ModelAndView cancelMyReservation(@RequestParam("reservation_id")  int reservation_id,@RequestParam("reservation_seats")  String reservation_seats,@RequestParam("schedule_id") int schedule_id,
            HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView myReservationDetail(@RequestParam("reservation_id")  int reservation_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView listMyReservationHistory(@RequestParam Map<String, String> dateMap,  HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ResponseEntity modifyMyInfo(@RequestParam("attribute")  String attribute,
					            @RequestParam("value")  String value,
					            HttpServletRequest request, HttpServletResponse response)  throws Exception;
	
	
}
