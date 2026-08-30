package br.com.aesthetic.framework.in.rest.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * Cognito has a custom logout url.
 * See more information <a href="https://docs.aws.amazon.com/cognito/latest/developerguide/logout-endpoint.html">here</a>.
 */
public class CognitoLogoutHandler extends SimpleUrlLogoutSuccessHandler {

    /**
     * The domain of your user pool.
     */
    private String dominio = "https://us-east-1v1smi8yqw.auth.us-east-1.amazoncognito.com";

    /**
     * An allowed callback URL.
     */
    private String urlRedirecionaLogout = "http://thais-rosa-estetica-website.s3-website-us-east-1.amazonaws.com";

    /**
     * The ID of your User Pool Client.
     */
    private String userPoolClientId = "3ea44a5qfpskvg8ogvog1l9ggv";

    /**
     * Here, we must implement the new logout URL request. We define what URL to send our request to, and set out client_id and logout_uri parameters.
     */
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        return UriComponentsBuilder
                .fromUri(URI.create(dominio + "/logout"))
                .queryParam("client_id", userPoolClientId)
                .queryParam("logout_uri", urlRedirecionaLogout)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUriString();
    }
}