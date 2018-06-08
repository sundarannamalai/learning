package com.currencyexchangeservice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 19/05/18
 * Time: 11:57 PM
 */
@RestController
public class CurrencyExchangeController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

  private final Environment environment;
  private ExchangeValueRepository repository;

  @Autowired
  public CurrencyExchangeController(Environment environment, ExchangeValueRepository repository) {
    this.environment = environment;
    this.repository = repository;
  }

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public ExchangeValue exchangeValue(@PathVariable String from, @PathVariable String to) {
    ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
    exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
    LOGGER.info("{}", exchangeValue);
    return exchangeValue;
  }
}
