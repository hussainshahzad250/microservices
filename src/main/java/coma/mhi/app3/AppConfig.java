package coma.mhi.app3;

import java.util.logging.Logger;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EntityScan("com.amhi.*")
@EnableJpaRepositories("com.amhi.*")
@PropertySource("classpath:database.properties")
public class AppConfig {

	protected Logger logger;

	public AppConfig() {
		logger = Logger.getLogger(getClass().getName());
	}
}
