package com.nilsw13.spring_replicate.unitaire.collection;


import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModel;
import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModelList;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.impl.CollectionServiceImpl;
import com.nilsw13.spring_replicate.unitaire.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.when;


@Category(UnitTest.class)
public class CollectionServiceImplTest {

    @Mock
    private ReplicateRestClient mockRestClient;

    private CollectionServiceImpl collectionService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        collectionService = new CollectionServiceImpl(mockRestClient);
    }

    @Test
    public void testGet_ShouldCallCorrectEndpointAndReturnCollection(){
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.setName("collection");
        collectionModel.setSlug("collection-slug");
        collectionModel.setDescription("collection-description");

        when(mockRestClient.get("collections/" + collectionModel.getSlug(), CollectionModel.class)).thenReturn(collectionModel);

        CollectionModel result = collectionService.get("collection-slug");

        assertNotNull("Result shouldn't be null", result);
        assertEquals("collection", result.getName());
        assertEquals("collection-slug", result.getSlug());
        assertEquals("collection-description", result.getDescription());
        verify(mockRestClient, times(1)).get("collections/" + collectionModel.getSlug(), CollectionModel.class);
        verifyNoMoreInteractions(mockRestClient);

    }

    @Test
    public void testGet_ShouldCallCorrectEndpointAndReturnCollectionList() {
        CollectionModel col1 = new CollectionModel();
        col1.setName("collection1");
        col1.setSlug("collection-slug1");
        col1.setDescription("this is collection 1");

        CollectionModel col2 = new CollectionModel();
        col2.setName("collection2");
        col2.setSlug("collection-slug2");
        col2.setDescription("this is collection 2");

        List<CollectionModel> fakeList = new ArrayList<>();
        fakeList.add(col1);
        fakeList.add(col2);

        CollectionModelList list = new CollectionModelList();
        list.setNext("null");
        list.setPrevious("null");
        list.setResults(fakeList);

        when(mockRestClient.get("collections", CollectionModelList.class)).thenReturn(list);

        CollectionModelList result = collectionService.list();

        assertNotNull("result shouldn't be null", result);
        assertEquals("List should have 2 collections", 2, result.getResults().size());
        assertEquals("First element should be collection1", "collection1", result.getResults().get(0).getName());
        assertEquals("Second element should be collection2", "collection2", result.getResults().get(1).getName());

        verify(mockRestClient, times(1)).get("collections", CollectionModelList.class);
        verifyNoMoreInteractions(mockRestClient);
    }

}
