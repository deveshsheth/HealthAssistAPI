package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.DoctClinicBean;
import com.bean.ResponseBean;
import com.bean.RoleBean;
import com.dao.DoctClinicDao;

@RestController
@CrossOrigin
public class DoctClinicController {

	@Autowired
	DoctClinicDao doctclinicdao;
	
	@PostMapping("/addDoctClinic")
	public ResponseBean<DoctClinicBean> adddoctclinic(@RequestBody DoctClinicBean doctclinicBean){
		doctclinicdao.addDoctClinic(doctclinicBean);
		ResponseBean<DoctClinicBean> response = new ResponseBean<>();
		response.setData(doctclinicBean);
		response.setMsg("Doctor Clinic Added successfully...!!");
		response.setStatus(200);
		
		return response;
	}
	
	@GetMapping("/listDoctClinic")
	public ResponseBean<java.util.List<DoctClinicBean>> listDoctClinic()
	{
		ResponseBean<java.util.List<DoctClinicBean>> response = new ResponseBean<>();

		java.util.List<DoctClinicBean> doctClinicBean = doctclinicdao.listDoctCLinic();
		response.setData(doctClinicBean);
		response.setMsg("Doctor Clinic Display..!!!!");
		response.setStatus(201);
		return response;
	}
	
	@PutMapping("/updateDoctClinic")
	public ResponseBean<DoctClinicBean> updateDoctClinic(@RequestBody DoctClinicBean doctclinicBean){
		doctclinicdao.updateDoctClinic(doctclinicBean);
		ResponseBean<DoctClinicBean> response = new ResponseBean<>();
		response.setData(doctclinicBean);
		response.setMsg("Doctor Clinic Updated Successfully..!!");
		return response;
	}
	
	@DeleteMapping("/addDoctClinic/{doctclinicid}")
	public ResponseBean<RoleBean> deleteRole(@PathVariable("doctclinicid")int doctclinicid){
		
		ResponseBean<RoleBean> response = new ResponseBean<>();
		doctclinicdao.deleteDoctClinic(doctclinicid);
		response.setMsg("Deleted Successfully..!!");
		response.setStatus(200);
		return response;
	}
}
