package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.FeedbackBean;
import com.bean.ResponseBean;
import com.dao.FeedbackDao;

@CrossOrigin
@RestController
public class FeedbackController {
	
	@Autowired
	FeedbackDao feedbackDao;
	
	@PostMapping("/addFeedback")
	public ResponseBean<FeedbackBean> addPrescription(@RequestBody FeedbackBean feedbackBean) {
		feedbackDao.addFeedback(feedbackBean);

		ResponseBean<FeedbackBean> responseBean = new ResponseBean<>();

		responseBean.setData(feedbackBean);
		responseBean.setMsg("Feedback Added!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listFeedback")
	public ResponseBean<List<FeedbackBean>> listPrescription() {

		List<FeedbackBean> feedbackBean = feedbackDao.listFeedback();

		ResponseBean<List<FeedbackBean>> responseBean = new ResponseBean<>();

		responseBean.setData(feedbackBean);
		responseBean.setMsg("Feedback List!!");
		responseBean.setStatus(200);

		return responseBean;
	}

}
