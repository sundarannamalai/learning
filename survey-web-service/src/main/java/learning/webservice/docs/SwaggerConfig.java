package learning.webservice.docs;

import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.file.Path;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 06/05/18
 * Time: 3:21 PM
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final Contact DEFAULT_CONTACT = new Contact("Sundar Annamalai", "", "");
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Survey web service documentation", "It's a sample webservice built for learning purpose.", "1.0", "urn:tos",
			DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.consumes(Sets.newHashSet("application/xml", "application/json"))
				.produces(Sets.newHashSet("application/xml", "application/json"))
				.apiInfo(DEFAULT_API_INFO)
				.select()
				.apis(RequestHandlerSelectors.any())//<5>
				.paths(Predicates.not(PathSelectors.regex("/error.*")))//<6>, regex must be in double quotes.
				.paths(Predicates.not(PathSelectors.regex("/actuator.*")))
				.build()
				;
	}
}
