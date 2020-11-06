package com.cinema.admin.movies.controller;

import java.io.File;
import java.io.PrintWriter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.admin.movies.service.AdminMoviesService;
import com.cinema.common.base.BaseController;
import com.cinema.movies.vo.MoviesVO;
import com.cinema.movies.vo.ImageFileVO;
import com.cinema.member.vo.MemberVO;

@Controller("adminMoviesController")
@RequestMapping(value="/admin/movies")
public class AdminMoviesControllerImpl extends BaseController  implements AdminMoviesController{
	private static final String CURR_IMAGE_REPO_PATH = "C:\\Cinema\\file_repo";
	@Autowired
	AdminMoviesService adminMoviesService;
	
	@RequestMapping(value="/adminMoviesMain.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView adminMoviesMain(@RequestParam Map<String, String> dateMap,
			                           HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		session=request.getSession();
		session.setAttribute("side_menu", "admin_mode"); //���������� ���̵� �޴��� �����Ѵ�.
		
		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
		String section = dateMap.get("section");
		String pageNum = dateMap.get("pageNum");
		String dateCondition = dateMap.get("dateCondition");
		String searchWord = dateMap.get("searchWord");
		String beginDate=null,endDate=null;
		
		if(fixedSearchPeriod != "" && fixedSearchPeriod != null){
		String [] tempDate=fixedSearchPeriod.split(",");
		beginDate=tempDate[0];
		endDate=tempDate[1];
		}
		else {
			String [] tempDate=calcSearchPeriod(null).split(",");
			beginDate=tempDate[0];
			endDate=tempDate[1];
		}
		
		Map<String,Object> condMap=new HashMap<String,Object>();
		if(section== null) {
			section = "1";
		}
		if(pageNum== null) {
			pageNum = "1";
		}
		if(dateCondition== null) {
			dateCondition = "all";
		}
		condMap.put("pageNum",pageNum);
		condMap.put("section",section);
		condMap.put("beginDate",beginDate);
		condMap.put("endDate", endDate);
		condMap.put("searchWord",searchWord);
		List<MoviesVO> newMoviesList=adminMoviesService.listNewMovies(condMap);
		mav.addObject("newMoviesList", newMoviesList);
		
		int i_section =Integer.parseInt(section);
		
		int total=adminMoviesService.listNewMoviesSize(condMap);
		int total_section =(total/100)+1; 
		int start_page = 1;
		int end_page = 10;
		if(i_section==total_section) { 		
			int a = (total%100);
			if(a%10 == 0) {
				end_page = a/10;
			}else {
				end_page =a/10+1;
			}
		}
		
		String beginDate1[]=beginDate.split("-");
		String endDate2[]=endDate.split("-");
		mav.addObject("beginYear",beginDate1[0]);
		mav.addObject("beginMonth",beginDate1[1]);
		mav.addObject("beginDay",beginDate1[2]);
		mav.addObject("endYear",endDate2[0]);
		mav.addObject("endMonth",endDate2[1]);
		mav.addObject("endDay",endDate2[2]);
		
		mav.addObject("section", section);
		mav.addObject("pageNum", pageNum);
		mav.addObject("dateCondition", dateCondition);
		mav.addObject("searchWord", searchWord);
		
		mav.addObject("fixedSearchPeriod",fixedSearchPeriod);
		mav.addObject("startPage", start_page);
		mav.addObject("endPage", end_page);
		mav.addObject("side_status", "adminMoviesMain");

		return mav;
		
	}
	
	
	

	
	@RequestMapping(value="/addNewMovies.do" ,method={RequestMethod.POST})
	public ResponseEntity addNewMovies(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String imageFileName=null;
		
		Map newMoviesMap = new HashMap();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){ //multipartRequest �� ��ȭ������ ����
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			newMoviesMap.put(name,value);//��ȭ ������ ����
		}
	
		
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo"); //��� ������ ����
		//String reg_id = memberVO.getMember_id();
		String reg_id = "admin";
		
