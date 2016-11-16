package com.samplemvc.mongo;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.samplemvc.model.FormData;

@Component
public class MongoQueryBuilder {
	private String EQUALS_OPERATORS = "equals";
	private String CONTAILS_OPERATORS = "contains";
	private String LESS_OPERATORS = "less";
	private String GREATER_OPERATORS = "greater";
	
	
	

	public Document buildSearchQuery(FormData formData) {
		
		if(EQUALS_OPERATORS.equalsIgnoreCase(formData.getOperator())){
			
		}else if(CONTAILS_OPERATORS.equalsIgnoreCase(formData.getOperator())){
			
		}else if(LESS_OPERATORS.equalsIgnoreCase(formData.getOperator())){
			
		}else if(GREATER_OPERATORS.equalsIgnoreCase(formData.getOperator())){
			
		}
		final Map<String, Object> docObject = new HashMap<>();
        docObject.put(formData.getKey(), formData.getValue());
        
        
		final Document document = new Document();

		return document;
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
