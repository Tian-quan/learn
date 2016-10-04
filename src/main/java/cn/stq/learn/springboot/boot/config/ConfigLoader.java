package cn.stq.learn.springboot.boot.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = { "${db.file:classpath:config/db.properties}", "${config.file:classpath:config/config.properties}"})
public class ConfigLoader implements EnvironmentAware {
	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}