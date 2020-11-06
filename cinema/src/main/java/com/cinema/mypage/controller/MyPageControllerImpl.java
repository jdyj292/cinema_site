package com.cinema.mypage.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.common.base.BaseController;
import com.cinema.member.vo.MemberVO;
import com.cinema.movies.service.MoviesService;
import com.cinema.mypage.service.MyPageService;
import com.cinema.reservation.vo.ReservationVO;



@Controller("myPageController")
@RequestMapping(value="/mypage")
public class MyPageControllerImpl extends BaseController  implements MyPageController{
	@Autowired
	MyPageService myPageService;
	@Autowired
	MemberVO memberVO;
	@Autowired
	MoviesService moviesService;
	
	

	@Override
	@RequestMapping(value="/cancelMyReservation.do" ,method = RequestMethod.POST)
	public ModelAndView cancelMyReservation(@RequestParam("reservation_id")  int reservation_id,@RequestParam("reservation_seats")  String reservation_seats,@RequestParam("schedule_id") int schedule_id,
			                         HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		
		myPageService.cancelReservation(reservation_id,schedule_id,reservation_seats);
	
		mav.addObject("message", "cancel_reservation");
		mav.setViewName("redirect:/mypage/listMyReservationHistory.do");
		
		moviesService.updateReservationRate();
		return mav;
	}
	
	@Override
	@RequestMapping(value="/myReservationDetail.do" ,method = RequestMethod.GET)
	public ModelAndView myReservationDetail(@RequestParam("reservation_id")  int reservation_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
		
		String reservation_status ="상영시작전";
		//포맷
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//영화 시작 시간
		ReservationVO reservationVO=myPageService.findMyReservationInfo(reservation_id);
		String b_date = reservationVO.getSchedule_begin_date();
		Date begin_date =format.parse(b_date);
		//현재 시간
		Date time = new Date();
		String c_time = format.format(time);
		Date today =format.parse(c_time);
		//비교
		
		int result =begin_date.compareTo(today);
		if (result==1) {
			reservation_status="상영시작전";
		}else {
			reservation_status="상영시작후";
		}
		
		mav.addObject("reservation_status", reservation_status);
		mav.addObject("memberVO", memberVO);
		mav.addObject("reservationVO",reservationVO);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/listMyReservationHistory.do" ,method = RequestMethod.GET)
	public ModelAndView listMyReservationHistory(@RequestParam Map<String, String> dateMap,
			                               HttpServletRequest request, HttpServletResponse response)  throws Exception {
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		
		session.setAttribute("side_menu", "my_page"); //마이페이지 사이드 메뉴로 설정한다.
		
		String  member_id=memberVO.getMember_id();
		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
		String section = dateMap.get("section");
		String pageNum = dateMap.get("pageNum");
		String dateCondition = dateMap.get("dateCondition");
		String searchWord = dateMap.get("searchWord");
		String beginDate=null,endDate=null;		
		
	
		if(fixedSearchPeriod != "" && fixedSearchPeriod != null){			
			String [] tempDate=fixedSearchPeriod.split(",");
			beginDate=tempDate[0];			
			endDate=tempDate[1];
			}
			else {			
				String [] tempDate=calcSearchPeriod(null).split(",");
				beginDate=tempDate[0];
				endDate=tempDate[1];
			}
		
		Map<String,Object> condMap=new HashMap<String,Object>();
		if(section== null) {
			section = "1";
		}
		condMap.put("section",section);
		if(pageNum== null) {
			pageNum = "1";
		}
		condMap.put("pageNum",pageNum);
		condMap.put("section",section);
		condMap.put("beginDate",beginDate);
		condMap.put("endDate", endDate);
		condMap.put("searchWord",searchWord);
		condMap.put("member_id",member_id);
		List<ReservationVO> myReservationtList=myPageService.listMyReservationHistory(condMap);
		mav.addObject("myReservationtList", myReservationtList);
		
		int i_section =Integer.parseInt(section);
		
		int total=myPageService.listMyReservationHistorySize(condMap);
		int total_section =(total/100)+1; 
		int start_page = 1;
		int end_page = 10;
		if(i_section==total_section) { 		
			int a = (total%100);
			if(a%10 == 0) {
				end_page = a/10;
			}else {
				end_page =a/10+1;
			}
		}
		
		String beginDate1[]=beginDate.split("-"); 
		String endDate2[]=endDate.split("-");
		mav.addObject("beginYear",beginDate1[0]);
		mav.addObject("beginMonth",beginDate1[1]);
		mav.addObject("beginDay",beginDate1[2]);
		mav.addObject("endYear",endDate2[0]);
		mav.addObject("endMonth",endDate2[1]);
		mav.addObject("endDay",endDate2[2]);
		
		mav.addObject("section", section);
		mav.addObject("pageNum", pageNum);
		mav.addObject("dateCondition", dateCondition);
		mav.addObject("searchWord", searchWord);
		
		mav.addObject("fixedSearchPeriod",fixedSearchPeriod);
		mav.addObject("startPage", start_page);
		mav.addObject("endPage", end_page);
		mav.addObject("side_status", "listMyReservationHistory");
		return mav;
	}	
	
	@Override
	@RequestMapping(value="/myDetailInfo.do" ,method = RequestMethod.GET)
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("side_status", "myDetailInfo");
		return mav;
	}	
	
	@Override
	@RequestMapping(value="/modifyMyInfo.do" ,method = RequestMethod.POST)
	public ResponseEntity modifyMyInfo(@RequestParam("attribute")  String attribute,
			                 @RequestParam("value")  String value,
			               HttpServletRequest request, HttpServletResponse response)  throws Exception {
		Map<String,String> memberMap=new HashMap<String,String>();
		String val[]=null;
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String  member_id=memberVO.getMember_id();
		if(attribute.equals("member_birth")){
			val=value.split(",");
			memberMap.put("member_birth_y",val[0]);
			memberMap.put("member_birth_m",val[1]);
			memberMap.put("member_birth_d",val[2]);
			memberMap.put("member_birth_gn",val[3]);
		}else if(attribute.equals("tel")){
			val=value.split(",");
			memberMap.put("tel1",val[0]);
			memberMap.put("tel2",val[1]);
			memberMap.put("tel3",val[2]);
		}else if(attribute.equals("hp")){
			val=value.split(",");
			memberMap.put("hp1",val[0]);
			memberMap.put("hp2",val[1]);
			memberMap.put("hp3",val[2]);
			memberMap.put("smssts_yn", val[3]);
		}else if(attribute.equals("email")){
			val=value.split(",");
			memberMap.put("email1",val[0]);
			memberMap.put("email2",val[1]);
			memberMap.put("emailsts_yn", val[2]);
		}else if(attribute.equals("address")){
			val=value.split(",");
			memberMap.put("zipcode",val[0]);
			memberMap.put("roadAddress",val[1]);
			memberMap.put("jibunAddress", val[2]);
			memberMap.put("namujiAddress", val[3]);
		}else {
			memberMap.put(attribute,value);	
		}
		
		memberMap.put("member_id", member_id);
		
		
		memberVO=(MemberVO)myPageService.modifyMyInfo(memberMap);
		session.removeAttribute("memberInfo");
		session.setAttribute("memberInfo", memberVO);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message  = "mod_success";
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}	
	

	
}
