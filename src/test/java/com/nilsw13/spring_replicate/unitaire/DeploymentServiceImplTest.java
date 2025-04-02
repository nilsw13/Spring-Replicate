package com.nilsw13.spring_replicate.unitaire;

import com.nilsw13.spring_replicate.ResponseType.Deployment.Deployment;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentConfiguration;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentList;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentRelease;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.impl.DeploymentServiceImpl;
import com.nilsw13.spring_replicate.impl.PredictionBuilderServiceImpl;
import com.nilsw13.spring_replicate.service.DeploymentService;
import com.nilsw13.spring_replicate.service.PredictionBuilderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("unit-test")
public class DeploymentServiceImplTest {

    @Mock
    private ReplicateRestClient mockitoRestClient;

    private DeploymentService deploymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        deploymentService = new DeploymentServiceImpl(mockitoRestClient);
    }

    @Test
    public void testCreate_ShouldCallCorrectEndpointAndReturnCreatedDeployment(){
        DeploymentConfiguration configuration = new DeploymentConfiguration();
        configuration.setVersion("123456");
        configuration.setMinInstances(2);
        configuration.setMaxInstances(6);
        configuration.setHardware("cpu");

        DeploymentRelease release = new DeploymentRelease();
        release.setModel("nilsw13/flux_hugh");
        release.setNumber("1");
        release.setCreatedAt("12/02/1999");
        release.setConfiguration(configuration);

        Deployment deployment = new Deployment();
        deployment.setDeploymentRelease(release);
        deployment.setOwner("nilsw13");
        deployment.setName("test deployment create");

        Mockito.when(mockitoRestClient.post("deployments", configuration, Deployment.class)).thenReturn(deployment);

        Deployment result = deploymentService.create(configuration);

        System.out.println(result);
        assertNotNull(result, "Deployment Result should not be null");
        assertEquals("nilsw13", result.getOwner());
        assertEquals("123456", result.getCurrentRelease().getConfiguration().getVersion());
        assertEquals(6, result.getCurrentRelease().getConfiguration().getMaxInstances());

        verify(mockitoRestClient, times(1)).post("deployments" , configuration, Deployment.class);
        verifyNoMoreInteractions(mockitoRestClient);
    }

    @Test
    public void testUpdate_ShouldCallCorrectEndpointAndReturnUpdatedDeployment() {
        DeploymentConfiguration configuration = new DeploymentConfiguration();
        configuration.setVersion("123456");
        configuration.setMinInstances(2);
        configuration.setMaxInstances(6);
        configuration.setHardware("cpu");

        DeploymentRelease release = new DeploymentRelease();
        release.setModel("nilsw13/flux_hugh");
        release.setNumber("1");
        release.setCreatedAt("12/02/1999");
        release.setConfiguration(configuration);

        Deployment deployment = new Deployment();
        deployment.setDeploymentRelease(release);
        deployment.setOwner("nilsw13");
        deployment.setName("test deployment create");

        Mockito.when(mockitoRestClient.post("deployments", configuration, Deployment.class)).thenReturn(deployment);

        Deployment result = deploymentService.create(configuration);

        System.out.println(result);
        assertNotNull(result, "Deployment Result should not be null");
        assertEquals("nilsw13", result.getOwner());
        assertEquals("123456", result.getCurrentRelease().getConfiguration().getVersion());
        assertEquals(6, result.getCurrentRelease().getConfiguration().getMaxInstances());

        verify(mockitoRestClient, times(1)).post("deployments" , configuration, Deployment.class);
        verifyNoMoreInteractions(mockitoRestClient);

        configuration.setMinInstances(1);
        configuration.setMaxInstances(3);

        Mockito.when(mockitoRestClient.patch("deployments/" + deployment.getOwner() + '/' + deployment.getName(), configuration, DeploymentConfiguration.class)).thenReturn(configuration);

        DeploymentConfiguration resultUpdated = deploymentService.update(deployment.getOwner(), deployment.getName(), configuration);

        assertNotNull(resultUpdated, "Updated result should not be null");
        assertEquals(3, resultUpdated.getMaxInstances());
        assertEquals(1, resultUpdated.getMinInstances());
        assertEquals(3, deployment.getCurrentRelease().getConfiguration().getMaxInstances());

        verify(mockitoRestClient, times(1)).patch("deployments/" + deployment.getOwner() + "/" + deployment.getName(), configuration,  DeploymentConfiguration.class);
        verifyNoMoreInteractions(mockitoRestClient);
    }

    @Test
    public void testDelete_shouldCallCorrectEndpointAndReturnNull(){
        DeploymentConfiguration configuration = new DeploymentConfiguration();
        configuration.setVersion("123456");
        configuration.setMinInstances(2);
        configuration.setMaxInstances(6);
        configuration.setHardware("cpu");

        DeploymentRelease release = new DeploymentRelease();
        release.setModel("nilsw13/flux_hugh");
        release.setNumber("1");
        release.setCreatedAt("12/02/1999");
        release.setConfiguration(configuration);

        Deployment deployment = new Deployment();
        deployment.setDeploymentRelease(release);
        deployment.setOwner("nilsw13");
        deployment.setName("test deployment create");

        Mockito.when(mockitoRestClient.post("deployments", configuration, Deployment.class)).thenReturn(deployment);

        Deployment result = deploymentService.create(configuration);

        System.out.println(result);
        assertNotNull(result, "Deployment Result should not be null");
        assertEquals("nilsw13", result.getOwner());
        assertEquals("123456", result.getCurrentRelease().getConfiguration().getVersion());
        assertEquals(6, result.getCurrentRelease().getConfiguration().getMaxInstances());

        verify(mockitoRestClient, times(1)).post("deployments" , configuration, Deployment.class);
        verifyNoMoreInteractions(mockitoRestClient);

        Deployment deletedResult = deploymentService.delete(deployment.getOwner(), deployment.getName());
        assertNull(deletedResult);
        System.out.println(deletedResult);
        verify(mockitoRestClient, times(1)).delete("deployments/"  + deployment.getOwner() + "/" + deployment.getName(), Deployment.class);
        verifyNoMoreInteractions(mockitoRestClient);
    }

    @Test
    public void testCreateDeploymentPrediction() throws InterruptedException {
        // Configurer
        String owner = "nilsw13";
        String name = "test deployment create";

        PredictionBuilderService result = deploymentService.createDeploymentPrediction(owner, name);

        assertNotNull(result, "Builder should not be null");
        assertTrue(result instanceof PredictionBuilderServiceImpl,
                "Builder should be an instance of PredictionBuilderServiceImpl");
    }

    @Test
    public void testGet_ShouldCallCorrectEndpointAndReturnDeployment(){
        DeploymentConfiguration configuration = new DeploymentConfiguration();
        configuration.setMinInstances(2);
        configuration.setMaxInstances(6);
        configuration.setHardware("cpu");


        Map<String, String> createdByInfos = new HashMap<>();
        createdByInfos.put("type", "organization");
        createdByInfos.put("username", "acme");
        createdByInfos.put("name", "acme corp, inc");
        createdByInfos.put("avatar_url", "https://cdn.replicate.com/avatars/acme.png");
        createdByInfos.put("github_url", "https://github.com/acme");
        DeploymentRelease release  = new DeploymentRelease();
        release.setModel("Model test");
        release.setNumber("1");
        release.setVersion("123456789");
        release.setCreatedAt("12/02/13");
        release.setCreatedBy(createdByInfos);
        release.setConfiguration(configuration);

        Deployment deployment = new Deployment();
        deployment.setName("deployment 1");
        deployment.setOwner("Nils");
        deployment.setDeploymentRelease(release);

        Mockito.when(mockitoRestClient.get("deployments/" + deployment.getOwner() + "/" + deployment.getName(), Deployment.class)).thenReturn(deployment);

        Deployment result = deploymentService.get(deployment.getOwner(), deployment.getName());

        assertNotNull(result, "Deployment result should not be null");
        assertEquals("Nils", result.getOwner());
        assertEquals("Model test", result.getCurrentRelease().getModel());
        assertEquals("1", result.getCurrentRelease().getNumber());
        assertEquals("123456789", result.getCurrentRelease().getVersion());
        assertEquals("12/02/13", result.getCurrentRelease().getCreatedAt());
        assertEquals("deployment 1", result.getName());
        assertEquals("cpu", result.getCurrentRelease().getConfiguration().getHardware());
        assertEquals(createdByInfos, result.getCurrentRelease().getCreatedBy());
        System.out.println(result.getCurrentRelease().getConfiguration().getMaxInstances());
        assertEquals(6, result.getCurrentRelease().getConfiguration().getMaxInstances());
        assertEquals(2, result.getCurrentRelease().getConfiguration().getMinInstances());

        System.out.println(result.getCurrentRelease().getConfiguration().getHardware());

        verify(mockitoRestClient, times(1)).get("deployments/" + deployment.getOwner() + "/" + deployment.getName(), Deployment.class);
        verifyNoMoreInteractions(mockitoRestClient);
    }

    @Test
    public void testList_ShouldCallCorrectEndpointAndReturnDeployment() {
        List<Deployment> list = new ArrayList<>();

        DeploymentConfiguration configuration1 = new DeploymentConfiguration();
        configuration1.setMinInstances(2);
        configuration1.setMaxInstances(6);
        configuration1.setHardware("cpu");

        DeploymentRelease release1  = new DeploymentRelease();
        release1.setModel("Model test");
        release1.setNumber("1");
        release1.setVersion("123456789");
        release1.setCreatedAt("12/02/13");
        release1.setConfiguration(configuration1);

        Deployment deployment1= new Deployment();
        deployment1.setName("deployment 1");
        deployment1.setOwner("Nils");
        deployment1.setDeploymentRelease(release1);

        DeploymentConfiguration configuration = new DeploymentConfiguration();
        configuration.setMinInstances(2);
        configuration.setMaxInstances(6);
        configuration.setHardware("cpu");

        DeploymentRelease release  = new DeploymentRelease();
        release.setModel("Model test");
        release.setNumber("2");
        release.setVersion("123456789");
        release.setCreatedAt("12/02/13");
        release.setConfiguration(configuration);

        Deployment deployment = new Deployment();
        deployment.setName("deployment 2");
        deployment.setOwner("Nils");
        deployment.setDeploymentRelease(release);

        list.add(deployment1);
        list.add(deployment);

        DeploymentList deploymentList = new DeploymentList();
        deploymentList.setNext("null");
        deploymentList.setPrevious("null");
        deploymentList.setResults(list);

        Mockito.when(mockitoRestClient.get("deployments", DeploymentList.class)).thenReturn(deploymentList);

        DeploymentList result = deploymentService.list();

        assertNotNull(result, "Deployment list should not be null");
        assertEquals(2, result.getResults().size(), "List should have 2 element");
        assertEquals("deployment 1", result.getResults().get(0).getName(), "First element should be deployment 1");
        assertEquals("deployment 2", result.getResults().get(1).getName(), "Second element should be deployment 2");

        verify(mockitoRestClient, times(1)).get("deployments", DeploymentList.class);
        verifyNoMoreInteractions(mockitoRestClient);
    }
}