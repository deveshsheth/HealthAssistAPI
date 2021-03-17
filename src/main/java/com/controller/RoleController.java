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
import com.bean.RoleBean;
import com.dao.RoleDao;

@RestController
@CrossOrigin
public class RoleController {
    @Autowired
    RoleDao roleDao;

    @PostMapping("/addRole")
    public ResponseBean<RoleBean> addRole(@RequestBody RoleBean roleBean) {
        roleDao.addRole(roleBean);
        ResponseBean<RoleBean> response = new ResponseBean<>();
        response.setData(roleBean);
        response.setMsg("Inserted successfully...!!");
        response.setStatus(200);
        return response;

    }

    @GetMapping("/listRole")
    public ResponseBean<java.util.List<RoleBean>> listRole() {
        ResponseBean<java.util.List<RoleBean>> response = new ResponseBean<>();

        java.util.List<RoleBean> roleBean = roleDao.listRole();
        response.setData(roleBean);
        response.setMsg("List Display..!!!!");
        response.setStatus(201);
        return response;
    }

    @DeleteMapping("/addRole/{RoleId}")
    public ResponseBean<RoleBean> deleteRole(@PathVariable("RoleId") int RoleId) {

        ResponseBean<RoleBean> response = new ResponseBean<>();
        roleDao.deleteRole(RoleId);
        response.setMsg("Deleted Successfully..!!");
        response.setStatus(200);
        return response;
    }

    @PutMapping("/updateRole")
    public ResponseBean<RoleBean> updateRole(@RequestBody RoleBean roleBean) {
        roleDao.updateRole(roleBean);
        ResponseBean<RoleBean> response = new ResponseBean<>();
        response.setData(roleBean);
        response.setMsg("Updated Successfully..!!");

        return response;
    }
}
