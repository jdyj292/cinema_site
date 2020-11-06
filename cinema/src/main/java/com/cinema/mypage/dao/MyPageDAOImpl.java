package com.cinema.mypage.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cinema.admin.schedule.vo.ScheduleVO;
import com.cinema.member.vo.MemberVO;
import com.cinema.reservation.vo.ReservationVO;
import com.cinema.reservation.vo.SeatsVO;

@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO{
	@Autowired
	private SqlSession sqlSession;
	
	public List<ReservationVO> selectMyReservationList(String member_id) throws DataAccessException{
		List<ReservationVO> reservationList=(List)sqlSession.selectList("mapper.mypage.selectMyReservationList",member_id);
		return reservationList;
	}

	
	public void deleteMyReservation(int reservation_id) throws DataAccessException{
		sqlSession.delete("mapper.mypage.deleteMyReservation",reservation_id);
	}
	
	public void deleteSeats(SeatsVO seatsVO) throws DataAccessException{
		sqlSession.delete("mapper.mypage.deleteSeats",seatsVO);

	}
	
	public ReservationVO selectMyReservationInfo(int schedule_id) throws DataAccessException{
		ReservationVO reservationBean = new ReservationVO();
		reservationBean=(ReservationVO)sqlSession.selectOne("mapper.mypage.selectMyReservationInfo",schedule_id);
		return reservationBean;
		
	}
	
	public List<ReservationVO> selectMyReservationHistoryList(Map dateMap) throws DataAccessException{
		List<ReservationVO> myReservationHistList=(List)sqlSession.selectList("mapper.mypage.selectMyReservationHistoryList",dateMap);
		return myReservationHistList;
	}
	
	public int selectMyReservationHistoryListSize (Map condMap) throws DataAccessException{
		int myReservationHistListSize = sqlSession.selectOne("mapper.mypage.selectMyReservationHistoryListSize",
				condMap);
		return myReservationHistListSize;
	}
	public void updateMyInfo(Map memberMap) throws DataAccessException{
		sqlSession.update("mapper.mypage.updateMyInfo",memberMap);
	}
	
	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException{
		MemberVO memberVO=(MemberVO)sqlSession.selectOne("mapper.mypage.selectMyDetailInfo",member_id);
		return memberVO;
		
	}
	
}
