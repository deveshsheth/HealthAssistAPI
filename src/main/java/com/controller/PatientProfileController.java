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

import com.bean.PatientProfileBean;
import com.bean.ResponseBean;
import com.dao.PatientProfileDao;

@RestController
@CrossOrigin
public class PatientProfileController {

    @Autowired
    PatientProfileDao patientDao;

    @PostMapping("/addFamilyMember")
	public ResponseBean<PatientProfileBean> addPatient(@RequestBody PatientProfileBean patientBean) {

		patientDao.addPatientProfile(patientBean);
		ResponseBean<PatientProfileBean> response = new ResponseBean<>();
		response.setData(patientBean);
		response.setMsg("Patient Added successfully...!!");
		response.setStatus(200);
		return response;

	}

    @GetMapping("/listPatient")
    public ResponseBean<java.util.List<PatientProfileBean>> listPatient() {
        ResponseBean<java.util.List<PatientProfileBean>> response = new ResponseBean<>();

        java.util.List<PatientProfileBean> patientBean = patientDao.listPatient();
        response.setData(patientBean);
        response.setMsg("Patient List Display..!!!!");
        response.setStatus(201);
        return response;
    }
    
    @GetMapping("/listUserPatient/{userid}")
    public ResponseBean<java.util.List<PatientProfileBean>> listUserPatient(@PathVariable("userid") int userid) {
        ResponseBean<java.util.List<PatientProfileBean>> response = new ResponseBean<>();
        System.out.println("===>"+userid);
        java.util.List<PatientProfileBean> userPatientBean = patientDao.listUserPatient(userid);
        response.setData(userPatientBean);
        response.setMsg("User Patient Display..!!!!");
        response.setStatus(201);
        return response;
    }

    @DeleteMapping("/addPatient/{PatientId}")
    public ResponseBean<PatientProfileBean> deletePathology(@PathVariable("PatientId") int PatientId) {

        ResponseBean<PatientProfileBean> response = new ResponseBean<>();
        patientDao.deletePatient(PatientId);
        response.setMsg("Patient Deleted Successfully..!!");
        response.setStatus(200);
        return response;
    }
    
    @GetMapping("/getuserPatient/{userId}")
	public ResponseBean<PatientProfileBean> getUser(@PathVariable("userId") int userId, PatientProfileBean bean) {
		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();
		bean = patientDao.getPatientById(userId);
		responseBean.setData(bean);
		responseBean.setMsg("Single User Return");
		responseBean.setStatus(200);
		return responseBean;
	}
    
    @GetMapping("/getEditUserPatient/{patientid}")
	public ResponseBean<PatientProfileBean> getEditUserPatient(@PathVariable("patientid") int patientid, PatientProfileBean bean) {
		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();
		bean = patientDao.getEditUserPatient(patientid);
		responseBean.setData(bean);
		responseBean.setMsg("Single User Return");
		responseBean.setStatus(200);
		return responseBean;
	}
    
    @GetMapping("/getPatientprofile/{userId}")
	public ResponseBean<PatientProfileBean> getProfile(@PathVariable("userId") int userId, PatientProfileBean bean) {

		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();
		bean = patientDao.getPatientProfileById(userId);
		responseBean.setData(bean);
		responseBean.setMsg("Single User Return");
		responseBean.setStatus(200);

		return responseBean;
	}
    
    
    @GetMapping("/getFamilyMember/{patientid}")
	public ResponseBean<PatientProfileBean> getFamilyMember(@PathVariable("patientid") int patientid, PatientProfileBean bean) {

		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();
		bean = patientDao.getFamilyMember(patientid);
		responseBean.setData(bean);
		responseBean.setMsg("Single User Return");
		responseBean.setStatus(200);

		return responseBean;
	}
    
    @PutMapping("/updateFamilyMember")
    public ResponseBean<PatientProfileBean> updateFamilyMember(@RequestBody PatientProfileBean patientBean) {
        System.out.println("Edit Family Memember Email => "+patientBean.getEmail());
    	patientDao.updateFamilyMember(patientBean);
        ResponseBean<PatientProfileBean> response = new ResponseBean<>();
        response.setData(patientBean);
        response.setMsg("Patient Updated Successfully..!!");
        return response;
    }


}
