package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.PathologyBean;


@Repository
public class PathologyDao {

	@Autowired
	JdbcTemplate stmt;
	
	ArrayList<PathologyBean> pathology = new ArrayList<>();
	
	public void addPathology(PathologyBean pathologyBean) {
		// TODO Auto-generated method stub
					stmt.update("insert into pathology(pathologyname,timing,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)", 
					pathologyBean.getPathologyname(),pathologyBean.getTiming(),pathologyBean.getAddress(),pathologyBean.getPhoneno(),pathologyBean.getRating(),
					pathologyBean.getAbout(),pathologyBean.getLat(),pathologyBean.getLog(),pathologyBean.getCityid(),pathologyBean.getPincode());
		
	}

	public List<PathologyBean> listPathology() {
		// TODO Auto-generated method stub
		
		java.util.List<PathologyBean> pathologyBean = stmt.query("select *,cities.cityname from pathology as p join cities using(cityid) where p.cityid = cityid",BeanPropertyRowMapper.newInstance(PathologyBean.class));
		return pathologyBean;
	}

	public void deletePathology(int pathologyId) {
		// TODO Auto-generated method stub
		stmt.update("delete from pathology where pathologyid = ?",pathologyId);
		
	}

	public void updatePathology(PathologyBean pathologyBean) {
		// TODO Auto-generated method stub
		stmt.update("update pathology set pathologyname=?,timing=?,address=?,phoneno=?,rating=?,about=?,lat=?,log=?,cityid=?,pincode=? where pathologyid=?",				
				pathologyBean.getPathologyname(),pathologyBean.getTiming(),pathologyBean.getAddress(),pathologyBean.getPhoneno(),pathologyBean.getRating(),
				pathologyBean.getAbout(),pathologyBean.getLat(),pathologyBean.getLog(),pathologyBean.getCityid(),pathologyBean.getPincode(),pathologyBean.getPathologyid());
		
	}

	public PathologyBean getPathologyById(int pathologyId) {
		// TODO Auto-generated method stub
		
		PathologyBean pathologyBean = null;
		for(PathologyBean pathology : pathology)
		{
			if(pathology.getPathologyid() == pathologyId) {
				
				pathologyBean = pathology;
				break;
			}
		}
		return pathologyBean;
	}
	
	

}
