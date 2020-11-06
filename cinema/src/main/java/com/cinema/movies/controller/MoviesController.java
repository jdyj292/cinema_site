package com.cinema.movies.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface MoviesController  {
	public ModelAndView moviesDetail(@RequestParam("movies_id") int movies_id,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public @ResponseBody String  keywordSearch(@RequestParam("keyword") String keyword,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchMovies(@RequestParam("searchWord") String searchWord, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
