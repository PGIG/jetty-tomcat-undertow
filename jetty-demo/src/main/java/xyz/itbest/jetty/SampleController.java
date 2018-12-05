package xyz.itbest.jetty;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.itbest.jetty.model.School;

import java.io.IOException;

/**
 * @author pgig
 */
@RestController
public class SampleController {

	@Autowired
	private ResourceLoader resourceLoader;
	
	@GetMapping("/")
    public String home() {
        return "Hello World!";
    }
    
    @GetMapping("/getschool/{schoolId}")
    public School getSchool(@PathVariable("schoolId") String schoolId) {
    	School mySchool = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Resource myFile = resourceLoader.getResource("classpath:schoolsample.json");
			mySchool = objectMapper.readValue(myFile.getInputStream(), School.class);
			mySchool.setSchoolId(Integer.parseInt(schoolId));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mySchool;
    }
}
