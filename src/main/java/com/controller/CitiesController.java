package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.CitiesBean;
import com.bean.ResponseBean;
import com.dao.CitiesDao;

@RestController
public class CitiesController {

	@Autowired
	CitiesDao citiesDao;
	
	@PostMapping("/addCities")
	public ResponseBean<CitiesBean> addCities(@RequestBody CitiesBean citiesBean){
		citiesDao.addCities(citiesBean);
		ResponseBean<CitiesBean> response = new ResponseBean<>();
		response.setData(citiesBean);
		response.setMsg("Cities Inserted successfully...!!");
		response.setStatus(200);
		return response;
		
	}
	
	@GetMapping("/listCities")
	public ResponseBean<java.util.List<CitiesBean>> listCities()
	{
		ResponseBean<java.util.List<CitiesBean>> response = new ResponseBean<>();

		java.util.List<CitiesBean> roleBean = citiesDao.listCities();
		response.setData(roleBean);
		response.setMsg("Cities List Display..!!!!");
		response.setStatus(201);
		return response;
	}
	@DeleteMapping("/addCities/{CityId}")
	public ResponseBean<CitiesBean> deleteCities(@PathVariable("CityId")int CityId){
		
		ResponseBean<CitiesBean> response = new ResponseBean<>();
		citiesDao.deleteCities(CityId);
		response.setMsg("Deleted Successfully..!!");
		response.setStatus(200);
		return response;
	}
	
	@PutMapping("/updateCities")
	public ResponseBean<CitiesBean> updateCities(@RequestBody CitiesBean cityBean){
		citiesDao.updateCities(cityBean);
		ResponseBean<CitiesBean> response = new ResponseBean<>();
		response.setData(cityBean);
		response.setMsg("Cities Updated Successfully..!!");
		
		return response;
	}
	
	
}
