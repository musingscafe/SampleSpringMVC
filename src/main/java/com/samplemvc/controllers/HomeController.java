package com.samplemvc.controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.samplemvc.model.FormData;
import com.samplemvc.mongo.MongoQueryBuilder;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amitkumar on 13/11/16.
 */

@Controller
public class HomeController {

    private static final String MONGO_SERVER_HOST = "localhost";
	private static final String MONGO_DB_NAME = "firstdb";
	private static final String MONGO_COLLECTION = "testCollection";

	@Autowired
	private MongoQueryBuilder mongoQueryBuilder;
	
	private MongoClient mongoClient = new MongoClient( MONGO_SERVER_HOST , 27017 );
	
	@RequestMapping(value="/sample", method={RequestMethod.GET})
    public String homepage(Model model) {

        model.addAttribute("message", "Sample MVC!!!");

        return "welcome";
    }

    @RequestMapping(value="/rest", method={RequestMethod.GET})
    public @ResponseBody Map<String, String> sampleRestEndPoint() throws InterruptedException {

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        Thread.sleep(100);
        System.out.println("*****************Got Request Here ********************");

        return response;
    }

    @RequestMapping(value="/put", method={RequestMethod.POST})
    public @ResponseBody Map<String, String> putInMongo(@RequestBody FormData formData) throws InterruptedException {
        final MongoCollection<Document> coll = getMongoCollection();
        final Document document = mongoQueryBuilder.buildPutQuery(formData);
        coll.insertOne(document);
        FindIterable<Document> documents = coll.find();
        final Map<String, String> response = new HashMap<>();
        response.put("insertedObjects", documents.toString());
        return response;
    }
    
    @RequestMapping(value="/search", method={RequestMethod.POST})
    public @ResponseBody Map<String, List<String>> searchInMongo(@RequestBody FormData formData) throws InterruptedException {
    	
    	final MongoCollection<Document> coll = getMongoCollection();
    	final Document filter = mongoQueryBuilder.buildSearchQuery(formData);
    	final Map<String, List<String>> response = new HashMap<>();
    	
		return response;
    	
    }
    
    @RequestMapping(value="/delete", method={RequestMethod.POST})
    public @ResponseBody String delete(@RequestBody FormData formData) throws InterruptedException {
    	String response = "FAILURE";
    	try {
    		final MongoCollection<Document> coll = getMongoCollection();
        	final Document document = mongoQueryBuilder.buildDeleteQuery(formData);
        	Document deletedElement = coll.findOneAndDelete(document);
        	if(deletedElement != null){
        		response = "SUCCESS";
        	}else{
        		response = "RECORD NOT FOUND";
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		return response;
    	
    }
    
    private MongoCollection<Document> getMongoCollection(){
         return mongoClient.getDatabase(MONGO_DB_NAME).getCollection(MONGO_COLLECTION);
    }
}
