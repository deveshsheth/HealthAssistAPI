package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.MedicineBean;

@Repository
public class MedicineDao {
	
	@Autowired
	JdbcTemplate stmt;
	
	public void addMedicine(MedicineBean medicineBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into medicine(medicinename,medicinetype) values(?,?)", medicineBean.getMedicinename(),
				medicineBean.getMedicinetype());
	}

	public List<MedicineBean> listMedicine() {
		// TODO Auto-generated method stub
		List<MedicineBean> medicineBean = stmt.query("select * from medicine where isdeleted=0",
				BeanPropertyRowMapper.newInstance(MedicineBean.class));
		return medicineBean;
	}

	public void updateMedicine(MedicineBean medicineBean) {
		// TODO Auto-generated method stub
		stmt.update("update medicine set medicinename = ?, medicinetype = ? where medicineid = ?",
				medicineBean.getMedicinename(), medicineBean.getMedicinetype(), medicineBean.getMedicineid());
	}

	public void deleteMedicine(int medicineid) {
		// TODO Auto-generated method stub
		stmt.update("update medicine set isdeleted=1 where medicineid = ?",medicineid);
	}

	public MedicineBean getMedicineById(int medicineid) {
		// TODO Auto-generated method stub
		MedicineBean bean = null;
        try {
            bean = stmt.queryForObject("select * from medicine where medicineid=?", new Object[]{medicineid},
                    BeanPropertyRowMapper.newInstance(MedicineBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
		return bean;
	}

}
