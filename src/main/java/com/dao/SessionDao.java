package com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.DoctorProfileBean;
import com.bean.PatientProfileBean;
import com.bean.UserBean;

@Repository
public class SessionDao {
    @Autowired
    JdbcTemplate stmt;

    public int insertUser(UserBean userBean) {
        // TODO Auto-generated method stub
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSql = "insert into users (email,password,firstname,lastname,gender,roleid,status,statusreason,otp) values(?,?,?,?,?,?,?,?,?)";

        stmt.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement pstmt = con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, userBean.getEmail());
                pstmt.setString(2, userBean.getPassword());
                pstmt.setString(3, userBean.getFirstname());
                pstmt.setString(4, userBean.getLastname());
                pstmt.setString(5, userBean.getGender());
                pstmt.setInt(6, userBean.getRoleid());
                pstmt.setInt(7, userBean.getStatus());
                pstmt.setString(8, userBean.getStatusReason());
                pstmt.setString(9, userBean.getOtp());
                return pstmt;
            }
        }, keyHolder);

        int userId = (Integer) keyHolder.getKeys().get("userid");
        userBean.setUserId(userId);
        return userBean.getUserId();
    }

    public void addDoctorProfile(DoctorProfileBean doctorProfileBean) {

        UserBean userBean = new UserBean();
        userBean.setRoleid(3);

        //		doctorProfileBean.setRoleId(3);
        int userId = insertUser(doctorProfileBean);

        doctorProfileBean.setUserId(userId);

        stmt.update(
                "insert into doctorprofile (userid,qualification,specialization,experience,profilepic,about,registrationno) values (?,?,?,?,?,?,?)",
                doctorProfileBean.getUserId(), doctorProfileBean.getQualification(),
                doctorProfileBean.getSpecialization(),
                doctorProfileBean.getExperience(), doctorProfileBean.getProfilepic(),
                doctorProfileBean.getAbout(), doctorProfileBean.getRegistrationNo());
    }
    
    public void addPatient(PatientProfileBean patientBean) {
    	
    	UserBean userBean = new UserBean();
        userBean.setRoleid(2);
        int userId = insertUser(patientBean);
        patientBean.setUserId(userId);
        patientBean.setPatientname(patientBean.getFirstname());
        
    	
        stmt.update("insert into patientprofile(patientname,gender,phoneno,email,age,profilepic,cityid,pincode,userid) values(?,?,?,?,?,?,?,?,?)",
                 patientBean.getPatientname(),patientBean.getGender(), patientBean.getPhoneno(), 
                patientBean.getEmail(),patientBean.getAge(), patientBean.getProfilepic(), 
                 patientBean.getCityid(),patientBean.getPincode(),
                patientBean.getUserId());
        System.out.println(patientBean.getUserId());
	}


    public void updateSignup(UserBean signupBean) {
        stmt.update(
                "update users set email = ?,password = ?,firstname = ?,lastname = ?,gender = ?,roleid = ?,status=? where userid = ?",
                signupBean.getEmail(), signupBean.getPassword(), signupBean.getFirstname(), signupBean.getLastname(),
                signupBean.getGender(), signupBean.getRoleid(), signupBean.getStatus(), signupBean.getUserId());

    }

    public UserBean login(String email, String password) {
        // TODO Auto-generated method stub
        email = email.trim();
        password = password.trim();
        UserBean userBean = null;
        System.out.println(email);
        System.out.println(password);
        try {
            userBean = stmt.queryForObject("select * from users where email = ? and password = ?",
                    new Object[]{email, password}, BeanPropertyRowMapper.newInstance(UserBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("errror ...");
            e.printStackTrace();
        }

        System.out.println(userBean);
        return userBean;
    }


    public UserBean getUserByEmail(String email) {
        // TODO Auto-generated method stub

        UserBean userBean = null;

        try {
            userBean = stmt.queryForObject("select * from users where email=?",

                    new Object[]{email}, BeanPropertyRowMapper.newInstance(UserBean.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return userBean;
    }

    public void updatePassword(UserBean userBean) {
        // TODO Auto-generated method stub
        stmt.update("update users set password = ? where email  = ? ", userBean.getPassword(), userBean.getEmail());
    }

    public List<DoctorProfileBean> listdoctor() {

        java.util.List<DoctorProfileBean> DoctorBean = stmt.query("select u.*,d.*,d.doctorprofileid as docProfileId from doctorprofile as d join users u using(userid) where userid = d.userid and d.isdeleted=0", BeanPropertyRowMapper.newInstance(DoctorProfileBean.class));
        // TODO Auto-generated method stub
        System.out.println(DoctorBean.get(0).getDocProfileId());
        return DoctorBean;
    }

    public DoctorProfileBean getDoctorById(int userId) {
        // TODO Auto-generated method stub
        DoctorProfileBean bean = null;
        try {
            bean = stmt.queryForObject("select * from doctorprofile as d join users using(userid) where userid = d.userid and userid=?", new Object[]{userId},
                    BeanPropertyRowMapper.newInstance(DoctorProfileBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        //userid -- userBean  //first
        //docprod - doctProfile
        return bean;
    }

    public void updateDoctor(DoctorProfileBean bean) {
        // TODO Auto-generated method stub

        stmt.update("update doctorprofile set qualification=?,specialization=?,experience=?,profilepic=?,about=?,registrationno=? where userid=?"
                , bean.getQualification(), bean.getSpecialization(), bean.getExperience(), bean.getProfilepic(), bean.getAbout(), bean.getRegistrationNo(), bean.getUserId());

        stmt.update(
                "update users set email = ?,password = ?,firstname = ?,lastname = ?,gender = ?,status=? where userid = ?",
                bean.getEmail(), bean.getPassword(), bean.getFirstname(), bean.getLastname(), bean.getGender(), bean.getStatus(),
                bean.getUserId());

    }


    public List<UserBean> listUser() {
        // TODO Auto-generated method stub

        java.util.List<UserBean> userBean = stmt.query("select * from users where isdeleted=0", BeanPropertyRowMapper.newInstance(UserBean.class));
        return userBean;
    }

    public void deleteDoctor(int userId) {
        // TODO Auto-generated method stub
        stmt.update("update doctorprofile set isdeleted = 1 where userid = ?", userId);
    }

    
    public void deleteSignup(int userId) {
    	// TODO Auto-generated method stub
        stmt.update("update users set isdeleted = 1 where userid = ?", userId);

    }

	public UserBean getuserById(int userId) {
		// TODO Auto-generated method stub
		UserBean bean = null;
        try {
            bean = stmt.queryForObject("select * from users where userid=?", new Object[]{userId},
                    BeanPropertyRowMapper.newInstance(UserBean.class));
        } catch (Exception e) {
            // TODO: handle exception
        	
            e.printStackTrace();
        }
		return bean;
	}

	public void addPatientProfile(PatientProfileBean patientProfileBean) {
		// TODO Auto-generated method stub
		UserBean userBean = new UserBean();
      userBean.setRoleid(2);

        //		doctorProfileBean.setRoleId(3);
        int userId = insertUser(patientProfileBean);

        patientProfileBean.setUserId(userId);
//      patientProfileBean.setCityid(0);
    	
        stmt.update("insert into patientprofile(patientname,gender,phoneno,email,age,profilepic,pincode,userid) values(?,?,?,?,?,?,?,?)",
        		patientProfileBean.getPatientname(), patientProfileBean.getGender(), patientProfileBean.getPhoneno(), 
        		patientProfileBean.getEmail(),patientProfileBean.getAge(), patientProfileBean.getProfilepic(), 
        		patientProfileBean.getPincode(),patientProfileBean.getUserId());
	}

	public List<DoctorProfileBean> kycDoctor() {
		// TODO Auto-generated method stub
		java.util.List<DoctorProfileBean> DoctorBean = stmt.query("select u.*,d.*,d.doctorprofileid as docProfileId from doctorprofile as d join users u using(userid) where userid = d.userid and d.isdeleted=0 and u.status=5", BeanPropertyRowMapper.newInstance(DoctorProfileBean.class));
		return DoctorBean;
	}

	public List<DoctorProfileBean> activeDoctor() {
		// TODO Auto-generated method stub
		java.util.List<DoctorProfileBean> DoctorBean = stmt.query("select u.*,d.*,d.doctorprofileid as docProfileId from doctorprofile as d join users u using(userid) where userid = d.userid and d.isdeleted=0 and u.status=1", BeanPropertyRowMapper.newInstance(DoctorProfileBean.class));
		return DoctorBean;
	}

	public List<DoctorProfileBean> pendingDoctor() {
		// TODO Auto-generated method stub
		java.util.List<DoctorProfileBean> DoctorBean = stmt.query("select u.*,d.*,d.doctorprofileid as docProfileId from doctorprofile as d join users u using(userid) where userid = d.userid and d.isdeleted=0 and u.status=2", BeanPropertyRowMapper.newInstance(DoctorProfileBean.class));
		return DoctorBean;
	}

	public List<DoctorProfileBean> pauseDoctor() {
		// TODO Auto-generated method stub
		java.util.List<DoctorProfileBean> DoctorBean = stmt.query("select u.*,d.*,d.doctorprofileid as docProfileId from doctorprofile as d join users u using(userid) where userid = d.userid and d.isdeleted=0 and u.status=4", BeanPropertyRowMapper.newInstance(DoctorProfileBean.class));
		return DoctorBean;
	}

	
	public List<UserBean> searchDoctor(String searchdoctor) {
		// TODO Auto-generated method stub
		java.util.List<UserBean> DoctorBean = stmt.query("select * from users where firstname LIKE '%"+searchdoctor+"%", BeanPropertyRowMapper.newInstance(UserBean.class));
		return DoctorBean;
	}

	


}
