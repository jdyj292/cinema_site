package com.cinema.movies.vo;

public class ImageFileVO {
	private int movies_id;
	private int image_id;
	private String fileName;
	private String fileType;
	private String reg_id;
	

	public ImageFileVO() {
		super();
	}


	public int getMovies_id() {
		return movies_id;
	}




	public void setMovies_id(int movies_id) {
		this.movies_id = movies_id;
	}




	public int getImage_id() {
		return image_id;
	}




	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}




	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}


	

}
