package com.nilsw13.spring_replicate.integration;

import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModel;
import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModelList;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("integration-test")
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
