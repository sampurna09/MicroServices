package com.currencycalculation.facade;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.currencycalculation.model.CalculatedAmount;

//@FeignClient(name="currency-exchange-service",url="http://localhost:8001/")
@FeignClient(name="currency-exchange-service")
@LoadBalancerClient(name="currency-exchange-service")
public interface CurrencyExchangeProxy {
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CalculatedAmount retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
		
	
}
