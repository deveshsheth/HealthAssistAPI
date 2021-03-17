package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.MedicineBean;
import com.bean.ResponseBean;
import com.dao.MedicineDao;

@CrossOrigin
@RestController
public class MedicineController {
	
	@Autowired
	MedicineDao medicineDao;
	
	@PostMapping("/addMedicine")
	public ResponseBean<MedicineBean> addMedicine(@RequestBody MedicineBean medicineBean){
		
		medicineDao.addMedicine(medicineBean);
		
		ResponseBean<MedicineBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(medicineBean);
		responseBean.setMsg("Medicine Added!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/listMedicine")
	public ResponseBean<List<MedicineBean>> listMedicine(){
		List<MedicineBean> medicineBean = medicineDao.listMedicine();
		
		ResponseBean<List<MedicineBean>> responseBean = new ResponseBean<>();
	
		responseBean.setData(medicineBean);	
		responseBean.setMsg("Medicine List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("/updateMedicine")
	public ResponseBean<MedicineBean> updateMedicine(@RequestBody MedicineBean medicineBean){
		medicineDao.updateMedicine(medicineBean);
		
		ResponseBean<MedicineBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(medicineBean);
		responseBean.setMsg("Medicine Updated!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/getMedicine/{medicineid}")
    public ResponseBean<MedicineBean> getUser(@PathVariable("medicineid") int medicineid, MedicineBean bean) {
        ResponseBean<MedicineBean> responseBean = new ResponseBean<>();
        bean = medicineDao.getMedicineById(medicineid);
        responseBean.setData(bean);
        responseBean.setMsg("Single Medicine Return");
        responseBean.setStatus(200);

        return responseBean;
    }
	
	@DeleteMapping("/deleteMedicine/{medicineid}")
	public ResponseBean<MedicineBean> deleteMedicine(@PathVariable("medicineid") int medicineid){
		medicineDao.deleteMedicine(medicineid);
		
		ResponseBean<MedicineBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Medicine Deleted!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}

}
