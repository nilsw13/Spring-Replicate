package com.nilsw13.spring_replicate.integration.webhookSigning;

import com.nilsw13.spring_replicate.integration.BaseReplicateTest;
import com.nilsw13.spring_replicate.ResponseType.webhook.SigningSecretDefaultWebhook;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WebhookSigningSecretTest extends BaseReplicateTest {



    @Test
    void testWebhookSecretSigning() {
        SigningSecretDefaultWebhook signingWebhookResponse = replicate.webhook().getDefaultSecretSigningWebhook();

        assertThat(signingWebhookResponse).isNotNull();
        assertThat(signingWebhookResponse.getKey()).isNotEmpty();
        System.out.println(signingWebhookResponse.getKey());
    }
}
