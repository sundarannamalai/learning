package com.limitservice.controller;

import com.limitservice.bean.LimitConfiguration;
import com.limitservice.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 10/05/18
 * Time: 11:24 PM
 */
@RestController
public class LimitsConfigurationController {

  private Configuration configuration;

  @Autowired
  public LimitsConfigurationController(Configuration configuration) {
    this.configuration = configuration;
  }

  @GetMapping("/limits")
  public LimitConfiguration retrieveLimits() {
    return new LimitConfiguration(configuration.getMax(), configuration.getMin());
  }
}
