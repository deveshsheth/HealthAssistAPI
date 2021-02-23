package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.DoctorProfileBean;
import com.bean.LoginBean;
import com.bean.ResponseBean;

import com.bean.UserBean;
import com.dao.OtpDao;
import com.dao.SessionDao;
import com.services.MailerService;

import net.bytebuddy.asm.Advice.This;

@RestController
public class SessionController {
	@Autowired
	SessionDao signupDao;

	@Autowired
	OtpDao otpDao;

	@Autowired
	com.services.OtpService OtpService;

	@Autowired
	MailerService mailerService;

	@PostMapping("/Signup")
	public ResponseBean<UserBean> insertUser(@RequestBody UserBean userBean) {
		ResponseBean<UserBean> response = new ResponseBean<>();
		
		if(signupDao.getUserByEmail(userBean.getEmail()) != null) {
			response.setMsg("Email Already registered");
			response.setStatus(201);
		}else {
			userBean.setOtp(OtpService.generateOtp());
			mailerService.sendOtpForUserVerification(userBean);
			signupDao.insertUser(userBean);
			

			response.setData(userBean);
			response.setMsg("user signup successfully....!!");
			response.setStatus(200);
		}
		
		
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
	public ResponseBean<java.util.List<UserBean>> listUser() {
		ResponseBean<java.util.List<UserBean>> response = new ResponseBean<>();

		java.util.List<UserBean> userBean = signupDao.listUser();
		response.setData(userBean);
		response.setMsg("List Display..!!!!");
		response.setStatus(201);
		return response;
	}

	@PostMapping("/login")
	public ResponseBean<UserBean> Login(@RequestBody LoginBean loginBean) {
		UserBean signup = null;
		System.out.println(loginBean.getEmail());
		System.out.println(loginBean.getPassword());
		ResponseBean<UserBean> response = new ResponseBean<>();
		signup = signupDao.login(loginBean.getEmail(), loginBean.getPassword());
		if(signup ==null) {
			response.setMsg("Invalid Credentails..!!");
			response.setStatus(201);
		}
		else {
			response.setData(signup);
			response.setMsg("user login....!!");
			response.setStatus(200);
		}
		

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

	@PutMapping("/updateSignup")
	public ResponseBean<UserBean> updateSignup(@RequestBody UserBean signupBean) {

		signupDao.updateSignup(signupBean);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		responseBean.setData(signupBean);
		responseBean.setMsg("Signup Updated!!");
		responseBean.setStatus(201);

		return responseBean;
	}

	@GetMapping("/resetpassword/{email}")
	public ResponseBean<UserBean> sendOtpForResetPassword(@PathVariable("email") String email) {
		System.out.println("reset password call...");
		UserBean userBean = signupDao.getUserByEmail(email);
		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		responseBean.setData(userBean);

		if (userBean == null) {

			responseBean.setMsg("Invalid Email Address");
			responseBean.setStatus(201);

		} else {

			String otp = OtpService.generateOtp();
			userBean.setOtp(otp);
			otpDao.updateOtp(email, otp);
			mailerService.sendOtpForForgetPassword(userBean);

			responseBean.setMsg("Please Check Email for OTP");
			responseBean.setStatus(200);

		}

		return responseBean;
	}

	@GetMapping("setnewpassword/{otp}/{password}/{email}")
	public ResponseBean<UserBean> setNewPasswordUsingOtp(@PathVariable("otp") String otp,
			@PathVariable("password") String password, @PathVariable("email") String email) {
		System.out.println("Setnewpassword....!!!");
		
		System.out.println(otp);
		System.out.println(password);
		System.out.println(email);
		System.out.println("=====");
		UserBean dbUser = signupDao.getUserByEmail(email);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (dbUser == null) {

			responseBean.setMsg("User not found");
			responseBean.setStatus(201);

		} else {

			dbUser.setPassword(password);
			if (dbUser.getOtp().equals(otp)) {
				otpDao.updateOtp(email, "");
				signupDao.updatePassword(dbUser);
				mailerService.sendMailForPasswordUpdate(dbUser);
				responseBean.setMsg("Password Update...");
				responseBean.setStatus(200);
			} else {
				responseBean.setMsg("Invalid Otp....");
				responseBean.setStatus(201);
			}

		}

		return responseBean;
	}

}
