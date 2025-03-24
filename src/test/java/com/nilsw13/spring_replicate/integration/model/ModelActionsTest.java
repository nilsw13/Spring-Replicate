package com.nilsw13.spring_replicate.integration.model;

import com.nilsw13.spring_replicate.ResponseType.Model.Model;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelList;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelVersionList;
import com.nilsw13.spring_replicate.ResponseType.Model.Version;
import com.nilsw13.spring_replicate.integration.BaseReplicateTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ModelActionsTest extends BaseReplicateTest {

    @Test
    void ModelGetListTest() {
       ModelList response = replicate.models().list();

        System.out.println(response.getResults());

    }

    @Test
    void getModelById() {
        String owner = "anthropic";
        String modelName = "claude-3.7-sonnet";
        Model response = replicate.models().get(owner, modelName);

        System.out.println(response.getUrl());
    }

    @Test
    void getModelVersionsList() {
        String owner = "nilsw13";
        String modelName = "nari_flux_04";
        ModelVersionList response = replicate.models().listModelVersions(owner, modelName);

        System.out.println(response.getResults());
    }

    @Test
    void getModelVersionTest() {
        String owner = "nilsw13";
        String modelName = "nari_flux_04";
        String versionId = "4b4d50e4ab188e2c955255dd95bedbb4dc5a09681d597746709219e181689cfd";
        Version response = replicate.models().getModelVersion(owner, modelName, versionId);
        System.out.println(response.getId());
        System.out.println(response.getCogVersion());
        System.out.println(response.getCreatedAt());
    }


    @Test
    void getModelReadMeTest() {
        String owner = "anthropic";
        String modelName = "claude-3.7-sonnet";
        String response = replicate.models().getModelReadme(owner, modelName);

        System.out.println(response);
    }

    @Test
    void deleteModelVersionTest() {
        String owner = "nilsw13";
        String modelName = "nari_flux_04";
        String versionId = "ef560df1a9433538ec6b335b5d6314ea83afa7ea76333b8fb2340dab6c4cf805";
        Version response = replicate.models().deleteModelVersion(owner, modelName, versionId);


    }

    @Test void deleteModelTest() {
        String owner = "nilsw13";
        String modelName = "testdev-102119321145802782035";
        String versionId = "84d630efd1c746cab7ffd1c5aae551ad960df75c32bbdee0a36fc308f88c8438";


        Model response = replicate.models().delete(owner, modelName);
        System.out.println(response);
        assertThat(response.getId()).isNull();
    }

    @Test
    void createModelTest() {
        Model model = new Model();
        model.setOwner("nilsw13");
        model.setVisibility("private");
        model.setName("testlibrairie");
        model.setDescription("ce model est un test de creation de model via ma librairie java springboot");
        model.setHardware("cpu");

        Model response = replicate.models().create(model);
        System.out.println(response.getName());
        assertThat(response).isNotNull();

    }


}
