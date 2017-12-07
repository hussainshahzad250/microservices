package com.amhi.app1;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
@ComponentScan
@EntityScan("com.amhi.*")
@EnableJpaRepositories("com.amhi.*")
@PropertySource("classpath:client1.properties")
public class AccountsConfiguration {

	protected Logger logger;

	public AccountsConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		DataSource dataSource = (new EmbeddedDatabaseBuilder())
				.addScript("classpath:testdb/schema.sql")
				.addScript("classpath:testdb/data.sql").build();

		logger.info("dataSource = " + dataSource);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> accounts = jdbcTemplate
				.queryForList("SELECT number FROM T_ACCOUNT");
		logger.info("System has " + accounts.size() + " accounts");

		Random rand = new Random();

		for (Map<String, Object> item : accounts) {
			String number = (String) item.get("number");
			BigDecimal balance = new BigDecimal(rand.nextInt(10000000) / 100.0)
					.setScale(2, BigDecimal.ROUND_HALF_UP);
			jdbcTemplate.update(
					"UPDATE T_ACCOUNT SET balance = ? WHERE number = ?",
					balance, number);
		}

		return dataSource;
	}
}
