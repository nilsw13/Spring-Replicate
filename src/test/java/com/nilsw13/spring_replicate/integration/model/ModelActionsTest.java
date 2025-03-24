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
        String modelName = "linkedin_flux_01";
        String versionId = "4fee9b1305f4bbfeeabc2ad9db14cf904d40070c71a962eb2def07e098973bef";
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
        String modelName = "nilslinked-108216909907328377651";
        String versionId = "59e096d271654c7f471c47d93344e5947ae2bd3477e8f9d29f88c8ede0a9c173";
        Version response = replicate.models().deleteModelVersion(owner, modelName, versionId);

        System.out.println(response);



    }

    @Test void deleteModelTest() {
        String owner = "nilsw13";
        String modelName = "nilslinked-108216909907328377651";


        Model response = replicate.models().delete(owner, modelName);
        System.out.println(response);

        assertThat(response).isNull();

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
