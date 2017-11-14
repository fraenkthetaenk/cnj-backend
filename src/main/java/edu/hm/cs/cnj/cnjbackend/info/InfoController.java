package edu.hm.cs.cnj.cnjbackend.info;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class InfoController {
	@Autowired
	InfoProperties InfoProp; 
	
	@RequestMapping("/info")
	public Info greeting() {
		Info result = new Info();		
		result.setMessage(InfoProp.getMessage());
		return result;
	}
}