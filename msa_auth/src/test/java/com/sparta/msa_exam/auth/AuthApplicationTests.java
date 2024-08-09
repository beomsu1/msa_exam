package com.sparta.msa_exam.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class AuthApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {
	}

	@Test
	void connectionTest(){

        try {
            dataSource.getConnection();
			System.out.println("연결 완료");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
