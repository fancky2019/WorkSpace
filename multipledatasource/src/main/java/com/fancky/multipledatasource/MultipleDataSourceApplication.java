package com.fancky.multipledatasource;

import com.fancky.multipledatasource.configuration.DemoDataSourceConfigParameters;
import com.fancky.multipledatasource.configuration.TestDataSourceConfigParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//在配置参数类加@Component取代
//@EnableConfigurationProperties(value = {TestDataSourceConfigParameters.class, DemoDataSourceConfigParameters.class})
public class MultipleDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDataSourceApplication.class, args);
    }

}
