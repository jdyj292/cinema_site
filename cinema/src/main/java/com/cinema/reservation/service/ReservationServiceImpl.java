package com.cinema.reservation.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cinema.admin.schedule.vo.ScheduleVO;
import com.cinema.member.vo.MemberVO;
import com.cinema.movies.dao.MoviesDAO;
import com.cinema.movies.vo.MoviesVO;
import com.cinema.reservation.dao.ReservationDAO;
import com.cinema.reservation.vo.ReservationVO;
import com.cinema.reservation.vo.SeatsVO;
import com.cinema.movies.vo.ImageFileVO;

@Service("reservationService")
@Transactional(propagation=Propagation.REQUIRED)
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	ReservationDAO reservationDAO;
	
	@Autowired
	SeatsVO seatsVO;
	
	public List<ScheduleVO> listSchedule() throws Exception {
		List<ScheduleVO> scheduleList =reservationDAO.selectScheduleList();	
			
		return scheduleList;
	}
	
	public List<String> listScheduleDay(Map scheduleMap) throws Exception{
		return reservationDAO.selectScheduleDayList(scheduleMap);
	}
	
	public List<ScheduleVO> listScheduleTime(Map scheduleMap) throws Exception{
		List<ScheduleVO> scheduleTimeList = reservationDAO.selectScheduleTimeList(scheduleMap);
		
		return scheduleTimeList;
	}
	
	public List<String> listSeats(int schedule_id) throws Exception{
		reservationDAO.deleteSeatsTimeOut(schedule_id); //결제안된 상태로 31분 지난 좌석 삭제 
		List<String> seatsList = reservationDAO.selectSeatsList(schedule_id);

		return seatsList;
	}
	
	public void selectSeats(int schedule_id, List<String> seats_id) throws Exception{
		seatsVO.setSchedule_id(schedule_id);
	    
		for(String i : seats_id) {
			seatsVO.setSeats_id(i);
			reservationDAO.insertNewSeats(seatsVO);
		}	
	    	
	}
	
	public void deleteNewSeats(ReservationVO reservationVO) throws Exception{
		seatsVO.setSchedule_id(reservationVO.getSchedule_id());
        String seats= reservationVO.getReservation_seats();
        List<String> seats_id = Arrays.asList(seats.split(","));	    
		for(String i : seats_id) {
			seatsVO.setSeats_id(i);
			reservationDAO.deleteSeats(seatsVO); //새로고침이나 페이지변경시 좌석 삭제 
		}		
	    	
	}
		
	public void addReservation(ReservationVO reservationVO) throws Exception{
        seatsVO.setSchedule_id(reservationVO.getSchedule_id());
        String seats= reservationVO.getReservation_seats();
        List<String> seats_id = Arrays.asList(seats.split(","));	    
		for(String i : seats_id) {
			seatsVO.setSeats_id(i);
			reservationDAO.updateSeatsPurchaseStatus(seatsVO); //구매여부 입력
		}	
		
		reservationDAO.insertNewReservation(reservationVO);
	}
}
