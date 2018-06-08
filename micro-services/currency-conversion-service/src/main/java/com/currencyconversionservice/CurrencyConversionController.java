package com.currencyconversionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 19/05/18
 * Time: 11:57 PM
 */
@RestController
public class CurrencyConversionController {

  private final Environment environment;
  private final CurrencyExchangeServiceProxy proxy;
  private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConversionController.class);

  @Autowired
  public CurrencyConversionController(Environment environment, CurrencyExchangeServiceProxy proxy) {
    this.environment = environment;
    this.proxy = proxy;
  }

  @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
  public ConvertedValue exchangeValue(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("from", from);
    uriVariables.put("to", to);
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<ConvertedValue> responseEntity = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", ConvertedValue.class, uriVariables);
    ConvertedValue convertedValue = responseEntity.getBody();
    return new ConvertedValue(1L, from, to, convertedValue.getConversionMultiple(), quantity, quantity.multiply(convertedValue.getConversionMultiple()), convertedValue.getPort());
  }

  @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
  public ConvertedValue exchangeValueFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
    ConvertedValue convertedValue = proxy.exchangeValue(from, to);
    LOGGER.info("Converted Value = {}", convertedValue);
    return new ConvertedValue(1L, from, to, convertedValue.getConversionMultiple(), quantity, quantity.multiply(convertedValue.getConversionMultiple()), convertedValue.getPort());
  }

}
