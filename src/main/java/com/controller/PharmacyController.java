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

import com.bean.PharmacyBean;
import com.bean.ResponseBean;
import com.dao.PharmacyDao;

@RestController
@CrossOrigin
public class PharmacyController {

    @Autowired
    PharmacyDao pharmacyDao;

    @PostMapping("/addPharmacy")
    public ResponseBean<PharmacyBean> addPharmacy(@RequestBody PharmacyBean pharmacyBean) {
        pharmacyDao.addPathology(pharmacyBean);
        ResponseBean<PharmacyBean> response = new ResponseBean<>();
        response.setData(pharmacyBean);
        response.setMsg("Pharmacy Added successfully...!!");
        response.setStatus(200);
        return response;

    }

    @GetMapping("/listPharmacy")
    public ResponseBean<java.util.List<PharmacyBean>> listPharmacy() {
        ResponseBean<java.util.List<PharmacyBean>> response = new ResponseBean<>();

        java.util.List<PharmacyBean> pharmacyBean = pharmacyDao.listPharmacy();
        response.setData(pharmacyBean);
        response.setMsg("Pharmacy List Display..!!!!");
        response.setStatus(201);
        return response;
    }

    @DeleteMapping("/addPharmacy/{pharmacyid}")
    public ResponseBean<PharmacyBean> deletePharmacy(@PathVariable("pharmacyid") int pharmacyid) {

        ResponseBean<PharmacyBean> response = new ResponseBean<>();
        pharmacyDao.deletePharmacy(pharmacyid);
        response.setMsg("Pharmacy Deleted Successfully..!!");
        response.setStatus(200);
        return response;
    }


    @GetMapping("/getpharmacy/{pharmacyId}")
    public ResponseBean<PharmacyBean> getUser(@PathVariable("pharmacyId") int pharmacyId, PharmacyBean bean) {

        ResponseBean<PharmacyBean> responseBean = new ResponseBean<>();
        bean = pharmacyDao.getPharmacyById(pharmacyId);
        responseBean.setData(bean);
        responseBean.setMsg("Single User Return");
        responseBean.setStatus(200);

        return responseBean;
    }

    @PutMapping("/updatePharmacy")
    public ResponseBean<PharmacyBean> updatePharmacy(@RequestBody PharmacyBean pharmacyBean) {
        pharmacyDao.updatePharmacy(pharmacyBean);
        ResponseBean<PharmacyBean> response = new ResponseBean<>();
        response.setData(pharmacyBean);
        response.setMsg("Pharmacy Updated Successfully..!!");
        return response;
    }


}
