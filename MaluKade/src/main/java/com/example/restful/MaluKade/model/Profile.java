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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "profile")
@EntityListeners(AuditingEntityListener.class)
public class Profile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_Name",length = 20,nullable = false)
	private String first_Name;
	
	@Column(name = "last_Name",length = 20,nullable = false)
	private String last_Name;
	
	@Column(name = "mobile",length = 15,nullable = false)
	private String mobile;
	
	@Column(name = "email",length = 40,nullable = false,unique = true)
	private String email;
	
	@Column(name = "user_name",length = 45,nullable = false,unique = true)
	private String userName;
	
	@Column(name = "password",length = 40,nullable = false)
	private String password;
	
	@Column(name = "active_Status",nullable = false)
	private boolean status;
	
	@Column(name = "create_Date",nullable = false)
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_type",nullable = false)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private ProfileType profileType;
	
	@OneToMany(mappedBy = "profile",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Order> orders;
	
	@OneToMany(mappedBy = "profile",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<JobCard> jobCards;
	
	@OneToOne(mappedBy = "profile")
	private ProfileImage profileImage;
	
	public Profile() {
	}

	public Profile(long id) {
		super();
		this.id = id;
	}


	public Profile(long id, String first_Name, String last_Name, String mobile, String email, String userName,
			String password, boolean status, Date date, ProfileType profileType) {
		super();
		this.id = id;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.mobile = mobile;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.status = status;
		this.date = date;
		this.profileType = profileType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst_Name() {
		return first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public ProfileType getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", first_Name=" + first_Name + ", last_Name=" + last_Name + ", mobile=" + mobile
				+ ", email=" + email + ", userName=" + userName + ", password=" + password + ", status=" + status
				+ ", date=" + date + "]";
	}
	
}
