package com.samplemvc.controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.core.env.SystemEnvironmentPropertySource;
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
    Map<String, String> sampleRestEndPoint() throws InterruptedException {

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        Thread.sleep(100);
        System.out.println("*****************Got Request Here ********************");

        return response;
    }

    @RequestMapping(value="/putInMongo", method={RequestMethod.GET})
    public @ResponseBody
    Map<String, String> putInMongo() throws InterruptedException {

        Map<String, String> response = new HashMap<>();
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase db = mongoClient.getDatabase("firstdb");
        MongoCollection<Document> coll = db.getCollection("testCollection");
        Map<String, Object> docObject = new HashMap<>();
        docObject.put("key1", "value1");
        Document document = new Document(docObject);
        coll.insertOne(document);

        FindIterable<Document> documents = coll.find();
        response.put("insertedObjects", documents.toString());
        return response;
    }
}
