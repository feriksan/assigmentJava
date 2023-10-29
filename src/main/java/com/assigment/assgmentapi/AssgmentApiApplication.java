package com.assigment.assgmentapi;

import com.assigment.assgmentapi.configs.YmlConfig;
import com.assigment.assgmentapi.properties.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class AssgmentApiApplication implements CommandLineRunner {
    
    @Autowired
    private YmlConfig myConfig;
    
    public static void main(String[] args) {
        SpringApplication.run(AssgmentApiApplication.class, args);
    }
    
    public void run(String... args)throws Exception{
        System.out.println("using environment: " + myConfig.getEnvironment());
        System.out.println("name: " + myConfig.getName());
        System.out.println("enabled:" + myConfig.isEnabled());
        System.out.println("port: " + myConfig.getServers());
    }
    
}
