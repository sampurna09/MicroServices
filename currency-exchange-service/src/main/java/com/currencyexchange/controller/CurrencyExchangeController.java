package com.currencyexchange.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.currencyexchange.model.ExchangeValue;
import com.currencyexchange.repository.ExchangeValueRepository;


@RestController
public class CurrencyExchangeController {
	
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository exchangeValueRepository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ResponseEntity<ExchangeValue> retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
		logger.info("Exchange Value : {}",exchangeValue);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return new ResponseEntity<ExchangeValue>(exchangeValue, HttpStatus.OK);
	}

	@PostMapping(value = "/saveCurrencyExchangeValue")
	public ResponseEntity<ExchangeValue> saveCurrencyExchangeValue(@RequestBody ExchangeValue exchangeValue) {
		return new ResponseEntity<ExchangeValue>(exchangeValueRepository.save(exchangeValue), HttpStatus.CREATED);
	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<ExchangeValue>> getAllExchangeValues() {
		return new ResponseEntity<List<ExchangeValue>>(exchangeValueRepository.findAll(), HttpStatus.OK);
	}

}
