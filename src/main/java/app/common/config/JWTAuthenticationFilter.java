package app.common.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import app.entity.User;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	 public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	  public static final String SECRET = "SecretKeyToGenJWTs";
	    public static final String TOKEN_PREFIX = "Bearer ";
	    public static final String HEADER_STRING = "Authorization";
	    public static final String SIGN_UP_URL = "/users/sign-up";
	
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User  creds = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);
            /*
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
            */
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
            
            return authentication;
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        
        //res.getWriter().append("{\""+HEADER_STRING + "\": "+ "\"" + TOKEN_PREFIX + token + "\"" + " }");
        res.getWriter().append("{\"");
        res.getWriter().append( HEADER_STRING + "\": "+ "\"" + TOKEN_PREFIX + token + "\"");
        res.getWriter().append(",");
        res.getWriter().append( "\"UserName" + "\": "+ "\"" + ((User) auth.getPrincipal()).getUsername() + "\"");
        res.getWriter().append( " }");
        
        
        res.setContentType("application/json");
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}