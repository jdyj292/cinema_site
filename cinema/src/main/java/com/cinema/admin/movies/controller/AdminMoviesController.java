package com.cinema.admin.movies.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface AdminMoviesController {
	public ModelAndView adminMoviesMain(@RequestParam Map<String, String> dateMap,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ResponseEntity addNewMovies(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception;
	public ResponseEntity modifyMoviesInfo( @RequestParam("movies_id") String movies_id,
                                 @RequestParam("mod_type") String mod_type,
                                 @RequestParam("value") String value,
			                     HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public void  removeMoviesImage(@RequestParam("movies_id") int movies_id,
            @RequestParam("image_id") int image_id,
            @RequestParam("imageFileName") String imageFileName,
            HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public void  addNewMoviesImage(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception;
	public void modifyMoviesImageInfo(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception;
}
