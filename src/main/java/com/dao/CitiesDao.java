package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.CitiesBean;
import com.bean.MedicineBean;

@Repository
public class CitiesDao {

    @Autowired
    JdbcTemplate stmt;

    public void addCities(CitiesBean citiesBean) {
        // TODO Auto-generated method stub
        stmt.update("insert into cities(cityname,stateid) values(?,?)", citiesBean.getCityname(), citiesBean.getStateid());
    }

    public List<CitiesBean> listCities() {
        // TODO Auto-generated method stub
        java.util.List<CitiesBean> citiesBean = stmt.query("select *,states.statename from cities as s join states using(stateid) where s.stateid = stateid and s.isdeleted=0", BeanPropertyRowMapper.newInstance(CitiesBean.class));
        return citiesBean;
    }

    public void deleteCities(int cityId) {
        // TODO Auto-generated method stub
        stmt.update("update cities set isdeleted = 1 where cityid = ?", cityId);
    }

    public void updateCities(CitiesBean cityBean) {
        // TODO Auto-generated method stub
        stmt.update("update cities set cityname=?,stateid=? where cityid=?", cityBean.getCityname(), cityBean.getStateid(), cityBean.getCityid());
    }

	public CitiesBean getCityById(int cityid) {
		// TODO Auto-generated method stub
		CitiesBean bean = null;
        try {
            bean = stmt.queryForObject("select *,states.statename from cities as s join states using(stateid) where s.stateid = stateid and cityid=?", new Object[]{cityid},
                    BeanPropertyRowMapper.newInstance(CitiesBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
		return bean;
	}


}
