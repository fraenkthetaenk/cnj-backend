
package edu.hm.cs.cnj.cnjbackend.info;

import org.springframework.beans.factory.annotation.Value;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Server-Info")
public class Info {
	
	@ApiModelProperty(example="Alletsuper!", readOnly=true)
	String message;
	
	@Value("${spring.datasource.url}")
	  private String dbUrl;
	@Value("${spring.datasource.url2}")
    private String dbUrl2;

	public Info() {
		// Default constructor.
	}

	public String getMessage() {
		return message +dbUrl+dbUrl2;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
