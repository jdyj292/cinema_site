package com.cinema.admin.movies.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.movies.vo.MoviesVO;
import com.cinema.movies.vo.ImageFileVO;

public interface AdminMoviesService {
	public int  addNewMovies(Map newMoviesMap) throws Exception;
	public List<MoviesVO> listNewMovies(Map condMap) throws Exception;
	public int listNewMoviesSize(Map condMap) throws Exception;
	public List<MoviesVO> searchAdminMovies(String searchWord) throws Exception;
	public Map moviesDetail(int movies_id) throws Exception;
	public List moviesImageFile(int movies_id) throws Exception;
	public void modifyMoviesInfo(Map moviesMap) throws Exception;
	public void modifyMoviesImage(List<ImageFileVO> imageFileList) throws Exception;
	public void removeMoviesImage(int image_id) throws Exception;
	public void addNewMoviesImage(List imageFileList) throws Exception;
	
}
