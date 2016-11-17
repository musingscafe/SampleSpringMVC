package com.samplemvc.controllers;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.musingscafe.grabber.channels.Registrar;
import com.musingscafe.grabber.connectors.RedisConnector;
import com.musingscafe.grabber.core.Employee;
import com.musingscafe.grabber.core.channel.Channel;
import com.musingscafe.grabber.core.channel.ChannelBuilder;
import com.musingscafe.grabber.core.connectors.GrabberConnector;
import com.musingscafe.grabber.core.connectors.ServerConfig;
import com.musingscafe.grabber.core.consumers.Consumer;
import com.musingscafe.grabber.core.consumers.PassThroughConsumer;
import com.musingscafe.grabber.core.message.GrabberMessage;
import com.musingscafe.grabber.core.message.Tuple;
import com.samplemvc.model.FormData;
import com.samplemvc.model.UserData;
import com.samplemvc.mongo.MongoQueryBuilder;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

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
	
	@RequestMapping(value="/sender", method={RequestMethod.GET})
    public String homepage(Model model) {
        return "sender";
    }

    @RequestMapping(value="/data", method={RequestMethod.GET})
    public String showDataPage(Model model) {
        return "data";
    }

    @RequestMapping(value="/rest", method={RequestMethod.GET})
    public @ResponseBody Map<String, String> sampleRestEndPoint() throws InterruptedException {

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        Thread.sleep(100);
        System.out.println("***************** Got Request Here ********************");

        return response;
    }

    @RequestMapping(value="/sendData", method={RequestMethod.POST})
    public @ResponseBody Map<String, String> sendMessage(@RequestBody UserData userData) throws InterruptedException {
        final Map<String, String> response = new HashMap<>();
        response.put("userData", userData.getFname());

        ChannelBuilder builder = new ChannelBuilder();

        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setHost("http://localhost");
        serverConfig.setPort("8084/gmessage");

        Channel defaultChannel = builder.setChannelIdentifier("default").setConnector(new GrabberConnector(serverConfig))
                .setConsumers(new ArrayList<Consumer>(){{ add(new PassThroughConsumer());}}).build();

        Channel channel = builder
                .setChannelIdentifier("user")
                .setConnector(new RedisConnector())
                .setConsumers(new ArrayList<Consumer>(){{ add(new PassThroughConsumer());}})
                .build();

        Registrar.getInstance().registerChannel("user", channel);
        Registrar.getInstance().registerChannel("defaultChannel", defaultChannel);

        send(channel, userData);

        System.out.println("***************** Sending Data to Grabber ********************");
        return response;
    }


    private static void send(Channel grabberChannel, UserData userData) {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName(UUID.randomUUID().toString());

        LinkedHashMap<String, Object> fields = new LinkedHashMap<>();
        fields.put("userData", userData);
        Tuple tuple = new Tuple(fields);

        GrabberMessage message = new GrabberMessage();
        message.setContent(tuple);
        grabberChannel.write(message);
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

    @RequestMapping(value="/fetchData", method={RequestMethod.GET})
    public @ResponseBody Map<String, List<String>> fetchData() throws InterruptedException {
        List<String> dataList = new ArrayList<>();
        final Map<String, List<String>> response = new HashMap<>();

        Jedis jedis = new Jedis("172.26.69.42", 6379);

        Set<String> keySet = jedis.keys("*");
        for (String s : keySet) {
            dataList.add(s);
            System.out.println("Key" + s);
        }

        response.put("RESPONSE", dataList);
        return response;


//
//        final MongoCollection<Document> coll = getMongoCollection();
//        FindIterable<Document> documents = coll.find();
//        final Map<String, List<String>> response = new HashMap<>();
//        List<String> dataList = new ArrayList<>();
//        for (Document document : documents) {
//            dataList.add(document.toJson());
//        }
//        response.put("RESPONSE", dataList);
//        return response;
    }
    
    @RequestMapping(value="/search", method={RequestMethod.POST})
    public @ResponseBody Map<String, List<String>> searchInMongo(@RequestBody FormData formData) throws InterruptedException {
    	final MongoCollection<Document> coll = getMongoCollection();
    	final Document filter = mongoQueryBuilder.buildSearchQuery(formData);
    	FindIterable<Document> results = coll.find(filter);
    	final Map<String, List<String>> response = new HashMap<>();
    	List<String> dataList = new ArrayList<>(0);
    	for (Document document : results) {
    		dataList.add(document.toJson());
		}
    	response.put("RESPONSE", dataList);
		return response;
    	
    }
    
    @RequestMapping(value="/delete", method={RequestMethod.POST})
    public @ResponseBody String delete(@RequestBody FormData formData) throws InterruptedException {
    	String response = "FAILURE";
    	try {
    		final MongoCollection<Document> coll = getMongoCollection();
        	final Document document = mongoQueryBuilder.buildDeleteQuery(formData);
            DeleteResult deleteResult = coll.deleteMany(document);
        	if(deleteResult != null){
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
