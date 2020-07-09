package com.codetest.cityconns;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Configuration
@ComponentScan
@EnableAutoConfiguration 
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AvailableConnections {

    private static final String DESTINATION = "destination";
	private static final String ORIGIN = "origin";
	private static final String NO = "No";
	private static final String YES = "Yes";

	public static void main(String[] args) throws Exception {
       SpringApplication.run(AvailableConnections.class, args);    	
    	
    }
    
    @RequestMapping(value="/connected", method = RequestMethod.GET)
    public String connectionsResponse(@RequestParam(required=false, name=ORIGIN) String origin, 
    		@RequestParam(required=false, name=DESTINATION) String destination) {
    	
        if(origin != null && destination != null 
        		&& !origin.isEmpty() && !destination.isEmpty() 
        		&& connectionExists(origin, destination)) {
        	return YES;
        } else {
        	return NO;
        }
        
    }

	private boolean connectionExists(String origin, String destination) {
		// read the file from the project and read line by line -- done
		// Store the values in a list as each line as a string
		String connectionFile = "./src/main/resources/connections.txt";
		List<String> routeLines = new ArrayList<String>();
		
		try {
			routeLines = Files.lines(new File(connectionFile).toPath())
					.map(s -> s.trim())
					.filter(s -> !s.isEmpty())
					.map(s -> s.toUpperCase())
					.collect(Collectors.toList());
			
			for (String citiesRoute : routeLines) {
				if(citiesRoute.contains(origin.toUpperCase())) {
					if(citiesRoute.contains(destination.toUpperCase())) {
						return Boolean.TRUE;
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Boolean.FALSE;
	}   
}
