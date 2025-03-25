package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModel;
import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModelList;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.CollectionService;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final ReplicateRestClient replicateRestClient;

    public CollectionServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }

    @Override
    public CollectionModelList list() {
        return replicateRestClient.get("collections", CollectionModelList.class);
    }

    @Override
    public CollectionModel get(String collection_slug) {
        return replicateRestClient.get("collections/" + collection_slug, CollectionModel.class);
    }
}
