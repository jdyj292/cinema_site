package com.cinema.reservation.vo;

import org.springframework.stereotype.Component;

@Component("reservationVO")
public class ReservationVO {
	private int reservation_id;
	private int schedule_id;
	private String member_id;
	private String reservation_seats;
	private String reservation_email1;
	private String reservation_email2;
	private String reservation_hp1;
	private String reservation_hp2;
	private String reservation_hp3;
	private String reservation_adult;
	private String reservation_teenager;
	private String movies_title;
	private String member_name;
	private String reservation_price;
	private String pay_method;
	private String card_com_name;
	private String reservation_date;
	private String card_pay_month;
	private String pay_order_tel1;
	private String pay_order_tel2;
	private String pay_order_tel3;
	private String schedule_place;// 포함 아래부터는 데이터 베이스에 없음
	private String schedule_begin_date;
	private String schedule_end_date;
	private String schedule_status;
	private int movies_id;
	private String movies_filename;
	private String movies_age_restriction;
	

	
	public int getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}
	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReservation_seats() {
		return reservation_seats;
	}
	public void setReservation_seats(String reservation_seats) {
		this.reservation_seats = reservation_seats;
	}
	public String getReservation_email1() {
		return reservation_email1;
	}
	public void setReservation_email1(String reservation_email1) {
		this.reservation_email1 = reservation_email1;
	}
	public String getReservation_email2() {
		return reservation_email2;
	}
	public void setReservation_email2(String reservation_email2) {
		this.reservation_email2 = reservation_email2;
	}
	public String getReservation_hp1() {
		return reservation_hp1;
	}
	public void setReservation_hp1(String reservation_hp1) {
		this.reservation_hp1 = reservation_hp1;
	}
	public String getReservation_hp2() {
		return reservation_hp2;
	}
	public void setReservation_hp2(String reservation_hp2) {
		this.reservation_hp2 = reservation_hp2;
	}
	public String getReservation_hp3() {
		return reservation_hp3;
	}
	public void setReservation_hp3(String reservation_hp3) {
		this.reservation_hp3 = reservation_hp3;
	}
	public String getReservation_adult() {
		return reservation_adult;
	}
	public void setReservation_adult(String reservation_adult) {
		this.reservation_adult = reservation_adult;
	}
	public String getReservation_teenager() {
		return reservation_teenager;
	}
	public void setReservation_teenager(String reservation_teenager) {
		this.reservation_teenager = reservation_teenager;
	}
	public String getMovies_title() {
		return movies_title;
	}
	public void setMovies_title(String movies_title) {
		this.movies_title = movies_title;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getReservation_price() {
		return reservation_price;
	}
	public void setReservation_price(String reservation_price) {
		this.reservation_price = reservation_price;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public String getCard_com_name() {
		return card_com_name;
	}
	public void setCard_com_name(String card_com_name) {
		this.card_com_name = card_com_name;
	}
	public String getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(String reservation_date) {
		this.reservation_date = reservation_date;
	}
	public String getCard_pay_month() {
		return card_pay_month;
	}
	public void setCard_pay_month(String card_pay_month) {
		this.card_pay_month = card_pay_month;
	}
	public String getPay_order_tel1() {
		return pay_order_tel1;
	}
	public void setPay_order_tel1(String pay_order_tel1) {
		this.pay_order_tel1 = pay_order_tel1;
	}
	public String getPay_order_tel2() {
		return pay_order_tel2;
	}
	public void setPay_order_tel2(String pay_order_tel2) {
		this.pay_order_tel2 = pay_order_tel2;
	}
	public String getPay_order_tel3() {
		return pay_order_tel3;
	}
	public void setPay_order_tel3(String pay_order_tel3) {
		this.pay_order_tel3 = pay_order_tel3;
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
	public String getSchedule_status() {
		return schedule_status;
	}
	public void setSchedule_status(String schedule_status) {
		this.schedule_status = schedule_status;
	}
	public int getMovies_id() {
		return movies_id;
	}
	public void setMovies_id(int movies_id) {
		this.movies_id = movies_id;
	}
	
	public String getMovies_filename() {
		return movies_filename;
	}
	public void setMovies_filename(String movies_filename) {
		this.movies_filename = movies_filename;
	}
	public String getMovies_age_restriction() {
		return movies_age_restriction;
	}
	public void setMovies_age_restriction(String movies_age_restriction) {
		this.movies_age_restriction = movies_age_restriction;
	}
	
	
}
