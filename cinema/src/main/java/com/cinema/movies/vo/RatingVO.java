package com.cinema.movies.vo;

import java.sql.Date;
import java.util.ArrayList;

public class RatingVO {
	private int rating_id;
	private int movies_id;
	private String member_id;
	private int rating_value;
	private String rating_contend;
	private Date rating_date;
	
	public RatingVO() {
	}

	public int getRating_id() {
		return rating_id;
	}

	public void setRating_id(int rating_id) {
		this.rating_id = rating_id;
	}

	public int getMovies_id() {
		return movies_id;
	}

	public void setMovies_id(int movies_id) {
		this.movies_id = movies_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getRating_value() {
		return rating_value;
	}

	public void setRating_value(int rating_value) {
		this.rating_value = rating_value;
	}

	public String getRating_contend() {
		return rating_contend;
	}

	public void setRating_contend(String rating_contend) {
		this.rating_contend = rating_contend;
	}

	public Date getRating_date() {
		return rating_date;
	}

	public void setRating_date(Date rating_date) {
		this.rating_date = rating_date;
	}


	



}
