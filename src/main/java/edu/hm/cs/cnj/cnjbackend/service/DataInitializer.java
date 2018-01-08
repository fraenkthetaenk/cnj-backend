package edu.hm.cs.cnj.cnjbackend.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DataInitializer {
	
	@Value("${spring.datasource.url}")
    	private String jdbcUrl;


	@Autowired
	private VeranstaltungService veranstaltungen;

	@PostConstruct
	public void erstelleDaten() {
		
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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Veranstaltung (id serial, titel varchar(140), beschreibung varchar(1000), beginn date)");
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Teilnahme (id serial, name varchar(255), begleiter integer, status varchar(20), veranstaltung_id integer)");
    
            } catch (Exception e) {
                
            }
	
	}
}



        
