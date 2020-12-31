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
				pstmt.setString(1,userBean.getEmail());
				pstmt.setString(2, userBean.getPassword());
				pstmt.setString(3,userBean.getFirstName());
				pstmt.setString(4,userBean.getLastName());
				pstmt.setString(5,userBean.getGender());
				pstmt.setInt(6,userBean.getRoleId());
				pstmt.setInt(7,userBean.getStatus());
				pstmt.setString(8,userBean.getStatusReason());
				pstmt.setString(9,userBean.getOtp());
				return pstmt;
			}
		}, keyHolder);

		int userId = (Integer) keyHolder.getKeys().get("userid");	
		userBean.setUserId(userId);
		return userBean.getUserId();
	}

	public void addDoctorProfile(DoctorProfileBean doctorProfileBean) {
		UserBean userBean = new UserBean();
		userBean.setEmail(doctorProfileBean.getEmail());
		userBean.setPassword(doctorProfileBean.getPassword());
		userBean.setFirstName(doctorProfileBean.getFirstName());
		userBean.setLastName(doctorProfileBean.getLastName());
		userBean.setGender(doctorProfileBean.getGender());
		userBean.setRoleId(doctorProfileBean.getRoleId());
		userBean.setStatus(doctorProfileBean.getStatus());
		userBean.setStatusReason(doctorProfileBean.getStatusReason());
		
		int userId  = insertUser(userBean);
		doctorProfileBean.setUserId(userId);
		stmt.update(
				"insert into doctorprofile (userid,qualification,specialization,experience,profilepic,about,registrationno) values (?,?,?,?,?,?,?)",
				doctorProfileBean.getUserId(), doctorProfileBean.getQualification(),
				doctorProfileBean.getSpecialization(),
				doctorProfileBean.getExperience_in_year(), doctorProfileBean.getProfile_pic(),
				doctorProfileBean.getAbout(), doctorProfileBean.getRegistrationNo());
	}
	
	
	public List<UserBean> listUser() {
		// TODO Auto-generated method stub
		java.util.List<UserBean> userBean = stmt.query("select * from users",BeanPropertyRowMapper.newInstance(UserBean.class)); 
		 return userBean;
	}

	
	public void updateSignup(UserBean signupBean) {
		stmt.update(
				"update users set email = ?,password = ?,firstname = ?,lastname = ?,gender = ?,roleid = ? where userid = ?",
				signupBean.getEmail(), signupBean.getPassword(), signupBean.getFirstName(), signupBean.getLastName(),
				signupBean.getGender(), signupBean.getRoleId(), signupBean.getUserId());

	}
	
	public UserBean login(String email, String password) {
		// TODO Auto-generated method stub
		UserBean userBean = null;
			userBean =stmt.queryForObject("select * from users where email=? and password=?",
				new Object[] {email,password}, BeanPropertyRowMapper.newInstance(UserBean.class));
		return userBean;
	}
	
	public void deleteSignup(int userId) {
		stmt.update("delete from users where userid = ?", userId);

	}

	public UserBean getUserByEmail(String email) {
		// TODO Auto-generated method stub
		
		UserBean userBean = null;

		try {
			userBean = stmt.queryForObject("select * from users where email=?",

					new Object[] { email }, BeanPropertyRowMapper.newInstance(UserBean.class));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userBean;
	}

	public void updatePassword(UserBean userBean) {
		// TODO Auto-generated method stub
		stmt.update("update users set password = ? where email  = ? ",userBean.getPassword(),userBean.getEmail());
	}

	

}
