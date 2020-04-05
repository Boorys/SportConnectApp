package com.sportapp.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

public class SpringApplicationContext implements ApplicationContextAware
{
private static ApplicationContext CONTEXT;

@Override
public void setApplicationContext(ApplicationContext context) throws BeansException {
	CONTEXT = context;
	
}
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

}