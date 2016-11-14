package main.java.com.samplemvc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amitkumar on 13/11/16.
 */

@Controller
public class HomeController {

    @RequestMapping(value="/sample", method={RequestMethod.GET})
    public String homepage(Model model) {

        model.addAttribute("message", "Sample MVC!!!");

        return "welcome";
    }

    @RequestMapping(value="/rest", method={RequestMethod.GET})
    public @ResponseBody
    Map<String, String> sampleRestEndPoint() {

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return response;
    }
}
