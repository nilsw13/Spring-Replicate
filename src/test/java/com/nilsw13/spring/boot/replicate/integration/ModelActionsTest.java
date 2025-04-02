package com.nilsw13.spring.boot.replicate.integration;

import com.nilsw13.spring.boot.replicate.ResponseType.Model.Model;
import com.nilsw13.spring.boot.replicate.ResponseType.Model.ModelList;
import com.nilsw13.spring.boot.replicate.ResponseType.Model.ModelVersionList;
import com.nilsw13.spring.boot.replicate.ResponseType.Model.Version;
import com.nilsw13.spring.boot.replicate.ResponseType.Prediction.Prediction;
import com.nilsw13.spring.boot.replicate.exception.ReplicateApiException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Fail.fail;

@Tag("integration-test")
public class ModelActionsTest extends BaseReplicateTest {

    private static final String modelTest = "linkedin_flux_01";
    private static final String modelToCreate = "modeltest-" + System.currentTimeMillis();

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
        assertThat(response).isNotNull();
        assertThat(response.getUrl()).isNotNull();
        assertThat(response.getName()).isEqualTo("claude-3.7-sonnet");

     }

    @Test
    void getModelVersionsList() {
        String owner = "nilsw13";
        String modelName = "linkedin_flux_01";
        ModelVersionList response = replicate.models().listModelVersions(owner, modelName);
        assertThat(response).isNotNull();
        assertThat(response.getResults()).isNotNull();
        assertThat(response.getResults().get(0).getId()).isNotNull();
    }

    @Test
    void getModelVersionTest() {
        String owner = "nilsw13";
        String modelName = "linkedin_flux_01";
        String versionId = "4fee9b1305f4bbfeeabc2ad9db14cf904d40070c71a962eb2def07e098973bef";
        Version response = replicate.models().getModelVersion(owner, modelName, versionId);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("4fee9b1305f4bbfeeabc2ad9db14cf904d40070c71a962eb2def07e098973bef");
        assertThat(response.getCogVersion()).isNotNull();


    }


    @Test
    void getModelReadMeTest() {
        String owner = "anthropic";
        String modelName = "claude-3.7-sonnet";
        String response = replicate.models().getModelReadme(owner, modelName);

        assertThat(response).isNotNull();
    }


    @Test void deleteModelTest() throws InterruptedException {

        Model model = new Model();
        model.setName(modelToCreate);
        model.setOwner("nilsw13");
        model.setHardware("cpu");
        model.setVisibility("private");
        System.out.println("modelToCreate : " + modelToCreate + "/" + " " + "modelName : " + model.getName());
        Model preResponse = replicate.models().create(model);
        assertThat(preResponse).isNotNull();
        Thread.sleep(3000);

        String owner = "nilsw13";


        Model response = replicate.models().delete(owner, modelToCreate);


        assertThat(response).isNull();

        try {
            Model model1 = replicate.models().get(owner, modelToCreate);
            fail("Model still exists");
        } catch (ReplicateApiException e) {
            assertThat(e.getResponseBody()).contains("This model has been deleted");
        }
    }




    @Test
    void createPredictionFromModelTest() throws InterruptedException {
        Prediction prediction = replicate.models().createModelPrediction("meta", "meta-llama-3-70b-instruct")
                .input("prompt", "Write a short poem about the weather")
                .executeFromModel(true, 60);

        System.out.println(prediction.getOutput());

    }


}
