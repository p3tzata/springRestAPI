package app.common.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import app.entity.Task;

@Component
public class SpringDataRestCustomization implements RepositoryRestConfigurer {

 @Override
 public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.getCorsRegistry().addMapping("/**")
           .allowedOrigins("*")
           // .allowedOrigins("*")
            .allowedMethods("GET","POST");
    
    
    config.exposeIdsFor(Task.class);
    
  }
 
 
 
}
