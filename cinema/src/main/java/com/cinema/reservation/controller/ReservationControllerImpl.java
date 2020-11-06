package com.cinema.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.admin.schedule.vo.ScheduleVO;
import com.cinema.common.base.BaseController;
import com.cinema.member.vo.MemberVO;
import com.cinema.movies.service.MoviesService;
import com.cinema.movies.vo.MoviesVO;
import com.cinema.reservation.service.ReservationService;
import com.cinema.reservation.vo.ReservationVO;

@Controller("reservationController")
@RequestMapping(value="/reservation")
public class ReservationControllerImpl extends BaseController implements ReservationController {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	MemberVO memberVO;
	@Autowired
	ReservationVO reservationVO;
	@Autowired
	MoviesService moviesService;
	
	
    //1단계
	@RequestMapping(value= "/choiceScheduleForm.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView choiceScheduleForm(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		HttpSession session=request.getSession();
		String resProcedure= (String) session.getAttribute("resProcedure"); 
		if(resProcedure=="5단계"){	
			reservationVO =(ReservationVO)session.getAttribute("reservationInfo");
			reservationService.deleteNewSeats(reservationVO); //처음부터 할 시 좌석 삭제
		}
		
		session.setAttribute("resProcedure","2단계"); //예약 절차 생성 
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView(viewName);
		
		List<ScheduleVO> scheduleList=reservationService.listSchedule();
		mav.addObject("scheduleList", scheduleList);
		mav.addObject("side_status", "choiceScheduleForm");
		
		return mav;
	}
	
	@RequestMapping(value="/searchScheduleDay.do" ,method={RequestMethod.POST} ,produces = "application/json; charset=utf8")
	public ResponseEntity searchScheduleDay( @RequestParam("movies_id") String movies_id,
                                 HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		Map<String,String> scheduleMap=new HashMap<String,String>();
		scheduleMap.put("movies_id", movies_id);
		
		List scheduleDayList =reservationService.listScheduleDay(scheduleMap);
		
		
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		resEntity =new ResponseEntity(scheduleDayList, responseHeaders, HttpStatus.OK);
		return resEntity;
		
	}
	
	@RequestMapping(value="/searchScheduleTime.do" ,method={RequestMethod.POST} ,produces = "application/json; charset=utf8")
	public ResponseEntity searchScheduleTime( @RequestParam("movies_id") String movies_id,@RequestParam("schedule_day") String schedule_day,
                                 HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		Map<String,String> scheduleMap=new HashMap<String,String>();
		scheduleMap.put("movies_id", movies_id);
		scheduleMap.put("schedule_day", schedule_day);
		
		List<ScheduleVO> scheduleList =reservationService.listScheduleTime(scheduleMap);
		
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		resEntity =new ResponseEntity(scheduleList, responseHeaders, HttpStatus.OK);
		return resEntity;
		
	}
	
	@RequestMapping(value="/checklogin.do" ,method = RequestMethod.POST)
	public ResponseEntity checklogin(@RequestParam("schedule_id") int schedule_id,@RequestParam("movies_title") String movies_title,@RequestParam("movies_id") int movies_id,
			@RequestParam("movies_filename") String movies_filename, @RequestParam("movies_age_restriction") String movies_age_restriction,
			@RequestParam("schedule_begin_date") String schedule_begin_date,@RequestParam("schedule_end_date") String schedule_end_date,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		reservationVO.setSchedule_id(schedule_id);
		reservationVO.setMovies_title(movies_title);
		reservationVO.setMovies_id(movies_id);
		reservationVO.setMovies_age_restriction(movies_age_restriction);
		reservationVO.setMovies_filename(movies_filename);
		reservationVO.setSchedule_begin_date(schedule_begin_date);
		reservationVO.setSchedule_end_date(schedule_end_date);
		session.setAttribute("reservationInfo", reservationVO);
		
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn"); //로그인 체크 
		String action=(String)session.getAttribute("action");		
		if(isLogOn==null || isLogOn==false){			
			session.setAttribute("action", "/reservation/updateAddressForm.do");
			String result = "needLogin";
			ResponseEntity resEntity =new ResponseEntity(result, HttpStatus.OK);
			return resEntity;
		}
		
		String result="loginOn";
		ResponseEntity resEntity =new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
		
	}
	
	//2단계
	@RequestMapping(value= "/updateAddressForm.do" ,method={RequestMethod.POST})
	public ModelAndView updateAddressForm(HttpServletRequest request, HttpServletResponse response)  throws Exception{		
		HttpSession session=request.getSession();		
		String resProcedure= (String) session.getAttribute("resProcedure"); //예약 절차 체크 	
		if(resProcedure==null || !resProcedure.equals("2단계")){			
			return new ModelAndView("redirect:/reservation/choiceScheduleForm.do");
		}
		session.setAttribute("resProcedure","3단계");
		
		String action=(String)session.getAttribute("action");	
		if(action!=null && action.equals("/reservation/updateAddressForm.do"))
		session.removeAttribute("action");
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView(viewName);
		
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		
		reservationVO.setMember_id(memberVO.getMember_id());
		reservationVO.setMember_name(memberVO.getMember_name());
		session.setAttribute("reservationInfo", reservationVO); //받아온 정보는 세션에 저장 
		
		mav.addObject("side_status", "choiceScheduleForm");
		return mav;
		
	}
	//3단계
	@RequestMapping(value= "/selectSeatsForm.do" ,method={RequestMethod.POST})
	public ModelAndView selectSeatsForm(@ModelAttribute("reservationVO") ReservationVO _reservationVO,  HttpServletRequest request, HttpServletResponse response)  throws Exception{
		HttpSession session=request.getSession();
		String resProcedure= (String) session.getAttribute("resProcedure"); //예약 절차 체크 	
		if(resProcedure==null || !resProcedure.equals("3단계")){			
			return new ModelAndView("redirect:/reservation/choiceScheduleForm.do");
		}
		session.setAttribute("resProcedure","4단계");
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView(viewName);
		
		reservationVO =(ReservationVO)session.getAttribute("reservationInfo");
		reservationVO.setReservation_hp1(_reservationVO.getReservation_hp1());
		reservationVO.setReservation_hp2(_reservationVO.getReservation_hp2());
		reservationVO.setReservation_hp3(_reservationVO.getReservation_hp3());
		reservationVO.setReservation_email1(_reservationVO.getReservation_email1());
		reservationVO.setReservation_email2(_reservationVO.getReservation_email2());
		session.setAttribute("reservationInfo", reservationVO);
	
		mav.addObject("side_status", "choiceScheduleForm");
		return mav;
		
	}
	
