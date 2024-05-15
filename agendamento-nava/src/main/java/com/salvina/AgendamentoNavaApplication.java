package com.salvina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "com.salvina.repository")
@EntityScan("com.salvina.*")
@ComponentScan(basePackages = "com.salvina")
public class AgendamentoNavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoNavaApplication.class, args);
	}

}
