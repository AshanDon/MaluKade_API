package com.example.restful.MaluKade.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "payment_Details")
@EntityListeners(AuditingEntityListener.class)
public class PaymentDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",nullable = false)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "o_id",nullable = false)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_Type_Id",nullable = false)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Payment payment;
	
	@Column(name = "pay_Amount",nullable = false)
	private double pay_Amount;
	
	@Column(name = "pay_Date",nullable = false)
	private Date date;
	
	protected PaymentDetails() {
		
	}

	public PaymentDetails(long id, Order order, Payment payment, double pay_Amount, Date date) {
		super();
		this.id = id;
		this.order = order;
		this.payment = payment;
		this.pay_Amount = pay_Amount;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public double getPay_Amount() {
		return pay_Amount;
	}

	public void setPay_Amount(double pay_Amount) {
		this.pay_Amount = pay_Amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PaymentDetails [id=" + id + ", pay_Amount=" + pay_Amount + ", date=" + date + "]";
	}
}
