package com.cinema.movies.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.common.base.BaseController;
import com.cinema.member.vo.MemberVO;
import com.cinema.movies.service.MoviesService;
import com.cinema.movies.vo.ImageFileVO;
import com.cinema.movies.vo.MoviesVO;
import com.cinema.movies.vo.RatingVO;

import net.sf.json.JSONObject;


@EnableScheduling
@Controller("moviesController")
@RequestMapping(value="/movies")
public class MoviesControllerImpl extends BaseController   implements MoviesController {
	@Autowired
	MoviesService moviesService;
	@Autowired
	MemberVO memberVO;
	
	@RequestMapping(value= "moviesList" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView moviesList(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		String movies_status = dateMap.get("movies_status");

		Map<String,Object> condMap=new HashMap<String,Object>();
	
		condMap.put("movies_status",movies_status);

		List<MoviesVO> newMoviesList=moviesService.listMovies(condMap);
		mav.addObject("newMoviesList", newMoviesList);	
		mav.addObject("movies_status", movies_status);
		mav.addObject("side_status", "moviesList");

		return mav;
	}
	
	@RequestMapping(value= "moviesRanking" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView moviesRanking(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response) throws Exception{				
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		String movies_status = dateMap.get("movies_status");
		if(movies_status==null) {
			movies_status="상영중";
		}

		Map<String,Object> condMap=new HashMap<String,Object>();
	
		condMap.put("movies_status",movies_status);

		List<MoviesVO> newMoviesList=moviesService.listMoviesRanking(condMap);
		mav.addObject("newMoviesList", newMoviesList);	
		mav.addObject("side_status", "moviesRanking");

		return mav;
	}
	
	@RequestMapping(value="/moviesDetail.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView moviesDetail(@RequestParam("movies_id") int movies_id,
			                       HttpServletRequest request, HttpServletResponse response) throws Exception {

		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		Map moviesMap=moviesService.moviesDetail(movies_id);
		List ratingList =moviesService.listRating(movies_id);
		
		List<ImageFileVO> imageList = (List)moviesMap.get("imageList");
		String imageNames = "";
		for(ImageFileVO temp : imageList){
			imageNames= imageNames+temp.getFileName()+" ";
			}
		imageNames=imageNames.trim();
		
		mav.addObject("moviesMap", moviesMap);
		mav.addObject("ratingList",ratingList);
		mav.addObject("imageNames",imageNames);
		return mav;
	}
	
	@RequestMapping(value="/keywordSearch.do",method = RequestMethod.GET,produces = "application/text; charset=utf8")
	public @ResponseBody String  keywordSearch(@RequestParam("keyword") String keyword,
			                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//System.out.println(keyword);
		if(keyword == null || keyword.equals(""))
		   return null ;
	
		keyword = keyword.toUpperCase();
	    List<String> keywordList =moviesService.keywordSearch(keyword);
	    
	 // ���� �ϼ��� JSONObject ����(��ü)
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keywordList);
		 		
	    String jsonInfo = jsonObject.toString();
	   // System.out.println(jsonInfo);
	    return jsonInfo ;
	}
	
	@RequestMapping(value="/searchMovies.do" ,method = RequestMethod.GET)
	public ModelAndView searchMovies(@RequestParam("searchWord") String searchWord,
			                       HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName=(String)request.getAttribute("viewName");
		List<MoviesVO> moviesList=moviesService.searchMovies(searchWord);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("moviesList", moviesList);
		return mav;
		
	}
	
	@RequestMapping(value="/addRatingForm.do" ,method = RequestMethod.GET)
	public ModelAndView addRatingForm(@RequestParam("movies_title") String movies_title,@RequestParam("movies_id") int movies_id,
			                       HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("movies_title", movies_title);
		mav.addObject("movies_id", movies_id);
		return mav;
	}
	
	@RequestMapping(value="/addRating.do" ,method = RequestMethod.POST)
	public ResponseEntity addRating(@RequestParam("movies_title") String movies_title,@ModelAttribute("ratingVO") RatingVO ratingVO,
			                       HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		ratingVO.setMember_id(memberVO.getMember_id());
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
		    moviesService.addRating(ratingVO);
		  
		    message  = "<script>";
		    message +=" alert('등록을 마쳤습니다.');";
		    message += " location.href='"+request.getContextPath()+"/movies/moviesDetail.do?movies_id="+ratingVO.getMovies_id()+"';";
		    message += " </script>";
		    
		}catch(Exception e) {
			message  = "<script>";
		    message +=" alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
		    message += " location.href='"+request.getContextPath()+"/movies/addRatingForm.do?movies_id="+ratingVO.getMovies_id()+"&movies_title="+movies_title+"';";
		    message += " </script>";
			e.printStackTrace();
		}
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	@RequestMapping(value="/checkrating.do" ,method = RequestMethod.POST)
	public ResponseEntity checkRating(@RequestParam("movies_id") int movies_id ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn"); //로그인 체크 
		String action=(String)session.getAttribute("action");		
		if(isLogOn==null || isLogOn==false){			
			session.setAttribute("action", "/movies/moviesDetail.do");
			session.setAttribute("action_movies_id", movies_id);
			String result = "needlogin";
			ResponseEntity resEntity =new ResponseEntity(result, HttpStatus.OK);
			return resEntity;
		}else {
			if(action!=null && action.equals("/movies/moviesDetail.do"))
				session.removeAttribute("action");
			    session.removeAttribute("action_movies_id");
		}
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id =memberVO.getMember_id();
		Map rating_info = new HashMap();
		rating_info.put("member_id", member_id);
		rating_info.put("movies_id", movies_id);
		String result = moviesService.checkRating(rating_info);
		if(result == null) {
			result = "0";
		}
		ResponseEntity resEntity =new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
		
	}
	
	
	
	  @Scheduled(cron= "0 0/5 * * * *")
	    public void MovieStatusScheduler(){
	        System.out.println("영화 상태 변경");
	        
	        try {
	        	 moviesService.updateMoviesStatus();
			  
			}catch(Exception e) {
				e.printStackTrace();
			}
	        
	        
	    }
	
}
