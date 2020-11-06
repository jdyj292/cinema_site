package com.cinema.mypage.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinema.member.vo.MemberVO;
import com.cinema.mypage.dao.MyPageDAO;
import com.cinema.reservation.vo.ReservationVO;
import com.cinema.reservation.vo.SeatsVO;


@Service("myPageService")
@Transactional(propagation=Propagation.REQUIRED)
public class MyPageServiceImpl  implements MyPageService{
	@Autowired
	MyPageDAO myPageDAO;
	@Autowired
	SeatsVO seatsVO;

	public List<ReservationVO> listMyReservarion(String member_id) throws Exception{
		return myPageDAO.selectMyReservationList(member_id);
	}

	public void cancelReservation(int reservation_id,int schedule_id,String reservation_seats) throws Exception{
		seatsVO.setSchedule_id(schedule_id);
        String seats= reservation_seats;
        List<String> seats_id = Arrays.asList(seats.split(","));	    
		for(String i : seats_id) {
			seatsVO.setSeats_id(i);
			myPageDAO.deleteSeats(seatsVO); //새로고침이나 페이지변경시 좌석 삭제 
		}
				
		myPageDAO.deleteMyReservation(reservation_id);
	}
	
	public ReservationVO findMyReservationInfo(int reservation_id) throws Exception{
		return myPageDAO.selectMyReservationInfo(reservation_id);
	}
	
	public List<ReservationVO> listMyReservationHistory(Map dateMap) throws Exception{
		return myPageDAO.selectMyReservationHistoryList(dateMap);
	}
	public int listMyReservationHistorySize(Map condMap) throws Exception{
		return myPageDAO.selectMyReservationHistoryListSize(condMap);
	}
	
	public MemberVO  modifyMyInfo(Map memberMap) throws Exception{
		 String member_id=(String)memberMap.get("member_id");
		 myPageDAO.updateMyInfo(memberMap);
		 return myPageDAO.selectMyDetailInfo(member_id);
	}
	

}
