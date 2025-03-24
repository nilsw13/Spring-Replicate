package com.nilsw13.spring_replicate.integration.collection;

import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModel;
import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModelList;
import com.nilsw13.spring_replicate.integration.BaseReplicateTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CollectionActionsTest extends BaseReplicateTest {

    @Test
    void collectionListTest(){
        CollectionModelList list = replicate.collections().list();
        System.out.println(list.getResults());
        assertThat(list).isNotNull();
        assertThat(list.getResults()).isNotNull();
    }

    @Test
    void collectionGetTest() {
        CollectionModel collectionModel = replicate.collections().get("wan-video");
        System.out.println(collectionModel.getSlug());
        assertThat(collectionModel.getSlug()).isNotNull();
        assertThat(collectionModel.getSlug()).isEqualTo("wan-video");
    }

}
