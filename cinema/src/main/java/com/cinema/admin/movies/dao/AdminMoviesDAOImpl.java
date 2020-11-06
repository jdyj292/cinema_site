package com.cinema.admin.movies.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cinema.movies.vo.MoviesVO;
import com.cinema.movies.vo.ImageFileVO;

@Repository("adminMoviesDAO")
public class AdminMoviesDAOImpl implements AdminMoviesDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertNewMovies(Map newMoviesMap) throws DataAccessException {
		sqlSession.insert("mapper.admin.movies.insertNewMovies", newMoviesMap);
		return Integer.parseInt((String) newMoviesMap.get("movies_id"));
	}

	@Override
	public List<MoviesVO> selectNewMoviesList(Map condMap) throws DataAccessException {
		ArrayList<MoviesVO> moviesList = (ArrayList) sqlSession.selectList("mapper.admin.movies.selectNewMoviesList",
				condMap);
		return moviesList;
	}
	@Override
	public int selectNewMoviesListSize(Map condMap) throws DataAccessException{
		int moviesListSize = sqlSession.selectOne("mapper.admin.movies.selectNewMoviesListSize",
				condMap);
		return moviesListSize;
	}
	
	@Override
	public List<MoviesVO> selectAdminMoviesBySearchWord(String searchWord) throws DataAccessException{
		ArrayList list=(ArrayList)sqlSession.selectList("mapper.admin.movies.selectAdminMoviesBySearchWord",searchWord);
		return list;
		
	}

	@Override
	public MoviesVO selectMoviesDetail(int movies_id) throws DataAccessException {
		MoviesVO moviesBean = new MoviesVO();
		moviesBean = (MoviesVO) sqlSession.selectOne("mapper.admin.movies.selectMoviesDetail", movies_id);
		return moviesBean;
	}

	@Override
	public List selectMoviesImageFileList(int movies_id) throws DataAccessException {
		List imageList = new ArrayList();
		imageList = (List) sqlSession.selectList("mapper.admin.movies.selectMoviesImageFileList", movies_id);
		return imageList;
	}
	
	@Override
	public void insertMoviesImageFile(List fileList) throws DataAccessException {
		for (int i = 0; i < fileList.size(); i++) {
			ImageFileVO imageFileVO = (ImageFileVO) fileList.get(i);
			sqlSession.insert("mapper.admin.movies.insertMoviesImageFile", imageFileVO);
		}
	}

	@Override
	public void updateMoviesInfo(Map moviesMap) throws DataAccessException {
		sqlSession.update("mapper.admin.movies.updateMoviesInfo", moviesMap);
	}

	@Override
	public void deleteMoviesImage(int image_id) throws DataAccessException {
		sqlSession.delete("mapper.admin.movies.deleteMoviesImage", image_id);
	}

	@Override
	public void deleteMoviesImage(List fileList) throws DataAccessException {
		int image_id;
		for (int i = 0; i < fileList.size(); i++) {
			ImageFileVO bean = (ImageFileVO) fileList.get(i);
			image_id = bean.getImage_id();
			sqlSession.delete("mapper.admin.movies.deleteMoviesImage", image_id);
		}
	}

	@Override
	public void updateMoviesImage(List<ImageFileVO> imageFileList) throws DataAccessException {

		for (int i = 0; i < imageFileList.size(); i++) {
			ImageFileVO imageFileVO = imageFileList.get(i);
			sqlSession.update("mapper.admin.movies.updateMoviesImage", imageFileVO);
		}

	}

}
