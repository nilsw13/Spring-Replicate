package com.nilsw13.spring_replicate.service.collection;

import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModel;
import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModelList;

public interface CollectionService {

    CollectionModelList list();
    CollectionModel get(String collection_slug);


}
