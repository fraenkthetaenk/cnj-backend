package edu.hm.cs.cnj.cnjbackend.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="cnj.info")
public class InfoProperties {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.url2:default}")
    private String dbUrl2;
    
	String message;
	
	public void setMessage(String message){
		this.message = message +dbUrl + dbUrl2;
	}
	
	public String getMessage()
	{
		return message;
	}
}
