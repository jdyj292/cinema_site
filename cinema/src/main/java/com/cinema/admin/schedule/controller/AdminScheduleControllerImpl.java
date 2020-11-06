package com.cinema.admin.schedule.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;


import com.cinema.admin.schedule.service.AdminScheduleService;
import com.cinema.admin.schedule.vo.ScheduleVO;
import com.cinema.common.base.BaseController;


@Controller("adminScheduleController")
@RequestMapping(value="/admin/schedule")
public class AdminScheduleControllerImpl extends BaseController  implements AdminScheduleController {
	@Autowired
	AdminScheduleService adminScheduleService;
	
	@RequestMapping(value="/adminScheduleMain.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView adminScheduleMain(@RequestParam Map<String, String> dateMap,HttpServletRequest request, HttpServletResponse response)  throws Exception{
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
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
		List<ScheduleVO> newScheduleList=adminScheduleService.listNewSchedule(condMap);
		mav.addObject("newScheduleList", newScheduleList);
		
		int i_section =Integer.parseInt(section);
		
		int total=adminScheduleService.listNewScheduleSize(condMap);
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
		
		mav.addObject("fixedSearchPeriod",fixedSearchPeriod);
		mav.addObject("startPage", start_page);
		mav.addObject("endPage", end_page);
		mav.addObject("side_status", "adminScheduleMain");
		return mav;

	}
	@RequestMapping(value="/addNewSchedule.do" ,method={RequestMethod.POST})
	public ResponseEntity addNewSchedule(@ModelAttribute("schedule") ScheduleVO schedule, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
	
	
		
		try {
		    adminScheduleService.addNewSchedule(schedule);
		    message  = "<script>";
		    message +=" alert('새 상영계획을 추가 했습니다.');";
		    message +=" location.href='"+request.getContextPath()+"/admin/schedule/addNewScheduleForm.do';";
		    message += " </script>";
		    
		}catch(Exception e) {
			message  = "<script>";
		    message +=" alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
		    message +=" location.href='"+request.getContextPath()+"/admin/schedule/addNewScheduleForm.do';";
		    message += " </script>";
			e.printStackTrace();
		}
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
		
		
	}
	
	@RequestMapping(value="/modifyScheduleForm.do" ,method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView modifyScheduleForm(@RequestParam("schedule_id") int schedule_id,
			                            HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		ScheduleVO scheduleVO=adminScheduleService.scheduleDetail(schedule_id);
		mav.addObject("scheduleVO",scheduleVO);
		
		return mav;
	}
	
	@RequestMapping(value="/modifyScheduleInfo.do" ,method={RequestMethod.POST})
	public ResponseEntity modifyScheduleInfo( @RequestParam("schedule_id") String schedule_id,
                                 @RequestParam("attribute") String attribute,
                                 @RequestParam("value") String value,
			                     HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		Map<String,String> scheduleMap=new HashMap<String,String>();
		scheduleMap.put("schedule_id", schedule_id);
		scheduleMap.put(attribute, value); 
		adminScheduleService.modifyScheduleInfo(scheduleMap);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message  = "mod_success";
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
		
	}
	
}
