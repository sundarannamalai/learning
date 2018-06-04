package com.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 02/06/18
 * Time: 10:57 PM
 */
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

  @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
  ConvertedValue exchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
