package edu.hm.cs.cnj.cnjbackend.database;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Server-Info")
public class Database {
    @ApiModelProperty(example = "Alletsuper!", readOnly = true)
    String message;

    public Database() {
        // Default constructor.
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
