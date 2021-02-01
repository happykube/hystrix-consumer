package com.springcloud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CafeService {
	@Autowired
	private CoffeeDomain coffeeDomain;
	
	public List<String> getCoffees(String param) {
		return coffeeDomain.getCoffees(param);
	}
	
}
