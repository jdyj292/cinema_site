package com.cinema.admin.movies.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.cinema.movies.vo.MoviesVO;
import com.cinema.movies.vo.ImageFileVO;


public interface AdminMoviesDAO {
	public int insertNewMovies(Map newMoviesMap) throws DataAccessException;
	public List<MoviesVO> selectNewMoviesList(Map condMap) throws DataAccessException;
	public int selectNewMoviesListSize(Map condMap) throws DataAccessException;
	public List<MoviesVO> selectAdminMoviesBySearchWord(String searchWord) throws DataAccessException;
	public MoviesVO selectMoviesDetail(int movies_id) throws DataAccessException;
	public List selectMoviesImageFileList(int movies_id) throws DataAccessException;	
	public void insertMoviesImageFile(List fileList)  throws DataAccessException;
	public void updateMoviesInfo(Map moviesMap) throws DataAccessException;
	public void updateMoviesImage(List<ImageFileVO> imageFileList) throws DataAccessException;
	public void deleteMoviesImage(int image_id) throws DataAccessException;
	public void deleteMoviesImage(List fileList) throws DataAccessException;

	
}
