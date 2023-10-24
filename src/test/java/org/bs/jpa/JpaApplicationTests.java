package org.bs.jpa;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class JpaApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {

		try {
			dataSource.getConnection();

			log.info("DB Connection--------------");
			log.info("DB Connection--------------");
			log.info("DB Connection--------------");

			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
