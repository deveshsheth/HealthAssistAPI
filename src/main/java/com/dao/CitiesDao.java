package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.CitiesBean;

@Repository
public class CitiesDao {
	
	@Autowired
	JdbcTemplate stmt;

	public void addCities(CitiesBean citiesBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into cities(cityname,stateid) values(?,?)", citiesBean.getCityname(),citiesBean.getStateid());
	}

	public List<CitiesBean> listCities() {
		// TODO Auto-generated method stub
		 java.util.List<CitiesBean> citiesBean = stmt.query("select * from cities",BeanPropertyRowMapper.newInstance(CitiesBean.class)); 
		return citiesBean;
	}

	public void deleteCities(int cityId) {
		// TODO Auto-generated method stub
		stmt.update("delete from cities where cityid = ?",cityId);
	}

	public void updateCities(CitiesBean cityBean) {
		// TODO Auto-generated method stub
		stmt.update("update cities set cityname=?,stateid=? where cityid=?",cityBean.getCityname(),cityBean.getStateid());
	}
	
	
}
