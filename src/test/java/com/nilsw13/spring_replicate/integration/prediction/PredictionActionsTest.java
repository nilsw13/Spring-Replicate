package com.nilsw13.spring_replicate.integration.prediction;

import com.nilsw13.spring_replicate.ResponseType.Prediction.Prediction;
import com.nilsw13.spring_replicate.ResponseType.Prediction.PredictionsList;
import com.nilsw13.spring_replicate.integration.BaseReplicateTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PredictionActionsTest extends BaseReplicateTest {

    @Test
    void testGetPredictionList() {
        PredictionsList predictionsList = replicate.predictions().list();

        assertThat(predictionsList).isNotNull();
        assertThat(predictionsList.getResults()).isNotEmpty();
        System.out.println(predictionsList.getResults());
    }

    @Test
    void testGetPredictionById(){
        String id = "ecbevw573srm80cnhaesdmp0rc";
        Prediction prediction = replicate.predictions().get(id);

        assertThat(prediction).isNotNull();
        assertThat(prediction.getInput()).isNotEmpty();

        System.out.println(prediction.getInput());
    }

    @Test
    void testCreatePredictionAndCancelIt() throws InterruptedException {
        String modelVersion = "ideogram-ai/ideogram-v2a";
        Prediction response = replicate.predictions().create(modelVersion)
                .input("prompt", "an beautiful contact section icon")
                .execute(true,60);

        assertThat(response.getId()).isNotNull();

        System.out.println("Created at : " + response.getCreatedAt());
        System.out.println("prediction id : " + response.getId());
    }

    @Test
    void testPredictionWithoutWaiting() throws InterruptedException {
        String modelVersion = "ideogram-ai/ideogram-v2a";
        Prediction response = replicate.predictions().create(modelVersion)
                .input("prompt", "an beautiful contact section icon")
                .execute(false);


        assertThat(response.getId()).isNotNull();
        System.out.println("Created at : " + response.getCreatedAt());
        System.out.println("prediction id : " + response.getId());
    }

}
