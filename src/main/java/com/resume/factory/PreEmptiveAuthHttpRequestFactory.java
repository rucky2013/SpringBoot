package com.resume.factory;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * Authenticate yourself when you go to github everytime.
 */
@SuppressWarnings("deprecation")
public class PreEmptiveAuthHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    private final CloseableHttpClient httpClient;
    private final static HttpRequestInterceptor HTTP_REQUEST_INTERCEPTOR = new PreemptiveAuthInterceptor();
    private final static String PREEMPTIVE_AUTH = "preemptive-auth";
    private final AtomicBoolean isInterceptorAdded = new AtomicBoolean(false);
    private static final AuthCache authCache = new BasicAuthCache();
    private static final AuthScheme basicAuth = new BasicScheme();
    private static final HttpContext localcontext = new BasicHttpContext();
    private final CredentialsProvider credentialsProvider;

    public PreEmptiveAuthHttpRequestFactory(final CloseableHttpClient httpClient, final CredentialsProvider credentialsProvider) {
        super(httpClient);
        this.httpClient = httpClient;
        this.credentialsProvider = credentialsProvider;
    }

    /**
     * Intercepter to authenticate one while requesting data from github.
     */
    private static class PreemptiveAuthInterceptor implements HttpRequestInterceptor {

        @Override
        public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
            final AuthState authState = (AuthState) context.getAttribute(HttpClientContext.TARGET_AUTH_STATE);
            if (authState.getAuthScheme() == null) {
                final AuthScheme authScheme = (AuthScheme) context.getAttribute(PREEMPTIVE_AUTH);
                final CredentialsProvider credsProvider = (CredentialsProvider) context.getAttribute(HttpClientContext.CREDS_PROVIDER);
                final Credentials credentials = credsProvider.getCredentials(AuthScope.ANY);
                authState.update(authScheme, credentials);
            }
            request.addHeader("User-Agent", "USER_AGENT");
        }
    }

    /**
     * Add intercepter to authenticate user while creating http context.
     */
    @Override
    protected HttpContext createHttpContext(final HttpMethod httpMethod, final URI uri) {
        final HttpHost targetHost = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        authCache.put(targetHost, basicAuth);
        localcontext.setAttribute(HttpClientContext.AUTH_CACHE, authCache);
        if (!isInterceptorAdded.get()) {
            ((AbstractHttpClient) httpClient).addRequestInterceptor(HTTP_REQUEST_INTERCEPTOR, 0);
            isInterceptorAdded.set(false);
        }
        localcontext.setAttribute(HttpClientContext.CREDS_PROVIDER, this.credentialsProvider);
        localcontext.setAttribute(PREEMPTIVE_AUTH, basicAuth);
        return localcontext;
    }
}
