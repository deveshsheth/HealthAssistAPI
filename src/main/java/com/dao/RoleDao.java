package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.RoleBean;


@Repository
public class RoleDao {
@Autowired
	JdbcTemplate stmt;

public void addRole(RoleBean roleBean) {
	// TODO Auto-generated method stub
	stmt.update("insert into role (rolename) values (?)",roleBean.getRoleName());

	
}

public List<RoleBean> listRole() {
	// TODO Auto-generated method stub
	 java.util.List<RoleBean> RoleBean = stmt.query("select * from role",BeanPropertyRowMapper.newInstance(RoleBean.class)); 
	 return RoleBean;

}

public void deleteRole(int roleId) {
	// TODO Auto-generated method stub
	stmt.update("delete from role where roleid = ?",roleId);
}


public void updateRole(RoleBean roleBean) {
	// TODO Auto-generated method stub
	stmt.update("update role set rolename=? where roleid=?",roleBean.getRoleName(),roleBean.getRoleId());
}


	
}
