package com.currencyexchangeservice.web;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 20/05/18
 * Time: 6:55 PM
 */

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
  ExchangeValue findByFromAndTo(String from, String to);
}
