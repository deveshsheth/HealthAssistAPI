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

import com.bean.ResponseBean;
import com.bean.userPharmacyBean;
import com.dao.userPharmacyDao;

@RestController
@CrossOrigin
public class userPharmacyController {

    @Autowired
    userPharmacyDao userPharmacyDao;

    @PostMapping("/adduserPharmacy")
    public ResponseBean<userPharmacyBean> addUserPharmacy(@RequestBody userPharmacyBean userPharmacyBean) {
        userPharmacyDao.addUserPharmacy(userPharmacyBean);
        ResponseBean<userPharmacyBean> response = new ResponseBean<>();
        response.setData(userPharmacyBean);
        response.setMsg("User Pharmacy Added Successfully...!!");
        response.setStatus(200);
        return response;
    }
    
   
    
    @GetMapping("/listUserPharmacy/{userid}")
    public ResponseBean<java.util.List<userPharmacyBean>> listuserPharmacy(@PathVariable("userid") int userid) {
        ResponseBean<java.util.List<userPharmacyBean>> response = new ResponseBean<>();

        java.util.List<userPharmacyBean> userPharmacyBean = userPharmacyDao.listUserPharmacy(userid);
        response.setData(userPharmacyBean);
        response.setMsg("List Display..!!!!");
        response.setStatus(201);
        return response;
    }
    
    @DeleteMapping("/addUserPharmacy/{pharmacyid}")
    public ResponseBean<userPharmacyBean> deleteUserPharmacy(@PathVariable("pharmacyid") int pharmacyid) {

        ResponseBean<userPharmacyBean> response = new ResponseBean<>();
        userPharmacyDao.deleteUserPharmacy(pharmacyid);
        response.setMsg("User Pharmacy Deleted Successfully..!!");
        response.setStatus(200);
        return response;
    }


    @GetMapping("/getUserPharmacy/{pharmacyId}")
    public ResponseBean<userPharmacyBean> getUser(@PathVariable("pharmacyId") int pharmacyId, userPharmacyBean bean) {

        ResponseBean<userPharmacyBean> responseBean = new ResponseBean<>();
        bean = userPharmacyDao.getPharmacyById(pharmacyId);
        responseBean.setData(bean);
        responseBean.setMsg("Single User Pharmacy Return");
        responseBean.setStatus(200);

        return responseBean;
    }


    @PutMapping("/updateuserPharmacy")
    public ResponseBean<userPharmacyBean> updateUserPharmacy(@RequestBody userPharmacyBean userPharmacyBean) {
        userPharmacyDao.updateUserPharmacy(userPharmacyBean);
        ResponseBean<userPharmacyBean> response = new ResponseBean<>();
        response.setData(userPharmacyBean);
        response.setMsg("User Pharmacy Updated Successfully..!!");
        return response;
    }

}
