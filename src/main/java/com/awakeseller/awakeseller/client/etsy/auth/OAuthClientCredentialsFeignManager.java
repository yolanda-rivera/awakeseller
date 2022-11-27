package com.awakeseller.awakeseller.client.etsy.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

public class OAuthClientCredentialsFeignManager {

    private final OAuth2AuthorizedClientManager manager;
    private final Authentication principal;
    private final ClientRegistration clientRegistration;

    public OAuthClientCredentialsFeignManager(final OAuth2AuthorizedClientManager manager,
            final ClientRegistration clientRegistration) {
        this.manager = manager;
        this.clientRegistration = clientRegistration;
        this.principal = createPrincipal();
    }

    private Authentication createPrincipal() {
        return new EtsyAuthenticationPrincipal(clientRegistration.getClientId());
    }

    public String getAccessToken() {
        final OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId(clientRegistration.getRegistrationId())
                .principal(principal)
                .build();
        final OAuth2AuthorizedClient client = manager.authorize(oAuth2AuthorizeRequest);
        if (client == null) {
            throw new IllegalStateException("client credentials flow on " + clientRegistration.getRegistrationId()
                    + " failed, client is null");
        }
        return client.getAccessToken().getTokenValue();
    }
}