package com.nilsw13.spring_replicate.service;

import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModel;
import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModelList;

public interface CollectionService {

    CollectionModelList list();
    CollectionModel get(String collection_slug);


}
