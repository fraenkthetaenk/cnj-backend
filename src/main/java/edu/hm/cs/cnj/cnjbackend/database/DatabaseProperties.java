package edu.hm.cs.cnj.cnjbackend.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DatabaseProperties {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    
    @Autowired
    private DataSource dataSource;

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
    
    public String getRecords()
    {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS infos (info timestamp)");
            stmt.executeUpdate("INSERT INTO info VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT info FROM infos");

            String output = "";
            while (rs.next()) {
              output += "Read from DB: " + rs.getTimestamp("tick") + "\n";
            }
            return output;
          } catch (Exception e) {
            return "error";
          } 
        
    }
}
