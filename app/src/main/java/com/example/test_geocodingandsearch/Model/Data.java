package com.example.test_geocodingandsearch.Model;

import java.util.List;

public class Data {
    private List<Item> items;
    private Object[] queryTerms;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Object[] getQueryTerms() {
        return queryTerms;
    }

    public void setQueryTerms(Object[] value) {
        this.queryTerms = value;
    }
}
