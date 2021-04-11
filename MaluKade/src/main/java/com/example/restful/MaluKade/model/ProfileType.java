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
@Table(name = "profile_Type")
@EntityListeners(AuditingEntityListener.class)
public class ProfileType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "profile_type",length = 20,unique = true,nullable = false)
	private String type;
	
	@Column(name = "active_Status",nullable = false)
	private boolean status;
	
	@Column(name = "c_Date",nullable = false)
	private Date date;
	
	@OneToMany(mappedBy = "profileType",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Profile> profiles;
	
	public ProfileType() {
		
	}

	public ProfileType(long id, String type, boolean status, Date date) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		return "JobType [id=" + id + ", type=" + type + ", status=" + status + ", date=" + date + "]";
	}
}
