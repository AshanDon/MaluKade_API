package com.example.restful.MaluKade.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id",nullable = false)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Profile profile;
	
	@Column(name = "total_Discount",nullable = false)
	private double discount;
	
	@Column(name = "total_Amount",nullable = false)
	private double amount;
	
	@Column(name = "active_Status",nullable = false)
	private boolean status;
	
	@Column(name = "date",nullable = false)
	private Date date;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<OrderDetails> details;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<PaymentDetails> paymentDetails;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<JobCard> jobCards;
	
	public Order() {
		
	}

	public Order(long id, Profile profile, double discount, double amount, boolean status, Date date) {
		super();
		this.id = id;
		this.profile = profile;
		this.discount = discount;
		this.amount = amount;
		this.status = status;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", discount=" + discount + ", amount=" + amount + ", status=" + status + ", date="
				+ date + "]";
	}
}
