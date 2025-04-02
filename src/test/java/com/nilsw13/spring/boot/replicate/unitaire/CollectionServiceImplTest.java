package com.nilsw13.spring.boot.replicate.unitaire;

import com.nilsw13.spring.boot.replicate.ResponseType.Collection.CollectionModel;
import com.nilsw13.spring.boot.replicate.ResponseType.Collection.CollectionModelList;
import com.nilsw13.spring.boot.replicate.api.ReplicateRestClient;
import com.nilsw13.spring.boot.replicate.impl.CollectionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Tag("unit-test")
public class CollectionServiceImplTest {

    @Mock
    private ReplicateRestClient mockRestClient;

    private CollectionServiceImpl collectionService;

    @BeforeEach
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

        assertNotNull(result, "Result shouldn't be null");
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

        assertNotNull(result, "result shouldn't be null");
        assertEquals(2, result.getResults().size(), "List should have 2 collections");
        assertEquals("collection1", result.getResults().get(0).getName(), "First element should be collection1");
        assertEquals("collection2", result.getResults().get(1).getName(), "Second element should be collection2");

        verify(mockRestClient, times(1)).get("collections", CollectionModelList.class);
        verifyNoMoreInteractions(mockRestClient);
        assertEquals("null", result.getNext());
        assertEquals("null", result.getPrevious());
    }

    @Test
    public void testGetNext() {

    }
}