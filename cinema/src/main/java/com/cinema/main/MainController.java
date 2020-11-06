package com.cinema.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.common.base.BaseController;
import com.cinema.movies.service.MoviesService;
import com.cinema.movies.vo.MoviesVO;

@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController extends BaseController{
	@Autowired
	private MoviesService moviesService;
	

	@RequestMapping(value= "/main/main.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		session=request.getSession();
		session.setAttribute("side_menu", "base_page");
		
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		Map<String,List<MoviesVO>> moviesMap=moviesService.listSimpleMovies("»ó¿µÁß");
		mav.addObject("moviesMap", moviesMap);
		return mav;
	}
}
