package edu.hm.cs.cnj.cnjbackend.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/db")
public class DatabaseController {
        @Autowired
        DatabaseProperties properties;
        
        
        @GetMapping
        public Database getDatabase(){
            Database database = new Database();
            database.setMessage(properties.getRecords());
            return database;
        }
        
}
