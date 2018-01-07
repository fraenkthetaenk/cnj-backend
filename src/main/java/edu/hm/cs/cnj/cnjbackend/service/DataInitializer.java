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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Teilnahmen (id serial, name varchar(255), begleiter integer, status varchar(20), veranstaltung_id integer)");
		    
		VeranstaltungDto v1 = new VeranstaltungDto();
		v1.setTitel("100 Jahre Java");
		v1.setBeschreibung("Festmahl zu Ehren aller Java-Entwickler");
		v1.setBeginn(new GregorianCalendar(2017, Calendar.JULY, 12, 20, 00, 00).getTime());		
		veranstaltungen.erzeugeVeranstaltung(v1);

		VeranstaltungDto v2 = new VeranstaltungDto();
		v2.setTitel("Workshop aufrechtes Sitzen");
		v2.setBeschreibung("Zertifizierungsworkshop für das Sitzen auf St\u00FChlen mit mindestens drei Beinen.");
		v2.setBeginn(new GregorianCalendar(2018, Calendar.AUGUST, 2, 9, 30, 00).getTime());		
		veranstaltungen.erzeugeVeranstaltung(v2);	
    
            } catch (Exception e) {
                
            }
	
	}
}



        
