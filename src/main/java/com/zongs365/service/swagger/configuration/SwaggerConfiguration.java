package com.zongs365.service.swagger.configuration;

import com.google.common.collect.Lists;
import com.zongs365.service.swagger.properties.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
@Scope(value = "prototype")
public class SwaggerConfiguration {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    @Primary
    @Scope(value = "prototype")
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return new SwaggerResourcesProvider() {
            @Override
            public List<SwaggerResource> get() {
                return getSwaggerResource();
            }

            private List<SwaggerResource> getSwaggerResource() {
                Map<String, SwaggerResource> modules = swaggerProperties.getModules();
                return Lists.newArrayList(modules.values());
            }
        };
    }

    @Bean
    @Scope(value = "prototype")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(swaggerProperties.getGroupName())
                .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, customerResponseMessage())
                .globalResponseMessage(RequestMethod.POST, customerResponseMessage())
                .globalResponseMessage(RequestMethod.PUT, customerResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, customerResponseMessage())
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage())) //这里写的是API接口所在的包位置
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<ApiKey> securitySchemes() {
        return newArrayList(
                new ApiKey(swaggerProperties.getAuthorizationKey(), swaggerProperties.getAuthorizationKey(), "header"));
    }

    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference(swaggerProperties.getAuthorizationKey(), authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDesc())
                .version(swaggerProperties.getVersion())
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                .license(swaggerProperties.getLicense())
                .licenseUrl(swaggerProperties.getLicenseUrl())
                .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
                .build();
    }

    private List<ResponseMessage> customerResponseMessage() {
        return newArrayList(
                /*new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value()).message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .responseModel(new ModelRef("全局错误响应(Global400ErrorRespModel)"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.UNAUTHORIZED.value()).message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                        .responseModel(new ModelRef("全局错误响应(Global401ErrorRespModel)"))
                        .build(),
                *//*new ResponseMessageBuilder()
                        .code(HttpStatus.FORBIDDEN.value()).message(HttpStatus.FORBIDDEN.getReasonPhrase())
                        .responseModel(new ModelRef("全局错误响应(Global403ErrorRespModel)"))
                        .build(),*//*
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_FOUND.value()).message(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .responseModel(new ModelRef("全局错误响应(Global404ErrorRespModel)"))
                        .build(),*/
                /*new ResponseMessageBuilder()
                        .code(HttpStatus.METHOD_NOT_ALLOWED.value()).message(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                        .responseModel(new ModelRef("全局错误响应(Global405ErrorRespModel)"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value()).message(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase())
                        .responseModel(new ModelRef("全局错误响应(Global406ErrorRespModel)"))
                        .build(),*/
                /*new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .responseModel(new ModelRef("全局错误响应(Global500ErrorRespModel)"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_GATEWAY.value()).message(HttpStatus.BAD_GATEWAY.getReasonPhrase())
                        .responseModel(new ModelRef("全局错误响应(Global502ErrorRespModel)"))
                        .build(),*/
                /*new ResponseMessageBuilder()
                        .code(HttpStatus.SERVICE_UNAVAILABLE.value()).message(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())
                        .responseModel(new ModelRef("全局错误响应(Global503ErrorRespModel)"))
                        .build(),*/
                /*new ResponseMessageBuilder()
                        .code(HttpStatus.GATEWAY_TIMEOUT.value()).message(HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase())
                        .responseModel(new ModelRef("全局错误响应(Global504ErrorRespModel)"))
                        .build()*/
        );
    }

}


