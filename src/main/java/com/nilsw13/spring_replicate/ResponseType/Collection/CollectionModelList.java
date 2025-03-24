package com.nilsw13.spring_replicate.ResponseType.Collection;


import java.util.List;

public class CollectionModelList {

    private String next;
    private String previous;
    List<CollectionModel> results;

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<CollectionModel> getResults() {
        return results;
    }
}
