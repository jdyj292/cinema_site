package com.cinema.mypage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cinema.member.vo.MemberVO;
import com.cinema.reservation.vo.ReservationVO;

public interface MyPageService{
	public List<ReservationVO> listMyReservarion(String member_id) throws Exception;
	public void cancelReservation(int reservation_id,int schedule_id,String reservation_seats) throws Exception;
	public ReservationVO findMyReservationInfo(int reservation_id) throws Exception;
	public List<ReservationVO> listMyReservationHistory(Map dateMap) throws Exception;
	public int listMyReservationHistorySize(Map condMap) throws Exception;
	public MemberVO  modifyMyInfo(Map memberMap) throws Exception;
}
