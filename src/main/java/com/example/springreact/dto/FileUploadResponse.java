package com.example.springreact.dto;

import java.util.Objects;

public class FileUploadResponse {

	private String profileImagePath;
	private String message;
	
	 
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, profileImagePath);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileUploadResponse other = (FileUploadResponse) obj;
		return Objects.equals(message, other.message) && Objects.equals(profileImagePath, other.profileImagePath);
	}

	@Override
	public String toString() {
		return "FileUploadResponse [profileImagePath=" + profileImagePath + ", message=" + message + "]";
	}

  	 
}
