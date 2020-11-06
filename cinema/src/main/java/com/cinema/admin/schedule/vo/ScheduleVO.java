package com.cinema.admin.schedule.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;


@Component("ScheduleVO")
public class ScheduleVO {
	private int schedule_id;
	private int movies_id;
	private String movies_fileName;
	private String movies_title;
	private String schedule_place;
	private String schedule_begin_date;
	private String schedule_end_date;
	private String schedule_num_of_seats;
	private String movies_age_restriction;
	
	private String schedule_begin_time;
	
	
	
	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public int getMovies_id() {
		return movies_id;
	}
	public void setMovies_id(int movies_id) {
		this.movies_id = movies_id;
	}
	
	public String getMovies_fileName() {
		return movies_fileName;
	}
	public void setMovies_fileName(String movies_fileName) {
		this.movies_fileName = movies_fileName;
	}
	public String getMovies_title() {
		return movies_title;
	}
	public void setMovies_title(String movies_title) {
		this.movies_title = movies_title;
	}
	public String getSchedule_place() {
		return schedule_place;
	}
	public void setSchedule_place(String schedule_place) {
		this.schedule_place = schedule_place;
	}
	public String getSchedule_begin_date() {
		return schedule_begin_date;
	}
	public void setSchedule_begin_date(String schedule_begin_date) {
		this.schedule_begin_date = schedule_begin_date;
	}
	public String getSchedule_end_date() {
		return schedule_end_date;
	}
	public void setSchedule_end_date(String schedule_end_date) {
		this.schedule_end_date = schedule_end_date;
	}
	public String getSchedule_num_of_seats() {
		return schedule_num_of_seats;
	}
	public void setSchedule_num_of_seats(String schedule_num_of_seats) {
		this.schedule_num_of_seats = schedule_num_of_seats;
	}
	public String getMovies_age_restriction() {
		return movies_age_restriction;
	}
	public void setMovies_age_restriction(String movies_age_restriction) {
		this.movies_age_restriction = movies_age_restriction;
	}

	public String getSchedule_begin_time() {
		return schedule_begin_time;
	}
	public void setSchedule_begin_time(String schedule_begin_time) {
		this.schedule_begin_time = schedule_begin_time;
	}

}
