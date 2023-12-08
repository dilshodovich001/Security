package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class SecurityApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(LocalDateTime.now());
	}

}
