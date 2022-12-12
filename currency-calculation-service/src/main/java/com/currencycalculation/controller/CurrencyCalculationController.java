package com.currencycalculation.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.currencycalculation.facade.CurrencyExchangeProxy;
import com.currencycalculation.model.CalculatedAmount;

import jakarta.ws.rs.GET;

@RestController
public class CurrencyCalculationController {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	
	Logger logger = LoggerFactory.getLogger(CurrencyCalculationController.class);
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	

	@GetMapping(value = "currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public ResponseEntity<CalculatedAmount> calculatedAmount(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CalculatedAmount> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8001/currency-exchange/from/{from}/to/{to}", CalculatedAmount.class, uriVariables);
		CalculatedAmount calculatedAmount = responseEntity.getBody();
		return new ResponseEntity<CalculatedAmount>(
				new CalculatedAmount(
						calculatedAmount.getId(),
						calculatedAmount.getFrom(),
						calculatedAmount.getTo(),
						calculatedAmount.getConversionMultiple(),
						quantity,
						quantity.multiply(calculatedAmount.getConversionMultiple()),
						calculatedAmount.getPort()
						),
				HttpStatus.OK);
	}
	
	
	@GetMapping(value = "currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public ResponseEntity<CalculatedAmount> calculatedAmountFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CalculatedAmount calculatedAmount = currencyExchangeProxy.retrieveExchangeValue(from, to);
		logger.info("CalculatedAmount : {}",calculatedAmount);
		return new ResponseEntity<CalculatedAmount>(
				new CalculatedAmount(
						calculatedAmount.getId(),
						calculatedAmount.getFrom(),
						calculatedAmount.getTo(),
						calculatedAmount.getConversionMultiple(),
						quantity,
						quantity.multiply(calculatedAmount.getConversionMultiple()),
						calculatedAmount.getPort()
						),
				HttpStatus.OK);
	}
	

}
