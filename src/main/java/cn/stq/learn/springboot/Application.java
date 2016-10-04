package cn.stq.learn.springboot;

import org.springframework.boot.builder.SpringApplicationBuilder;

import cn.stq.learn.springboot.boot.BootStarp;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//@SpringBootApplication(scanBasePackages={"cn.stq.learn.springboot"})
public class Application extends BootStarp{

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(Application.class).profiles("company").run(args);
    }

}
