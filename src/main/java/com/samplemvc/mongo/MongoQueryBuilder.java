package com.samplemvc.mongo;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.samplemvc.model.FormData;

@Component
public class MongoQueryBuilder {
	private String EQUALS_OPERATORS = "equals";
	private String CONTAILS_OPERATORS = "contains";
	private String LESS_OPERATORS = "less";
	private String GREATER_OPERATORS = "greater";
	
	public Document buildSearchQuery(FormData formData) {
		final Map<String, Object> docObject = new HashMap<>();
		if(EQUALS_OPERATORS.equalsIgnoreCase(formData.getOperator())){
			docObject.put(formData.getKey(), new BasicDBObject("$is", formData.getSearchString()));
		}else if(CONTAILS_OPERATORS.equalsIgnoreCase(formData.getOperator())){
			docObject.put(formData.getKey(), new BasicDBObject("$regex", ".*" + formData.getSearchString() + ".*" ));
		}else if(LESS_OPERATORS.equalsIgnoreCase(formData.getOperator())){
			docObject.put(formData.getKey(), new BasicDBObject("$lt", formData.getSearchString()));
		}else if(GREATER_OPERATORS.equalsIgnoreCase(formData.getOperator())){
			docObject.put(formData.getKey(), new BasicDBObject("$gt", formData.getSearchString()));
		}
		return new Document(docObject);
	}

	public Document buildDeleteQuery(FormData formData) {
		final Map<String, Object> docObject = new HashMap<>();
        docObject.put(formData.getKey(), formData.getValue());
		return new Document(docObject);
	}

	public Document buildPutQuery(FormData formData) {
		final Map<String, Object> docObject = new HashMap<>();
        docObject.put(formData.getKey(), formData.getValue());
		return new Document(docObject);
	}

}
