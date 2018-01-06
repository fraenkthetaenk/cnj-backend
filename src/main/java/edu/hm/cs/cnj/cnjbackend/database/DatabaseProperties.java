package edu.hm.cs.cnj.cnjbackend.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DatabaseProperties {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    //
     @Autowired
     private DataSource dataSource;

    public Connection getConnection() {
        DataSource dataSource = null;
        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            dataSource = new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(jdbcUrl);
            dataSource = new HikariDataSource(config);
        }
        try (Connection connection = dataSource.getConnection()) {
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

     @Bean
     public DataSource dataSource() throws SQLException {
     if (dbUrl == null || dbUrl.isEmpty()) {
     return new HikariDataSource();
     } else {
     HikariConfig config = new HikariConfig();
     config.setJdbcUrl(dbUrl);
     return new HikariDataSource(config);
     }
     }

}
