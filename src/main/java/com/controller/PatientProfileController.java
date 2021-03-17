package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @GetMapping("/listPatient")
    public ResponseBean<java.util.List<PatientProfileBean>> listPatient() {
        ResponseBean<java.util.List<PatientProfileBean>> response = new ResponseBean<>();

        java.util.List<PatientProfileBean> patientBean = patientDao.listPatient();
        response.setData(patientBean);
        response.setMsg("Patient List Display..!!!!");
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

    @PutMapping("/updatePatient")
    public ResponseBean<PatientProfileBean> updatePathology(@RequestBody PatientProfileBean patientBean) {
        patientDao.updatePatient(patientBean);
        ResponseBean<PatientProfileBean> response = new ResponseBean<>();
        response.setData(patientBean);
        response.setMsg("Patient Updated Successfully..!!");
        return response;
    }


}
