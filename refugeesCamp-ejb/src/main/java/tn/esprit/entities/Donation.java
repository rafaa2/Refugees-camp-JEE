package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * author Salim Ben Hassine
 *
 * 
 */

@Entity
public class Donation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5222348366362129628L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private double amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAndTime;
	private boolean anonymous;
	private String donorName;
	private String message;
	
	public Donation(int id, double amount, Date dateAndTime, boolean anonymous, String donorName, String message) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateAndTime = dateAndTime;
		this.anonymous = anonymous;
		this.donorName = donorName;
		this.message = message;
	}

	public Donation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
	
}