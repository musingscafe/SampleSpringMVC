package com.samplemvc.model;

/**
 * Created by amitkumar on 16/11/16.
 */
public class MongoRequest {

    private String key;
    private String operator;
    private String searchString;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
