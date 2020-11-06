package com.cinema.reservation.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cinema.movies.vo.MoviesVO;
import com.cinema.reservation.vo.ReservationVO;
import com.cinema.reservation.vo.SeatsVO;
import com.cinema.admin.schedule.vo.ScheduleVO;
import com.cinema.movies.vo.ImageFileVO;

@Repository("reservationDAO")
public class ReservationDAOImpl  implements ReservationDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ScheduleVO> selectScheduleList() throws DataAccessException {
		ArrayList<ScheduleVO> scheduleList=(ArrayList)sqlSession.selectList("mapper.reservation.selectScheduleList");
	   return scheduleList;	
     
	}

	public List<String> selectScheduleDayList(Map scheduleMap) throws DataAccessException {
		ArrayList<String> scheduleList=(ArrayList)sqlSession.selectList("mapper.reservation.selectScheduleDayList",scheduleMap);
	   return scheduleList;	
     
	}
	
	public List<ScheduleVO> selectScheduleTimeList(Map scheduleMap) throws DataAccessException {
		ArrayList<ScheduleVO>  scheduleList=(ArrayList)sqlSession.selectList("mapper.reservation.selectScheduleTimeList",scheduleMap);
	   return scheduleList;	
     
	   
	}
	
	public void deleteSeatsTimeOut(int schedule_id) throws DataAccessException{
		sqlSession.delete("mapper.reservation.deleteSeatsTimeOut",schedule_id);

	}
		
	public List<String> selectSeatsList(int schedule_id) throws DataAccessException{
		ArrayList<String> seatsList=(ArrayList)sqlSession.selectList("mapper.reservation.selectSeatsList",schedule_id);
		   return seatsList;	
	}
	
	public void insertNewSeats(SeatsVO seatsVO) throws DataAccessException{
		sqlSession.insert("mapper.reservation.insertNewSeats",seatsVO);
	}
	
	public void deleteSeats(SeatsVO seatsVO) throws DataAccessException{
		sqlSession.delete("mapper.reservation.deleteSeats",seatsVO);

	}
	
	
	public void updateSeatsPurchaseStatus(SeatsVO seatsVO) throws DataAccessException{
		sqlSession.update("mapper.reservation.updateSeatsPurchaseStatus",seatsVO);
	}
	
	
	public void insertNewReservation(ReservationVO reservationVO) throws DataAccessException{
		sqlSession.insert("mapper.reservation.insertNewReservation",reservationVO);
	}

	
	
}
