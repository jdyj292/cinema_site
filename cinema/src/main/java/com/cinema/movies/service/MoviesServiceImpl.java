package com.cinema.movies.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cinema.movies.dao.MoviesDAO;
import com.cinema.movies.vo.MoviesVO;
import com.cinema.movies.vo.RatingVO;
import com.cinema.movies.vo.ImageFileVO;

@Service("moviesService")
@Transactional(propagation=Propagation.REQUIRED)
public class MoviesServiceImpl implements MoviesService{
	@Autowired
	MoviesDAO moviesDAO;
	
	public Map<String,List<MoviesVO>> listSimpleMovies(String movies_status) throws Exception {
		Map<String,List<MoviesVO>> moviesMap=new HashMap<String,List<MoviesVO>>();
		List<MoviesVO> moviesList=moviesDAO.selectMoviesSimpleList(movies_status);
		moviesMap.put("onscreen",moviesList);	
		return moviesMap;
	}
	
	public List<MoviesVO> listMovies(Map condmap) throws Exception {
		Map<String,List<MoviesVO>> moviesMap=new HashMap<String,List<MoviesVO>>();
		List<MoviesVO> moviesList=moviesDAO.selectMoviesList(condmap);
						
		return moviesList;
	}
	
	
	
	public List<MoviesVO> listMoviesRanking(Map condmap) throws Exception {
		Map<String,List<MoviesVO>> moviesMap=new HashMap<String,List<MoviesVO>>();
		List<MoviesVO> moviesList=moviesDAO.selectMoviesRankingList(condmap);
						
		return moviesList;
	}
	
	public Map moviesDetail(int movies_id) throws Exception {
		Map moviesMap=new HashMap();
		MoviesVO moviesVO = moviesDAO.selectMoviesDetail(movies_id);
		moviesMap.put("moviesVO", moviesVO);
		List<ImageFileVO> imageList =moviesDAO.selectMoviesDetailImage(movies_id);
		moviesMap.put("imageList", imageList);
		return moviesMap;
	}
	
	public List<String> keywordSearch(String keyword) throws Exception {
		List<String> list=moviesDAO.selectKeywordSearch(keyword);
		return list;
	}
	
	public List<MoviesVO> searchMovies(String searchWord) throws Exception{
		List moviesList=moviesDAO.selectMoviesBySearchWord(searchWord);
		return moviesList;
	}
	
	public void addRating(RatingVO ratingVO) throws Exception{
		moviesDAO.insertNewRating(ratingVO);		
		
		MoviesVO moviesVO = moviesDAO.selectAverageRating(ratingVO);
		moviesDAO.updateMoviesRating(moviesVO);
	}
	
	public List<RatingVO> listRating(int movies_id) throws Exception{
		List ratingList=moviesDAO.selectRatingList(movies_id);
		return ratingList;
	}
	
	public String checkRating(Map rating_info) throws Exception{
		String result = moviesDAO.selectCheckRating(rating_info);
		return result;
	}
	
	public void updateReservationRate() throws Exception{
			
	    moviesDAO.updateReservationRate();	
	    moviesDAO.updateReservationNullRate();
	}
	
	public void updateMoviesStatus() throws Exception{
		
			moviesDAO.updateToBeRealesed("개봉예정");
			moviesDAO.updateOnScreen("상영중");
			moviesDAO.updateScreeningOver("상영종료");
		
	}
	

	
	  
}


	

