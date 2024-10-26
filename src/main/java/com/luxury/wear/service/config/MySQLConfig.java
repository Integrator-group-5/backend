package com.luxury.wear.service.config;

import com.luxury.wear.service.repository.LoadData;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mysql")
@AllArgsConstructor
public class MySQLConfig {

    private final LoadData loadData;

    @PostConstruct
    public void loadData() {
        loadData.loadData();
    }

    @PostConstruct
    public void configureDatasource() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DB_USERNAME"));
        System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));
        System.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    }
}
