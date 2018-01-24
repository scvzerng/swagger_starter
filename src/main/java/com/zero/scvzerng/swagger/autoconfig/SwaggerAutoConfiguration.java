package com.zero.scvzerng.swagger.autoconfig;

import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * 生产环境不激活
 */
@Configuration
@ConditionalOnClass(EnableSwagger2.class)
@ConditionalOnWebApplication
@ConditionalOnProperty(SwaggerProperties.PREFIX+".name")
@EnableConfigurationProperties(SwaggerProperties.class)
@Profile({"dev","test","predeploy"})
@EnableSwagger2
public class SwaggerAutoConfiguration {
    @Resource
    SwaggerProperties swaggerProperties;
    @Bean
    @ConditionalOnMissingBean(Docket.class)
    public Docket docket(ApiInfo apiInfo){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    @ConditionalOnMissingBean(ApiInfo.class)
    public ApiInfo getApiInfo(){

        return new ApiInfoBuilder()
                .version(swaggerProperties.getVersion())
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .license(swaggerProperties.getLicense())
                .licenseUrl(swaggerProperties.getLicenseUrl())
                .contact(new Contact(swaggerProperties.getName(),swaggerProperties.getUrl(),swaggerProperties.getEmail()))
                .build();
    }


}
