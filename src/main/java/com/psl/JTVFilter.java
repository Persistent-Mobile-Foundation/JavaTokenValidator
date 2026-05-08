package com.psl;

import java.io.IOException;
import java.net.URI;

import com.ibm.mfp.java.token.validator.AuthenticationError;
import com.ibm.mfp.java.token.validator.TokenValidationException;
import com.ibm.mfp.java.token.validator.TokenValidationManager;
import com.ibm.mfp.java.token.validator.TokenValidationResult;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/rest/*", initParams = {
        @WebInitParam(name = "scope", value = "accessRestricted")
})
public class JTVFilter implements Filter {

    public static final String AUTH_HEADER = "Authorization";
    private static final String AUTHSERVER_URI = "http://localhost:9080/mfp/api"; // Set here your authorization server
                                                                                  // URI
    private static final String CLIENT_ID = "jtv"; // Set here your confidential client ID
    private static final String CLIENT_SECRET = "jtv"; // Set here your confidential client SECRET

    private TokenValidationManager validator;
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        URI uri = null;
        try {
            uri = new URI(AUTHSERVER_URI);
            validator = new TokenValidationManager(uri, CLIENT_ID, CLIENT_SECRET);
            this.filterConfig = filterConfig;
        } catch (Exception e1) {
            System.out.println("Error reading introspection URI");
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        String expectedScope = filterConfig.getInitParameter("scope");
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;

        String authCredentials = httpServletRequest.getHeader(AUTH_HEADER);

        try {
            TokenValidationResult tokenValidationRes = validator.validate(authCredentials, expectedScope);
            if (tokenValidationRes.getAuthenticationError() != null) {
                // Error
                AuthenticationError error = tokenValidationRes.getAuthenticationError();
                httpServletResponse.setStatus(error.getStatus());
                httpServletResponse.setHeader("WWW-Authenticate", error.getAuthenticateHeader());
            } else if (tokenValidationRes.getIntrospectionData() != null) {
                // Success
                httpServletRequest.setAttribute("introspection-data", tokenValidationRes.getIntrospectionData());
                chain.doFilter(req, res);
            }
        } catch (TokenValidationException e) {
            httpServletResponse.setStatus(500);
        }
    }
}
