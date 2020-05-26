package app.common.bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

@Component
@Order(1)
public class beanConfig {
	
	
	@Bean
	public BCryptPasswordEncoder get9BCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public Gson getGson() {
		return new Gson();
	}

	
	

	

	
	
	
	
	
	
	
}
