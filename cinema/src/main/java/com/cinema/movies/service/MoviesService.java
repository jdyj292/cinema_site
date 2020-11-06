package com.cinema.movies.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cinema.movies.vo.MoviesVO;
import com.cinema.movies.vo.RatingVO;

public interface MoviesService {
	
	public Map<String,List<MoviesVO>> listSimpleMovies(String movies_status) throws Exception; //maincontroller
	public List<MoviesVO> listMovies(Map condmap) throws Exception;
	public List<MoviesVO> listMoviesRanking(Map condmap) throws Exception;
	public Map moviesDetail(int movies_id) throws Exception; //moviescontroller 
	public List<String> keywordSearch(String keyword) throws Exception;
	public List<MoviesVO> searchMovies(String searchWord) throws Exception;
	public void addRating(RatingVO ratingVO) throws Exception;
	public List<RatingVO> listRating(int movies_id) throws Exception;
	public String checkRating(Map rating_info) throws Exception;
	public void updateReservationRate() throws Exception;
	public void updateMoviesStatus() throws Exception;
	
}
