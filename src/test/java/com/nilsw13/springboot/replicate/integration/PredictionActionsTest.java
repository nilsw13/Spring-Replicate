package com.nilsw13.springboot.replicate.integration;

import com.nilsw13.springboot.replicate.responsetype.prediction.Prediction;
import com.nilsw13.springboot.replicate.responsetype.prediction.PredictionsList;
import com.nilsw13.springboot.replicate.responsetype.webhook.WebhookEvent;
import com.nilsw13.springboot.replicate.exception.ReplicateApiException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration-test")
 class PredictionActionsTest extends BaseReplicateTest {

    @Test
    void testGetPredictionList() {
        PredictionsList predictionsList = replicate.predictions().list();

        assertThat(predictionsList).isNotNull();
        assertThat(predictionsList.getResults()).isNotEmpty();
        assertThat(predictionsList.getPrevious()).isNull();
        assertThat(predictionsList.getNext()).isNotNull();
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
    void testCreatePrediction() throws InterruptedException {
        // 1. Configuration
        String modelVersion = "ideogram-ai/ideogram-v2a";
        String testPrompt = "A beautiful contact section icon";
        int timeoutSeconds = 60;

        // 2. Execution
        Prediction response = replicate.predictions()
                .create(modelVersion)
                .input("prompt", testPrompt)
                .execute(true, timeoutSeconds);

        // 3. Verification
        assertThat(response)
                .isNotNull()
                .satisfies(pred -> {
                    assertThat(pred.getId()).isNotBlank();
                    assertThat(pred.getCreatedAt()).isNotBlank();
                    assertThat(pred.getModel()).isEqualTo("ideogram-ai/ideogram-v2a");
                    assertThat(pred.getStatus()).isIn("starting", "processing", "completed");
                    assertThat(pred.getUrls()).isNotEmpty();
                    assertThat(pred.getInput())
                            .isNotNull()
                            .extracting("prompt")
                            .isEqualTo(testPrompt);
                });

        // Verification conditionnelle si complété
        if ("completed".equals(response.getStatus())) {
            assertThat(response.getCompletedAt()).isNotBlank();
            assertThat(response.getMetrics()).isNotEmpty();
            assertThat(response.getError()).isNull(); //
        }


    }

    @Test
    void testPredictionWithoutWaitingThenDeleteIt() throws InterruptedException, IOException {
        File testImage = new File("src/test/resources/computer.png");
        File testFile = new File("src/test/resources/test.txt");
        List<WebhookEvent> eventList = new ArrayList<>();
        eventList.add(WebhookEvent.START);
        eventList.add(WebhookEvent.COMPLETED);

        String modelVersion = "anthropic/claude-3.7-sonnet";
        Prediction response = replicate.predictions().create(modelVersion)
                .image("image", testImage)
                .file("file", testFile)
                .input("prompt", "an beautiful contact section icon")
                .webhook("https://testWebhook.com")
                .webhookEventFilter(eventList)
                .execute(false);



        assertThat(response.getId()).isNotNull();
         replicate.predictions().cancel(response.getId());

        try {
            Prediction optPred = replicate.predictions().get(response.getId());
            assertThat(optPred.getStatus()).isEqualTo("canceled");
        } catch (ReplicateApiException e) {
            AssertionsForClassTypes.assertThat(e.getResponseBody()).contains("Prediction was canceled");

            assertThat(e.getStatusCode()).isEqualTo(404);
        }
    }

    @Test
    void testExecuteFromModelTest() throws InterruptedException, IOException {
        File testImage = new File("src/test/resources/computer.png");


        Map<String , Object> inputs = new HashMap<>();
        inputs.put("prompt" , "a small poem About city of Marseille");
        inputs.put("system_prompt", "Talk to me in french and inspire you answer from the image file");

        List<WebhookEvent> eventList = new ArrayList<>();
        eventList.add(WebhookEvent.START);
        eventList.add(WebhookEvent.COMPLETED);


        Prediction prediction = replicate.models().createModelPrediction("anthropic", "claude-3.7-sonnet" )
                .inputs(inputs)
                .image("image", testImage)
                .webhook("https://testwebhook1.com")
                .webhookEventFilter(eventList)
                .executeFromModel(true, 60);



        assertThat(prediction.getId()).isNotNull();

    }

}
