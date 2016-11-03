package cn.stq.learn.springboot.listener;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import cn.stq.learn.utils.LogUtil;

/**
 * spring boot启动开始时执行的事件
 * @update 
 * @create 2016年10月20日 上午9:49:15
 * @author tianquan.shi<tianquan.shi@msxf.com>
 */
public class MyApplicationStatedEventListener implements ApplicationListener<ApplicationStartedEvent> {

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		// TODO Auto-generated method stub
		SpringApplication app = event.getSpringApplication();
        app.setBannerMode(Banner.Mode.OFF);;// 不显示banner信息
        LogUtil.getAppLogger().info("==MyApplicationStartedEventListener==");
	}

}
