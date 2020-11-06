package com.cinema.movies.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cinema.movies.vo.MoviesVO;
import com.cinema.movies.vo.RatingVO;
import com.cinema.movies.vo.ImageFileVO;

@Repository("moviesDAO")
public class MoviesDAOImpl  implements MoviesDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<MoviesVO> selectMoviesSimpleList(String moviesStatus ) throws DataAccessException {
		List<MoviesVO> moviesList=(ArrayList)sqlSession.selectList("mapper.movies.selectMoviesSimpleList",moviesStatus);
	   return moviesList;	
     
	}
	
	@Override
	public List<MoviesVO> selectMoviesList(Map condMap ) throws DataAccessException {
		List<MoviesVO> moviesList=(ArrayList)sqlSession.selectList("mapper.movies.selectMoviesList",condMap);
	   return moviesList;	
     
	}
	
	@Override
	public List<MoviesVO> selectMoviesRankingList(Map condMap ) throws DataAccessException {
		List<MoviesVO> moviesList=(ArrayList)sqlSession.selectList("mapper.movies.selectMoviesRankingList",condMap);
	   return moviesList;	
     
	}

	
	@Override
	public MoviesVO selectMoviesDetail(int movies_id) throws DataAccessException{
		MoviesVO moviesVO=(MoviesVO)sqlSession.selectOne("mapper.movies.selectMoviesDetail",movies_id);
		return moviesVO;
	}
	
	@Override
	public List<ImageFileVO> selectMoviesDetailImage(int movies_id) throws DataAccessException{
		List<ImageFileVO> imageList=(ArrayList)sqlSession.selectList("mapper.movies.selectMoviesDetailImage",movies_id);
		return imageList;
	}
	
	@Override
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException {
	   List<String> list=(ArrayList)sqlSession.selectList("mapper.movies.selectKeywordSearch",keyword);
	   return list;
	}
	
	@Override
	public ArrayList selectMoviesBySearchWord(String searchWord) throws DataAccessException{
		ArrayList list=(ArrayList)sqlSession.selectList("mapper.movies.selectMoviesBySearchWord",searchWord);
		 return list;
	}
	
	@Override
	public void insertNewRating(RatingVO ratingVO) throws DataAccessException{
		sqlSession.insert("mapper.movies.insertNewRating",ratingVO);
	}
	
	@Override
	public MoviesVO selectAverageRating(RatingVO ratingVO) throws DataAccessException{
		MoviesVO moviesVO=(MoviesVO)sqlSession.selectOne("mapper.movies.selectAverageRating",ratingVO);
		return moviesVO;	
	}
	
	@Override
	public void updateMoviesRating(MoviesVO moviesVO) throws DataAccessException{
		sqlSession.update("mapper.movies.updateAverageRating",moviesVO);
	}
	
	@Override
	public List<RatingVO> selectRatingList(int movies_id) throws DataAccessException {
		List<RatingVO> ratingList=(ArrayList)sqlSession.selectList("mapper.movies.selectRatingList",movies_id);
	   return ratingList;	
     
	}
	
	@Override
	public String selectCheckRating(Map rating_info) throws DataAccessException{
		String result =  sqlSession.selectOne("mapper.movies.selectCheckRating",rating_info);
		return result;
	}
	
	@Override
	public void updateReservationRate() throws DataAccessException{
		sqlSession.update("mapper.movies.updateReservationRate");
	}
	@Override
	public void updateReservationNullRate() throws DataAccessException{
		sqlSession.update("mapper.movies.updateReservationNullRate");
	}
	@Override
	public void updateToBeRealesed(String movies_status)throws DataAccessException{
		
		sqlSession.update("mapper.movies.updateToBeRealesed",movies_status);
	}
	
	@Override
	public void updateOnScreen(String movies_status)throws DataAccessException{
		
		sqlSession.update("mapper.movies.updateOnScreen",movies_status);
	}
	
	@Override
	public void updateScreeningOver(String movies_status) throws DataAccessException{
		
		sqlSession.update("mapper.movies.updateScreeningOver",movies_status);
		
	}
	
}
