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
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name",length = 45,nullable = false,unique = true)
	private String name;
	
	@Column(name = "description",length = 100,nullable = false)
	private String description;
	
	@Column(name = "unitprice",length = 10,nullable = false)
	private Double unitprice;
	
	@Column(name = "date",nullable = false)
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cate_id",nullable = false)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sup_id",nullable = false)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Supplier supplier;
	
	@OneToMany(mappedBy = "products",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<ProductBatch> productBatchs;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<OrderDetails> details;
	
	public Product() {
		
	}

	public Product(long id, String name, String description, Double unitprice, Date date, Category category,
			Supplier supplier) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.unitprice = unitprice;
		this.date = date;
		this.category = category;
		this.supplier = supplier;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

//	public Category getCategory() {
//		return category;
//	}
//
	public void setCategory(Category category) {
		this.category = category;
	}
//
//	public Supplier getSupplier() {
//		return supplier;
//	}
//
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", unitprice=" + unitprice
				+ ", date=" + date + ", category=" + category + ", supplier=" + supplier + "]";
	}
}
