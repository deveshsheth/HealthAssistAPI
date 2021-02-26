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

import com.bean.PathologyBean;
import com.bean.ResponseBean;
import com.dao.PathologyDao;

@RestController
@CrossOrigin
public class PathologyController {

	@Autowired
	PathologyDao pathologyDao;
	
	@PostMapping("/addPathology")
	public ResponseBean<PathologyBean> addPathology(@RequestBody PathologyBean pathologyBean){
		pathologyDao.addPathology(pathologyBean);
		ResponseBean<PathologyBean> response = new ResponseBean<>();
		response.setData(pathologyBean);
		response.setMsg("Pathology Added successfully...!!");
		response.setStatus(200);
		return response;
		
	}
	
	@GetMapping("/listPathology")
	public ResponseBean<java.util.List<PathologyBean>> listPathology()
	{
		ResponseBean<java.util.List<PathologyBean>> response = new ResponseBean<>();

		java.util.List<PathologyBean> pathologyBean = pathologyDao.listPathology();
		response.setData(pathologyBean);
		response.setMsg("Pathology List Display..!!!!");
		response.setStatus(201);
		return response;
	}
	
	@DeleteMapping("/addPathology/{PathologyId}")
	public ResponseBean<PathologyBean> deletePathology(@PathVariable("PathologyId")int PathologyId){
		
		ResponseBean<PathologyBean> response = new ResponseBean<>();
		pathologyDao.deletePathology(PathologyId);
		response.setMsg("Pathology Deleted Successfully..!!");
		response.setStatus(200);
		return response;
	}
	
	@GetMapping("/getpathology/{PathologyId}")
	public ResponseBean<PathologyBean> getUser(@RequestBody @PathVariable("PathologyId") int pathologyId){
		
		ResponseBean<PathologyBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(pathologyDao.getPathologyById(pathologyId));
		responseBean.setMsg("Single User Return");
		responseBean.setStatus(200);
	
		return responseBean;
	}
	
	@PutMapping("/updatePathology")
	public ResponseBean<PathologyBean> updatePathology(@RequestBody PathologyBean pathologyBean){
		pathologyDao.updatePathology(pathologyBean);
		ResponseBean<PathologyBean> response = new ResponseBean<>();
		response.setData(pathologyBean);
		response.setMsg("Pathology Updated Successfully..!!");
		return response;
	}
}
