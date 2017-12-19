package edu.hm.cs.cnj.cnjbackend.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/db")
public class DatabaseController {
        @Autowired
        DatabaseProperties properties;
        
        @Value("${spring.datasource.url}")
        private String dbUrl;
        
  
        public String getRecords()
        {
            try (Connection connection = properties.dataSource(dbUrl).getConnection()) {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS infos (info timestamp)");
                stmt.executeUpdate("INSERT INTO info VALUES (now())");
                ResultSet rs = stmt.executeQuery("SELECT info FROM infos");

                String output = "";
                while (rs.next()) {
                  output += "Read from DB: " + rs.getTimestamp("info") + "\n";
                }
                return output;
              } catch (Exception e) {
                return "error";
              } 
            
        }
        
        @GetMapping
        public Database getDatabase(){
            Database database = new Database();
            database.setMessage(getRecords());
            return database;
        }
        
}
