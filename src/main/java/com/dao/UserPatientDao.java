package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserPatientBean;

@Repository
public class UserPatientDao {

    @Autowired
    JdbcTemplate stmt;

    public void addUserPatient(UserPatientBean userpatientBean) {
        // TODO Auto-generated method stub
        stmt.update("insert into user_patient(userid,patientid) values(?,?)", userpatientBean.getUserid(), userpatientBean.getPatientid());
    }

    public void updateUserPatient(UserPatientBean userpatientBean) {
        // TODO Auto-generated method stub
        stmt.update("update user_patient set userid=?,patientid=? where userpatientid=?", userpatientBean.getUserid(), userpatientBean.getPatientid(), userpatientBean.getUserpatientid());

    }

}
