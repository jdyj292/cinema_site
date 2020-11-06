package com.cinema.admin.movies.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cinema.admin.movies.dao.AdminMoviesDAO;
import com.cinema.movies.vo.MoviesVO;
import com.cinema.movies.vo.ImageFileVO;



@Service("adminMoviesService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminMoviesServiceImpl implements AdminMoviesService {
	@Autowired
	AdminMoviesDAO adminMoviesDAO;
	
	@Override
	public int addNewMovies(Map newMoviesMap) throws Exception{
		int movies_id = adminMoviesDAO.insertNewMovies(newMoviesMap);
		ArrayList<ImageFileVO> imageFileList = (ArrayList)newMoviesMap.get("imageFileList");
		for(ImageFileVO imageFileVO : imageFileList) {
			imageFileVO.setMovies_id(movies_id);
		}
		adminMoviesDAO.insertMoviesImageFile(imageFileList);
		return movies_id;
	}
	
	@Override
	public List<MoviesVO> listNewMovies(Map condMap) throws Exception{
		return adminMoviesDAO.selectNewMoviesList(condMap);
	}
	@Override	
	public int listNewMoviesSize(Map condMap) throws Exception{
		return adminMoviesDAO.selectNewMoviesListSize(condMap);
	}
	
	public List<MoviesVO> searchAdminMovies(String searchWord) throws Exception{
		List moviesList=adminMoviesDAO.selectAdminMoviesBySearchWord(searchWord);
		return moviesList;
	}
	
	@Override
	public Map moviesDetail(int movies_id) throws Exception {
		Map moviesMap = new HashMap();
		MoviesVO moviesVO=adminMoviesDAO.selectMoviesDetail(movies_id);
		List imageFileList =adminMoviesDAO.selectMoviesImageFileList(movies_id);
		moviesMap.put("movies", moviesVO);
		moviesMap.put("imageFileList", imageFileList);
		return moviesMap;
	}
	@Override
	public List moviesImageFile(int movies_id) throws Exception{
		List imageList =adminMoviesDAO.selectMoviesImageFileList(movies_id);
		return imageList;
	}
	
	@Override
	public void modifyMoviesInfo(Map moviesMap) throws Exception{
		adminMoviesDAO.updateMoviesInfo(moviesMap);
		
	}	
	@Override
	public void modifyMoviesImage(List<ImageFileVO> imageFileList) throws Exception{
		adminMoviesDAO.updateMoviesImage(imageFileList); 
	}
		
	
	@Override
	public void removeMoviesImage(int image_id) throws Exception{
		adminMoviesDAO.deleteMoviesImage(image_id);
	}
	
	@Override
	public void addNewMoviesImage(List imageFileList) throws Exception{
		adminMoviesDAO.insertMoviesImageFile(imageFileList);
	}
	

	
}
