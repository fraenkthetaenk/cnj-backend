package edu.hm.cs.cnj.cnjbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
@EnableSwagger2
public class CnjBackendApplication {
	
	@Value("${spring.datasource.url}")
    private String jdbcUrl;

	public static void main(String[] args) {

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

            } catch (Exception e) {
                
            }

		SpringApplication.run(CnjBackendApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

}
