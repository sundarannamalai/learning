package learning.spring.services.netflixzuulapigatewayserver;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 04/06/18
 * Time: 11:10 PM
 */
@Component
public class ZuulLoggingFilter extends ZuulFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(ZuulLoggingFilter.class);

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
    LOGGER.info("Request : {}, Uri : {}", request, request.getRequestURI());
    return null;
  }
}
