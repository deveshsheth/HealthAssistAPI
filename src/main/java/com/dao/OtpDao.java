package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OtpDao {

    @Autowired
    JdbcTemplate stmt;

    public void updateOtp(String email, String otp) {
        // TODO Auto-generated method stub
        stmt.update("update users set otp = ? where email = ?", otp, email);
    }

}
