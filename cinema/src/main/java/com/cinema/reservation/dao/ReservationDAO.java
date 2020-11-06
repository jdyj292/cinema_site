package com.cinema.reservation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.cinema.movies.vo.MoviesVO;
import com.cinema.reservation.vo.ReservationVO;
import com.cinema.reservation.vo.SeatsVO;
import com.cinema.admin.schedule.vo.ScheduleVO;
import com.cinema.movies.vo.ImageFileVO;

public interface ReservationDAO {
	public List<ScheduleVO> selectScheduleList() throws DataAccessException;
	public List<String> selectScheduleDayList(Map scheduleMap) throws DataAccessException;
	public List<ScheduleVO> selectScheduleTimeList(Map scheduleMap) throws DataAccessException;
	public void deleteSeatsTimeOut(int schedule_id) throws DataAccessException;
	public List<String> selectSeatsList(int schedule_id) throws DataAccessException;
	public void insertNewSeats(SeatsVO seatsVO) throws DataAccessException;
	public void deleteSeats(SeatsVO seatsVO) throws DataAccessException;
	public void updateSeatsPurchaseStatus(SeatsVO seatsVO) throws DataAccessException;
	public void insertNewReservation(ReservationVO reservationVO) throws DataAccessException;
	
	

}
