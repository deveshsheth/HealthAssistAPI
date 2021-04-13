package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
                userPathologyBean.getUserid(), userPathologyBean.getPathologyid());
    }

    public void updateUserPathology(userPathologyBean userPathologyBean) {
        // TODO Auto-generated method stub
        stmt.update("update user_pathology set userid=?,pathologyid=? where userpathologyid=?", userPathologyBean.getUserid(), userPathologyBean.getPathologyid(), userPathologyBean.getUserpathologyid());
    }

	public List<userPathologyBean> listUserPathology(int userid) {
		// TODO Auto-generated method stub
		 java.util.List<userPathologyBean> userPathologyBean = stmt.query("select u.*,p.*,up.* from users as u,pathology as p,user_pathology as up where up.userid = u.userid and up.pathologyid = p.pathologyid and up.userid = ? and up.isdeleted=0",new Object[] {userid}, BeanPropertyRowMapper.newInstance(userPathologyBean.class));

		return userPathologyBean;
	}

}
