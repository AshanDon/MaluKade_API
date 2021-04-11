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
@Table(name = "supplier")
@EntityListeners(AuditingEntityListener.class)
public class Supplier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name",length = 50,nullable = false,unique = true)
	private String name;
	
	@Column(name = "address",length = 100,nullable = false)
	private String address;
	
	@Column(name = "mobile1",length = 15,nullable = false)
	private String mobile1;
	
	@Column(name = "mobile2",length = 15,nullable = true)
	private String mobile2;
	
	@Column(name = "email",length = 25,nullable = false)
	private String email;
	
	@Column(name = "date",nullable = false)
	private Date date;
	
	@Column(name = "active_Status",nullable = false)
	private Boolean status;
	
	@OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Product> products;
	
	public Supplier() {
		
	}

	public Supplier(long id, String name, String address, String mobile1, String mobile2, String email, Date date,
			Boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.email = email;
		this.date = date;
		this.status = status;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getMobile1() {
		return mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public String getEmail() {
		return email;
	}

	public Date getDate() {
		return date;
	}

	public Boolean getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", address=" + address + ", mobile1=" + mobile1 + ", mobile2="
				+ mobile2 + ", email=" + email + ", date=" + date + ", status=" + status + "]";
	}
}
