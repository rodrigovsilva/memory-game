package com.rodrigovsilva.memorygame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.concurrent.TimeUnit;

/**
 * Spring boot main application.
 *
 * @author Rodrigo Silva
 */
@EnableJpaAuditing
@EnableJpaRepositories
@SpringBootApplication
public class MemoryGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoryGameApplication.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages", "classpath:validation-messages");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setCacheSeconds((int) TimeUnit.MINUTES.toSeconds(5));
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
