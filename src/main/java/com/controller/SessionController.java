package com.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.DoctorProfileBean;
import com.bean.LoginBean;
import com.bean.ResponseBean;

import com.bean.UserBean;
import com.dao.SessionDao;
import com.services.MailerService;



@RestController
public class SessionController {
	@Autowired
	SessionDao signupDao;
	
	@Autowired
	com.services.OtpService OtpService;
	
	@Autowired
	MailerService mailerService;
	 
	@PostMapping("/Signup")
	public ResponseBean<UserBean> insertUser(@RequestBody UserBean userBean){
		userBean.setOtp(OtpService.generateOtp());
		mailerService.sendOtpForUserVerification(userBean);
		signupDao.insertUser(userBean);
		ResponseBean<UserBean> response = new ResponseBean<>();
		
		response.setData(userBean);
		response.setMsg("user signup successfully....!!");
		response.setStatus(200);
		return response;
	}
	
	@PostMapping("/doctorsignup")
	public ResponseBean<UserBean> doctorSignup(@RequestBody DoctorProfileBean doctorProfileBean) {

		doctorProfileBean.setStatus(UserBean.KYC_DOCTOR);
		doctorProfileBean.setStatusReason("Your KYS is pending Our Team Will Contact You Soon..");	
		mailerService.sendDoctorRegisterMail(doctorProfileBean);
		signupDao.addDoctorProfile(doctorProfileBean);
		
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		responseBean.setData(doctorProfileBean);
		responseBean.setMsg("doctor successfully signup!!");
		responseBean.setStatus(200);

		return responseBean;
	}
	
	
	@GetMapping("/listUser")
	public ResponseBean<java.util.List<UserBean>> listRole()
	{
		ResponseBean<java.util.List<UserBean>> response = new ResponseBean<>();

		java.util.List<UserBean> userBean = signupDao.listUser();
		response.setData(userBean);
		response.setMsg("List Display..!!!!");
		response.setStatus(201);
		return response;
	}
	
	@PostMapping("/login")
	public ResponseBean<UserBean> Login(@RequestBody LoginBean loginBean){
		UserBean signup = null;
		ResponseBean<UserBean> response = new ResponseBean<>();
		signup = signupDao.login(loginBean.getEmail(),loginBean.getPassword());
		response.setData(signup);
		response.setMsg("user login");
		 response.setStatus(200);
		 
		 return response;
	}
	
	@DeleteMapping("deleteSignup/{userId}")
	public ResponseBean<UserBean> deleteSignup(@PathVariable("userId") int userId) {

		signupDao.deleteSignup(userId);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		responseBean.setMsg("Signup Deleted!!");
		responseBean.setStatus(201);

		return responseBean;

	}
	
	@PutMapping("updateSignup")
	public ResponseBean<UserBean> updateSignup(UserBean signupBean) {

		signupDao.updateSignup(signupBean);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		responseBean.setData(signupBean);
		responseBean.setMsg("Signup Updated!!");
		responseBean.setStatus(201);

		return responseBean;
	}
	
}
