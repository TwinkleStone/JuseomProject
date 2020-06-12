package com.team.juseom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.juseom.service.JuseomFacade;

@Controller
public class InsertRatingController {
	
	@Autowired
	private JuseomFacade juseom;

	
}
