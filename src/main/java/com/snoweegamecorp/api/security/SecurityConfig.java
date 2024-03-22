package com.snoweegamecorp.api.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * This class is used to configure the security settings of the application.
 * It's annotated with @Configuration to indicate that this class contains bean definitions.
 * The @ConfigurationProperties annotation binds the properties with the specified prefix to the properties of this class.
 */
@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfig {
    // The prefix for the security configuration properties
    public static String PREFIX;
    // The key for the security configuration
    public static String KEY;
    // The expiration time for the security configuration
    public static Long EXPIRATION;
    /**
     * Setter method for the prefix.
     * This method is used to set the prefix value from the properties file.
     * @param prefix the prefix for the security configuration properties
     */
    public void setPREFIX(String prefix){
        PREFIX = prefix;
    }
    /**
     * Setter method for the key.
     * This method is used to set the key value from the properties file.
     * @param key the key for the security configuration
     */
    public void setKey(String key){
        KEY = key;
    }
    /**
     * Setter method for the expiration.
     * This method is used to set the expiration value from the properties file.
     * @param expiration the expiration time for the security configuration
     */
    public void setEXPIRATION(Long expiration){
        EXPIRATION = expiration;
    }
}
