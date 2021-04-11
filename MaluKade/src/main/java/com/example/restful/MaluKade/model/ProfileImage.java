package com.example.restful.MaluKade.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "profile_Image")
@EntityListeners(AuditingEntityListener.class)
public class ProfileImage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pro_id",referencedColumnName = "id")
	private Profile profile;
	
	@Column(name = "image_Name",length = 45,nullable = false)
	private String imageName;
	
	@Column(name = "image_Type",length = 45,nullable = false)
	private String imageType;
	
	@Column(name = "image_Byte",nullable = false)
	private byte[] imageByte;
	
	public ProfileImage() {

	}

	public ProfileImage(Profile profile, String imageName, String imageType, byte[] imageByte) {
		super();
		this.profile = profile;
		this.imageName = imageName;
		this.imageType = imageType;
		this.imageByte = imageByte;
	}



	public ProfileImage(long id, Profile profile, String imageName, String imageType, byte[] imageByte) {
		super();
		this.id = id;
		this.profile = profile;
		this.imageName = imageName;
		this.imageType = imageType;
		this.imageByte = imageByte;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getImageByte() {
		return imageByte;
	}

	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

	@Override
	public String toString() {
		return "ProfileImage [id=" + id + ", imageName=" + imageName + ", imageType=" + imageType + ", imageByte="
				+ imageByte + "]";
	}
}
