package com.nagarro.employeemanagemet.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.employeemanagemet.dao.HRDao;
import com.nagarro.employeemanagemet.entities.Employee;

@RestController
public class ClientController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	HRDao dao;

	@RequestMapping("/employees")
	public ModelAndView getemployee(HttpEntity<Object> requestEntity) {
		ResponseEntity<List> responseEntity = restTemplate.exchange("http://localhost:9090/employees", HttpMethod.GET,
				requestEntity, List.class);
		List user = responseEntity.getBody();
		ModelAndView modelView = new ModelAndView("welcome");
		modelView.addObject("allEmployee", user);
		modelView.setViewName("welcome");
		return modelView;
	}

	@PostMapping("/employees")
	public ModelAndView createProducts(Employee employee) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);
		restTemplate.exchange("http://localhost:9090/employees", HttpMethod.POST, entity, String.class).getBody();
		ModelAndView modelView = new ModelAndView("uploaded");
		return modelView;
	}
}
