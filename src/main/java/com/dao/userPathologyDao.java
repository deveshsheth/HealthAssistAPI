package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.userPathologyBean;
@Repository
public class userPathologyDao {

	@Autowired
	JdbcTemplate stmt;
	
	public void addUserPathology(userPathologyBean userPathologyBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into user_pathology(userid,pathologyid) values(?,?)",
				userPathologyBean.getUserid(),userPathologyBean.getPathologyid());
	}

}
