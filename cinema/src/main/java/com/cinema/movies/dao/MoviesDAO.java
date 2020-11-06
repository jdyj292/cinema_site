package com.cinema.movies.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.cinema.movies.vo.MoviesVO;
import com.cinema.movies.vo.RatingVO;
import com.cinema.admin.schedule.vo.ScheduleVO;
import com.cinema.movies.vo.ImageFileVO;

public interface MoviesDAO {
	public List<MoviesVO> selectMoviesSimpleList(String moviesStatus ) throws DataAccessException;
	public List<MoviesVO> selectMoviesList(Map condmap ) throws DataAccessException;
	public List<MoviesVO> selectMoviesRankingList(Map condmap ) throws DataAccessException;
	public MoviesVO selectMoviesDetail(int movies_id) throws DataAccessException;
	public List<ImageFileVO> selectMoviesDetailImage(int movies_id) throws DataAccessException;
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException;
	public List<MoviesVO> selectMoviesBySearchWord(String searchWord) throws DataAccessException;
	public void insertNewRating(RatingVO ratingVO) throws DataAccessException;
	public MoviesVO selectAverageRating(RatingVO ratingVO) throws DataAccessException;
	public void updateMoviesRating(MoviesVO moviesVO) throws DataAccessException;
	public List<RatingVO> selectRatingList(int movies_id) throws DataAccessException;
	public String selectCheckRating(Map rating_info) throws DataAccessException;	
	public void updateReservationRate() throws DataAccessException;
	public void updateReservationNullRate() throws DataAccessException;
	public void updateToBeRealesed(String movies_status) throws DataAccessException;
	public void updateOnScreen(String movies_status) throws DataAccessException;
	public void updateScreeningOver(String movies_status) throws DataAccessException;
	
	
	

}
