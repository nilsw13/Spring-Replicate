package com.nilsw13.spring_replicate.unitaire;

import com.nilsw13.spring_replicate.ResponseType.Model.Model;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelList;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelVersionList;
import com.nilsw13.spring_replicate.ResponseType.Model.Version;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.impl.ModelServiceImpl;
import com.nilsw13.spring_replicate.service.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("unit-test")
public class ModelServiceImplTest {

    @Mock
    private ReplicateRestClient mockRestClient;

    private ModelService modelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        modelService = new ModelServiceImpl(mockRestClient);
    }

    @Test
    public void testGet_ShouldCallRightEndpointAndReturnModel() {
        Model model = new Model();
        model.setName("modelTest");
        model.setOwner("ownerTest");
        model.setHardware("cpu");
        model.setVisibility("private");
        model.setDescription("test model");

        Mockito.when(mockRestClient.get("models/" + model.getOwner() + "/" + model.getName(), Model.class)).thenReturn(model);

        Model result = modelService.get(model.getOwner(), model.getName());

        assertNotNull(result, "Model result should not be null");
        assertEquals("modelTest", result.getName(), "Model name should be modelTest");
        assertEquals("ownerTest", result.getOwner(), "Model owner should be ownerTest");
        assertEquals("cpu", result.getHardware(), "Model hardware should be cpu");
        assertEquals("private", result.getVisibility(), "Model visibility should be private");
        assertEquals("test model", result.getDescription(), "Model description should be test model");

        verify(mockRestClient, times(1)).get("models/" + model.getOwner() + "/" + model.getName(), Model.class);
        verifyNoMoreInteractions(mockRestClient);
    }




    @Test
    public void testGet_ShouldCallRightEndpointAndReturnModelList() {
        List<Model> modelList = new ArrayList<>();
        Model model = new Model();
        model.setName("modelTest");
        model.setOwner("ownerTest");
        model.setHardware("cpu");
        model.setVisibility("private");
        model.setDescription("test model");

        Model model2 = new Model();
        model2.setName("modelTest2");
        model2.setOwner("ownerTest2");
        model2.setHardware("cpu2");
        model2.setVisibility("private2");
        model2.setDescription("test model2");

        modelList.add(model);
        modelList.add(model2);

        ModelList fakeList = new ModelList();
        fakeList.setResults(modelList);

        Mockito.when(mockRestClient.get("models", ModelList.class)).thenReturn(fakeList);

        ModelList result = modelService.list();

        assertNotNull(result, "Models list result should not be null");
        assertEquals(2, result.getResults().size());
        assertEquals("modelTest2", result.getResults().get(1).getName(), "Model 2 name should be modelTest2");
        assertEquals("modelTest", result.getResults().get(0).getName(), "Model name should be modelTest");

        verify(mockRestClient, times(1)).get("models", ModelList.class);
        verifyNoMoreInteractions(mockRestClient);
    }

    @Test
    public void testGet_ShouldCallRightEndpointAndReturnModelVersionList() {
        Model model = new Model();
        model.setName("modelTest");
        model.setOwner("ownerTest");
        model.setHardware("cpu");
        model.setVisibility("private");
        model.setDescription("test model");

        Version version = new Version();
        version.setId("123456789");
        version.setCogVersion("cog-test");

        Version version1 = new Version();
        version1.setId("987654321");
        version1.setCogVersion("cog-test1");

        List<Version> fakeVersionList = new ArrayList<>();
        fakeVersionList.add(version);
        fakeVersionList.add(version1);

        ModelVersionList fakeVersions = new ModelVersionList();
        fakeVersions.setResults(fakeVersionList);

        Mockito.when(mockRestClient.get("models/" + model.getOwner() + "/" + model.getName() + "/" + "versions", ModelVersionList.class)).thenReturn(fakeVersions);

        ModelVersionList result = modelService.listModelVersions(model.getOwner(), model.getName());

        assertNotNull(result.getResults(), "version list should not be null");
        assertEquals("123456789", result.getResults().get(0).getId(), "Version 0 should be 123456789");
        assertEquals("987654321", result.getResults().get(1).getId(), "Version 1 should be 987654321");

        verify(mockRestClient, times(1)).get("models/" + model.getOwner() + "/" + model.getName() + "/" + "versions", ModelVersionList.class);
        verifyNoMoreInteractions(mockRestClient);
    }

    @Test
    public void testGet_ShouldCallRightEndpointAndReturnModelVersion() {
        Version version = new Version();
        version.setCreatedAt("12/02/12");
        version.setCogVersion("dev");
        version.setId("123456789");

        Model model = new Model();
        model.setName("modeltest");
        model.setOwner("modelOwner");

        Mockito.when(mockRestClient.get("models/" + model.getOwner() + "/" + model.getName() + "/" + "versions/" + version.getId(), Version.class)).thenReturn(version);

        Version result = modelService.getModelVersion(model.getOwner(), model.getName(), version.getId());

        assertNotNull(result);
        verify(mockRestClient, times(1)).get("models/" + model.getOwner() + "/" + model.getName() + "/" + "versions/" + version.getId(), Version.class);
        verifyNoMoreInteractions(mockRestClient);
    }

    @Test
    public void testDeleteVersion_ShouldCallRightEndpointAndReturnNull() {
        Version version = new Version();
        version.setCreatedAt("12/02/12");
        version.setCogVersion("dev");
        version.setId("123456789");

        Model model = new Model();
        model.setName("modeltest");
        model.setOwner("modelOwner");

        String expectedUrl = "models/" + model.getOwner() + "/" + model.getName() + "/versions/" + version.getId();

        Mockito.when(mockRestClient.delete(expectedUrl, Version.class)).thenReturn(version);

        Version result = modelService.deleteModelVersion(model.getOwner(), model.getName(), version.getId());

        assertNotNull(result);
        assertEquals(version, result);
        verify(mockRestClient, times(1)).delete(expectedUrl, Version.class);
        verifyNoMoreInteractions(mockRestClient);
    }


}