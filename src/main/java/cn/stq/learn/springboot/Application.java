package cn.stq.learn.springboot;

import org.springframework.boot.builder.SpringApplicationBuilder;

import cn.stq.learn.springboot.boot.BootStarp;
import cn.stq.learn.springboot.listener.MyApplicationEnvironmentPreparedEventListener;
import cn.stq.learn.springboot.listener.MyApplicationFailedEventListener;
import cn.stq.learn.springboot.listener.MyApplicationPreparedEventListener;
import cn.stq.learn.springboot.listener.MyApplicationStatedEventListener;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//@SpringBootApplication(scanBasePackages={"cn.stq.learn.springboot"})
public class Application extends BootStarp{

    public static void main(String[] args) {
        new SpringApplicationBuilder()
        	.sources(Application.class)
        	.profiles("company")
        	.listeners(new MyApplicationStatedEventListener(),new MyApplicationEnvironmentPreparedEventListener(),new MyApplicationPreparedEventListener(),new MyApplicationFailedEventListener())
        	.run(args);
    }

}
