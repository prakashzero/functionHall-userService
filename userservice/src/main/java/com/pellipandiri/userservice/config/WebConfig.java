package com.pellipandiri.userservice.config;

import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {



   // @Value("#{'${ui.endpoint}'.split(',')}")
   // private List<String> endpoints;

   // @Bean
   // public CorsFilter corsFilter() {
   //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
   //     CorsConfiguration config = new CorsConfiguration();

   //     // Allow all origins, you can restrict this to specific domains in production
   //     config.addAllowedOrigin("*");

   //     // Allow all headers
   //     config.addAllowedHeader("*");

   //     // Allow specific HTTP methods
   //     config.addAllowedMethod("GET");
   //     config.addAllowedMethod("POST");
   //     config.addAllowedMethod("PUT");
   //     config.addAllowedMethod("DELETE");
   //     config.addAllowedMethod("OPTIONS");

   //     // Allow credentials (cookies, authorization headers, etc.)
   //     config.setAllowCredentials(true);

   //     // Allow the client to expose specific headers in the response
   //     config.addExposedHeader("Authorization");
   //     config.addExposedHeader("Content-Type");

   //     // Apply CORS configuration to all paths
   //     source.registerCorsConfiguration("/**", config);

   //     return new CorsFilter(source);
   // }


   //  @Override
   //  public void addCorsMappings(CorsRegistry registry) {
   //      registry.addMapping("/api/**")
   //          .allowedOrigins(endpoints.get(0), endpoints.get(1),endpoints.get(2))
   //          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
   //          .allowedHeaders("*")
   //          .allowCredentials(true);
   //  }

     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.cors()
                    .and()
                    .csrf(csrf->csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .requestMatchers("/api/**").authenticated()
                            .anyRequest().permitAll()
                    ).httpBasic();

            return http.build();
     }
}
