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

import com.bean.PathologyBean;
import com.bean.PharmacyBean;
import com.bean.UserBean;


@Repository
public class PathologyDao {

    @Autowired
    JdbcTemplate stmt;


    public int addPathology(PathologyBean pathologyBean) {
        // TODO Auto-generated method stub
    	KeyHolder keyHolder = new GeneratedKeyHolder();
    	String insertSql = "insert into pathology(pathologyname,timing,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)";
    	
    	stmt.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement pstmt = con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, pathologyBean.getPathologyname());
                pstmt.setString(2, pathologyBean.getTiming());
                pstmt.setString(3, pathologyBean.getAddress());
                pstmt.setString(4, pathologyBean.getPhoneno());
                pstmt.setDouble(5, pathologyBean.getRating());
                pstmt.setString(6, pathologyBean.getAbout());
                pstmt.setDouble(7, pathologyBean.getLat());
                pstmt.setDouble(8, pathologyBean.getLog());
                pstmt.setInt(9, pathologyBean.getCityid());
                pstmt.setInt(10, pathologyBean.getPincode());
                return pstmt;
            }
        }, keyHolder);
    	int pathologyid = (Integer) keyHolder.getKeys().get("pathologyid");
    	pathologyBean.setPathologyid(pathologyid);
        return pathologyBean.getPathologyid();
    	
    }
    
	public void addAssignUserPathology(PathologyBean pathologyBean) {
		// TODO Auto-generated method stub
		int pathologyid = addPathology(pathologyBean);

		pathologyBean.setPathologyid(pathologyid);
		
		stmt.update("insert into user_pathology(userid,pathologyid) values(?,?)",
				pathologyBean.getUserid(), pathologyBean.getPathologyid());
	}

    public List<PathologyBean> listPathology() {
        // TODO Auto-generated method stub

        java.util.List<PathologyBean> pathologyBean = stmt.query("select *,cities.cityname from pathology as p join cities using(cityid) where p.cityid = cityid and p.isdeleted =0", BeanPropertyRowMapper.newInstance(PathologyBean.class));
        return pathologyBean;
    }

    public void deletePathology(int pathologyId) {
        // TODO Auto-generated method stub
        stmt.update("update pathology set isdeleted = 1 where pathologyid = ?", pathologyId);

    }

    public void updatePathology(PathologyBean pathologyBean) {
        // TODO Auto-generated method stub
        stmt.update("update pathology set pathologyname=?,timing=?,address=?,phoneno=?,rating=?,about=?,lat=?,log=?,cityid=?,pincode=? where pathologyid=?",
                pathologyBean.getPathologyname(), pathologyBean.getTiming(), pathologyBean.getAddress(), pathologyBean.getPhoneno(), pathologyBean.getRating(),
                pathologyBean.getAbout(), pathologyBean.getLat(), pathologyBean.getLog(), pathologyBean.getCityid(), pathologyBean.getPincode(), pathologyBean.getPathologyid());

    }

    public PathologyBean getPathologyById(int pathologyId) {
        // TODO Auto-generated method stub
        PathologyBean bean = null;
        try {
            bean = stmt.queryForObject("select *,cities.cityname from pathology as p join cities using(cityid) where p.cityid = cityid and pathologyid=?", new Object[]{pathologyId},
                    BeanPropertyRowMapper.newInstance(PathologyBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return bean;
    }

	public List<UserBean> listAssignUserPathology() {
		// TODO Auto-generated method stub
		java.util.List<UserBean> pathologyBean = stmt.query("select * from users where roleid=5", BeanPropertyRowMapper.newInstance(UserBean.class));
		return pathologyBean;
	}




}
