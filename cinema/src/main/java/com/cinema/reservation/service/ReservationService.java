package com.cinema.reservation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cinema.admin.schedule.vo.ScheduleVO;
import com.cinema.member.vo.MemberVO;
import com.cinema.movies.vo.MoviesVO;
import com.cinema.reservation.vo.ReservationVO;
import com.cinema.reservation.vo.SeatsVO;

public interface ReservationService {
	
	public List<ScheduleVO> listSchedule() throws Exception; 
	public List<String> listScheduleDay(Map scheduleMap) throws Exception;
	public List<ScheduleVO> listScheduleTime(Map scheduleMap) throws Exception;
	public List<String> listSeats(int schedule_id) throws Exception;
	public void selectSeats(int schedule_id, List<String> seats_id) throws Exception;
	public void deleteNewSeats(ReservationVO reservationVO) throws Exception;
	public void addReservation(ReservationVO reservationVO) throws Exception;

}
