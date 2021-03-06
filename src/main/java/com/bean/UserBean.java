package com.bean;

public class UserBean {
    int userId;
    String email;
    String password;
    String firstname;
    String lastname;
    String gender;
    int roleid;
    int status;
    String statusReason;
    String otp;
    String cityname;

    public static final int ACTIVE = 1, PENDING = 2, DISABLE = 3, PAUSE = 4, KYC_DOCTOR = 5;

    public UserBean() {
        status = PENDING;
        statusReason = "Verify Your Account Please Check your Email";
    }


    
    
    public String getCityname() {
		return cityname;
	}




	public void setCityname(String cityname) {
		this.cityname = cityname;
	}




	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstname() {
        return firstname;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public int getRoleid() {
        return roleid;
    }


    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }


}
