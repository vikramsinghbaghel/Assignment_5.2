package com.nagarro.employeemanagemet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.employeemanagemet.dao.HRDao;
import com.nagarro.employeemanagemet.entities.HRDetails;

@Controller
public class HRController {
	
	@Autowired
	HRDao dao;
	
	@RequestMapping("/")
	public String home()
	{
		return "Home";
	}
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "welcome";
	}
	@RequestMapping("/uploadEmployee")
	public String uploadEmployee()
	{
		return "uploadEmployee";
	}
	@RequestMapping("Home")
	public String login()
	{
		return "Home";
	}
	@RequestMapping("validate")
	public String validate(HttpSession session,@RequestParam String userid,@RequestParam String password)
	{
	  //  System.out.println(userid+" "+password);//just for check
		List<HRDetails> hrs=dao.findAll();
		for(HRDetails hr:hrs)
		{
			if((hr.getUserid().equals(userid)) &&(hr.getPassword().equals(password)))
			{
				session.setAttribute("name",userid);
				return "redirect:/employees";
			}
		 }
		 session.setAttribute("message", "Wrong user-id password!");
	     return "Home";
	}
	
}