		List<ImageFileVO> imageFileList =upload(multipartRequest); //�̹��� ���ε� 
		if(imageFileList!= null && imageFileList.size()!=0) {
			for(ImageFileVO imageFileVO : imageFileList) { //�����ʰ��� ���� ���ʿ� �����ؼ� �ݺ� 
				imageFileVO.setReg_id(reg_id); //������ imageFileVO �� ����� ���̵� ��� 
			}
			newMoviesMap.put("imageFileList", imageFileList); //imageFileVO�� ����ִ� �̹��� ���� ����Ʈ�� ����
		}
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int movies_id = adminMoviesService.addNewMovies(newMoviesMap); //��ȭ ������ �̹��� ���� ����Ʈ�� ������ ���̽��� �߰� 
			if(imageFileList!=null && imageFileList.size()!=0) {
				for(ImageFileVO  imageFileVO:imageFileList) { 
					imageFileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+imageFileName);
					File destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+movies_id);
					FileUtils.moveFileToDirectory(srcFile, destDir,true); //�ӽ��������� �̵� 
				}
			}
			message= "<script>";
			message += " alert('����ǰ�� �߰��߽��ϴ�.');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/admin/movies/addNewMoviesForm.do';";
			message +=("</script>");
		}catch(Exception e) {
			if(imageFileList!=null && imageFileList.size()!=0) {
				for(ImageFileVO  imageFileVO:imageFileList) {
					imageFileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+imageFileName);
					srcFile.delete(); //���� �߻��� �ӽ����� ���� 
				}
			}
			
			message= "<script>";
			message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/admin/movies/addNewMoviesForm.do';";
			message +=("</script>");
			e.printStackTrace();
		}
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	@RequestMapping(value="/modifyMoviesForm.do" ,method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView modifyMoviesForm(@RequestParam("movies_id") int movies_id,
			                            HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		Map moviesMap=adminMoviesService.moviesDetail(movies_id);
		mav.addObject("moviesMap",moviesMap);
		
		return mav;
	}
	
	@RequestMapping(value="/modifyMoviesInfo.do" ,method={RequestMethod.POST})
	public ResponseEntity modifyMoviesInfo( @RequestParam("movies_id") String movies_id,
			                     @RequestParam("attribute") String attribute,
			                     @RequestParam("value") String value,
			HttpServletRequest request, HttpServletResponse response)  throws Exception {
		
		
		Map<String,String> moviesMap=new HashMap<String,String>();
		moviesMap.put("movies_id", movies_id);
		moviesMap.put(attribute, value); //�Ӽ��� ��
		adminMoviesService.modifyMoviesInfo(moviesMap);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message  = "mod_success";
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	

	@RequestMapping(value="/modifyMoviesImageInfo.do" ,method={RequestMethod.POST})
	public void modifyMoviesImageInfo(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception {
	
		multipartRequest.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String imageFileName=null;
		
		Map moviesMap = new HashMap();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			moviesMap.put(name,value); //
		}
		
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		String reg_id = memberVO.getMember_id();
		
		List<ImageFileVO> imageFileList=null;
		int movies_id=0;
		int image_id=0;
		try {
			imageFileList =upload(multipartRequest);
			if(imageFileList!= null && imageFileList.size()!=0) {
				for(ImageFileVO imageFileVO : imageFileList) { //upload ������ ����Ÿ�԰� �����̸��� �����ֱ� ������ ���� 
					movies_id = Integer.parseInt((String)moviesMap.get("movies_id"));
					image_id = Integer.parseInt((String)moviesMap.get("image_id")); //�����̱� ������ 
					imageFileVO.setMovies_id(movies_id);
					imageFileVO.setImage_id(image_id);
					imageFileVO.setReg_id(reg_id);
				}
				
			    adminMoviesService.modifyMoviesImage(imageFileList);
				for(ImageFileVO  imageFileVO:imageFileList) {
					imageFileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+imageFileName);
					File destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+movies_id);
					FileUtils.moveFileToDirectory(srcFile, destDir,true);
				}
			}
		}catch(Exception e) {
			if(imageFileList!=null && imageFileList.size()!=0) {
				for(ImageFileVO  imageFileVO:imageFileList) {
					imageFileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+imageFileName);
					srcFile.delete();
				}
			}
			e.printStackTrace();
		}
		
	}
	

	@Override
	@RequestMapping(value="/addNewMoviesImage.do" ,method={RequestMethod.POST})
	public void addNewMoviesImage(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		
		multipartRequest.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String imageFileName=null;
		
		Map moviesMap = new HashMap();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			moviesMap.put(name,value);
		}
		
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		String reg_id = memberVO.getMember_id();
		
		List<ImageFileVO> imageFileList=null;
		int movies_id=0;
		try {
			imageFileList =upload(multipartRequest);
			if(imageFileList!= null && imageFileList.size()!=0) {
				for(ImageFileVO imageFileVO : imageFileList) {
					movies_id = Integer.parseInt((String)moviesMap.get("movies_id"));
					imageFileVO.setMovies_id(movies_id);
					imageFileVO.setReg_id(reg_id);
				}
				
			    adminMoviesService.addNewMoviesImage(imageFileList);
				for(ImageFileVO  imageFileVO:imageFileList) {
					imageFileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+imageFileName);
					File destDir = new File(CURR_IMAGE_REPO_PATH+"\\"+movies_id);
					FileUtils.moveFileToDirectory(srcFile, destDir,true);
				}
			}
		}catch(Exception e) {
			if(imageFileList!=null && imageFileList.size()!=0) {
				for(ImageFileVO  imageFileVO:imageFileList) {
					imageFileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+"temp"+"\\"+imageFileName);
					srcFile.delete();
				}
			}
			e.printStackTrace();
		}
	}

	@Override
	@RequestMapping(value="/removeMoviesImage.do" ,method={RequestMethod.POST})
	public void  removeMoviesImage(@RequestParam("movies_id") int movies_id,
			                      @RequestParam("image_id") int image_id,
			                      @RequestParam("imageFileName") String imageFileName,
			                      HttpServletRequest request, HttpServletResponse response)  throws Exception {
		
		adminMoviesService.removeMoviesImage(image_id);
		try{
			File srcFile = new File(CURR_IMAGE_REPO_PATH+"\\"+movies_id+"\\"+imageFileName);
			srcFile.delete();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
