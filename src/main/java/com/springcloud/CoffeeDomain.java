package com.springcloud;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@RefreshScope
public class CoffeeDomain {
	@Autowired
	private RestTemplate coffeeRestTemplate;
	
	@Value("${hystrix_producer_host:http://localhost:8013}")
	private String hystrixProducerHost;
	
	@HystrixCommand(fallbackMethod="getCoffeeFallback")
	public List<String> getCoffees(String param) {
		String url = hystrixProducerHost+"/api/coffees/"+param;
		System.out.println("call url=>"+url);
		
		return coffeeRestTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List <String>>() {})
				.getBody();
				
	}
	
	public List<String> getCoffeeFallback(String param, Throwable t) {
		System.err.println("###### ERROR =>"+t.toString());
		return Collections.emptyList();
	}
}
