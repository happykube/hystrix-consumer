package com.springcloud;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class CafeController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CafeService cafeService;
	
	@GetMapping("/delay/{param}")
	@ApiOperation(value="지연이 있는 서비스")
	public List<String> getCoffees(@PathVariable String param) {
		log.info("### Received: /delay/"+param);
		
		List<String> list = cafeService.getCoffees(param);
		
		log.info("### Sent: " + list.toString());
		return list;
	}
}
