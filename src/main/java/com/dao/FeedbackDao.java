package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.FeedbackBean;

@Repository
public class FeedbackDao {

	@Autowired
	JdbcTemplate stmt;
	
	public void addFeedback(FeedbackBean feedbackBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into feedback(userid,patientid,feedback,rating) values(?,?,?,?)", 
				feedbackBean.getUserid(),feedbackBean.getPatientid(),feedbackBean.getFeedback(),feedbackBean.getRating());
	}

	public List<FeedbackBean> listFeedback() {
		// TODO Auto-generated method stub
		List<FeedbackBean> feedbackBean = stmt.query("select * from feedback",
				BeanPropertyRowMapper.newInstance(FeedbackBean.class));
		return feedbackBean;
	}

}
