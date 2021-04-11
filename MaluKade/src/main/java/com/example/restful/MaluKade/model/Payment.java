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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "payment")
@EntityListeners(AuditingEntityListener.class)
public class Payment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "payment_Type",length = 20,unique = true,nullable = false)
	private String paymentType;
	
	@Column(name = "active_Status",nullable = false)
	private boolean status;
	
	@Column(name = "c_Date",nullable = false)
	private Date date;
	
	@OneToMany(mappedBy = "payment",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Payment> payment;
	
	public Payment() {
		
	}

	public Payment(long id, String paymentType, boolean status, Date date) {
		super();
		this.id = id;
		this.paymentType = paymentType;
		this.status = status;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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
		return "Payment [id=" + id + ", paymentType=" + paymentType + ", status=" + status + ", date=" + date + "]";
	}
}
