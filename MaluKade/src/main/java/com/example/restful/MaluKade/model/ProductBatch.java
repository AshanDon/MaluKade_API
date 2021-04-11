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
@Table(name = "products_batch")
@EntityListeners(AuditingEntityListener.class)
public class ProductBatch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "b_id",nullable = false)
	private long id;
	
	@Column(name = "purchase_amount",nullable = false)
	private double amount;
	
	@Column(name = "purchase_date",nullable = false)
	private Date date;
	
	@Column(name = "purchase_qty",nullable = false)
	private double qty;
	
	@Column(name = "available_qty",nullable = false)
	private double available_qty;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pro_id",nullable = false)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Product products;
	
	public ProductBatch() {
		
	}

	public ProductBatch(long id, double amount, Date date, double qty,double available_qty, Product products) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.qty = qty;
		this.available_qty = available_qty;
		this.products = products;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public long getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}

	public double getQty() {
		return qty;
	}

	public double getAvailable_qty() {
		return available_qty;
	}

	public void setAvailable_qty(double available_qty) {
		this.available_qty = available_qty;
	}

	@Override
	public String toString() {
		return "ProductBatch [id=" + id + ", amount=" + amount + ", date=" + date + ", qty=" + qty + ", available_qty="
				+ available_qty + "]";
	}
}
