package com.fizikovnet.hw.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "db.properties")
public class AppConfig {

    @Value("${datasource.user}")
    private String user;
    @Value("${datasource.password}")
    private String password;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.driverClassName}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(user);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

}
