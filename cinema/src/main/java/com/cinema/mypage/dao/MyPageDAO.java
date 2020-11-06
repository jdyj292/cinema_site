package com.cinema.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.cinema.member.vo.MemberVO;
import com.cinema.reservation.vo.ReservationVO;
import com.cinema.reservation.vo.SeatsVO;



public interface MyPageDAO {
	public List<ReservationVO> selectMyReservationList(String member_id) throws DataAccessException;
	public void deleteMyReservation(int reservation_id) throws DataAccessException;
	public void deleteSeats(SeatsVO seatsVO) throws DataAccessException;
	public ReservationVO selectMyReservationInfo(int order_id) throws DataAccessException;
	public List<ReservationVO> selectMyReservationHistoryList(Map dateMap) throws DataAccessException;
	public int selectMyReservationHistoryListSize (Map condMap) throws DataAccessException;
	public void updateMyInfo(Map memberMap) throws DataAccessException;
	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException;
	
}
