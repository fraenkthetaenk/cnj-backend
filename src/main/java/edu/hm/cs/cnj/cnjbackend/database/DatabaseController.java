package edu.hm.cs.cnj.cnjbackend.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@RestController
@RequestMapping("/db")
public class DatabaseController {

    // @Autowired
    // DatabaseProperties properties;
    //

    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    //
    // @Autowired
    // private DataSource dataSource;

    public String getRecords() {

        DataSource dataSource = null;
        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            dataSource = new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(jdbcUrl);
            dataSource = new HikariDataSource(config);
        }

       

            try {
                Connection con = dataSource.getConnection();
                Statement stmt = con.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Veranstaltung (id serial, title varchar(140), beschreibung varchar(1000), beginn date)");
//                stmt.executeUpdate("INSERT INTO info VALUES (now())");
                ResultSet rs = stmt.executeQuery("SELECT * FROM Veranstaltung");

                String output = "";
                while (rs.next()) {
                    output += "Read from DB: " + rs.toString() + "\n";
                }
                return output;
            } catch (Exception e) {
                return "error on execution" + e.getMessage();
            }
        
    }

    @GetMapping
    public Database getDatabase() {
        Database database = new Database();
        database.setMessage(getRecords());
        return database;
    }

}