	@RequestMapping(value="/selectSeatsList.do" ,method={RequestMethod.POST} ,produces = "application/json; charset=utf8") //예약된 좌석 비활성화
	public ResponseEntity selectSeatsList(@RequestParam("schedule_id") int schedule_id,  HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		List seatsList = reservationService.listSeats(schedule_id);
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		resEntity =new ResponseEntity(seatsList, responseHeaders, HttpStatus.OK);
		return resEntity;
		
	}
	
	@RequestMapping(value="/addNewSeats.do" ,method={RequestMethod.POST} ,produces = "application/json; charset=utf8")//좌석 예약
	public ResponseEntity addNewSeats( @RequestParam("schedule_id") int schedule_id,@RequestParam("seats_id") List<String> seats_id,  HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		reservationService.selectSeats(schedule_id,seats_id);
		
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		resEntity =new ResponseEntity(0, responseHeaders, HttpStatus.OK);
		return resEntity;
		
	}
	//4단계
	@RequestMapping(value= "/reservationForm.do" ,method={RequestMethod.POST})
	public ModelAndView reservationForm(@ModelAttribute("reservationVO") ReservationVO _reservationVO,  HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		
		HttpSession session=request.getSession();
		reservationVO =(ReservationVO)session.getAttribute("reservationInfo");
		reservationVO.setReservation_seats(_reservationVO.getReservation_seats());
		
		
		String resProcedure= (String) session.getAttribute("resProcedure"); //예약 절차 체크 	
		if(resProcedure==null || !resProcedure.equals("4단계")){	
			return new ModelAndView("redirect:/reservation/choiceScheduleForm.do");
		}
		session.setAttribute("resProcedure","5단계");
			
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView(viewName);
		
	
		reservationVO.setReservation_adult(_reservationVO.getReservation_adult());
		reservationVO.setReservation_teenager(_reservationVO.getReservation_teenager());
		int adult = Integer.parseInt(_reservationVO.getReservation_adult());
		int teenager =Integer.parseInt(_reservationVO.getReservation_teenager());
		String price = Integer.toString((adult*8000+teenager*6500));
		reservationVO.setReservation_price(price);
		session.setAttribute("reservationInfo", reservationVO);
		
		mav.addObject("side_status", "choiceScheduleForm");
		return mav;
	}
	@RequestMapping(value= "/removeSeats.do" ,method={RequestMethod.POST})
	public ResponseEntity removeSeats(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		HttpSession session=request.getSession();
		reservationVO =(ReservationVO)session.getAttribute("reservationInfo");
		String resProcedure= (String) session.getAttribute("resProcedure"); //예약 절차 체크 	
		if(resProcedure.equals("5단계")){	
			reservationService.deleteNewSeats(reservationVO);
			session.removeAttribute("resProcedure");
			session.removeAttribute("isResOn");
			session.removeAttribute("reservationInfo");			
		}
				
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		resEntity =new ResponseEntity(0, responseHeaders, HttpStatus.OK);
		return resEntity;
	
	
	}
	//5단계
	@RequestMapping(value= "/addNewReservation.do" ,method={RequestMethod.POST})
	public ModelAndView addNewReservation(@ModelAttribute("reservationVO") ReservationVO _reservationVO,  HttpServletRequest request, HttpServletResponse response)  throws Exception{
		HttpSession session=request.getSession();
		String resProcedure= (String) session.getAttribute("resProcedure"); //예약 절차 체크 	
		if(resProcedure==null || !resProcedure.equals("5단계")){			
			return new ModelAndView("redirect:/reservation/choiceScheduleForm.do");
		}
	
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav=new ModelAndView(viewName);
		
		reservationVO =(ReservationVO)session.getAttribute("reservationInfo");
		reservationVO.setPay_method(_reservationVO.getPay_method());
		reservationVO.setCard_com_name(_reservationVO.getCard_com_name());
		reservationVO.setCard_pay_month(_reservationVO.getCard_pay_month());
		reservationVO.setPay_order_tel1(_reservationVO.getPay_order_tel1());
		reservationVO.setPay_order_tel2(_reservationVO.getPay_order_tel2());
		reservationVO.setPay_order_tel3(_reservationVO.getPay_order_tel3());
		reservationService.addReservation(reservationVO);
		
		moviesService.updateReservationRate();
	
		session.removeAttribute("resProcedure");
		session.removeAttribute("isResOn");
		session.setAttribute("reservationInfo", reservationVO);
		mav.addObject("side_status", "choiceScheduleForm");
		return mav;//결과값 으로 
	}
	
	
	
}
