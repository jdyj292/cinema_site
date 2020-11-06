package com.cinema.reservation.vo;

import org.springframework.stereotype.Component;

@Component("seatsVO")
public class SeatsVO {
	
	private int schedule_id;
	private String seats_id;
	private String purchase_status;
	private String seats_date;
	
	
	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public String getSeats_id() {
		return seats_id;
	}
	public void setSeats_id(String seats_id) {
		this.seats_id = seats_id;
	}
	public String getPurchase_status() {
		return purchase_status;
	}
	public void setPurchase_status(String purchase_status) {
		this.purchase_status = purchase_status;
	}
	public String getSeats_date() {
		return seats_date;
	}
	public void setSeats_date(String seats_date) {
		this.seats_date = seats_date;
	}
	
	
	

	

}
