package cn.stq.learn.springboot.listener;

import java.util.Iterator;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import cn.stq.learn.utils.JSONFormatUtil;
import cn.stq.learn.utils.LogUtil;
import cn.stq.learn.utils.TestUtil;

/**
 * spring boot 配置环境事件监听 spring boot对应Enviroment已经准备完毕，但此时上下文context还没有创建。<br/>
 * 
 * @update
 * @create 2016年10月20日 上午9:54:01
 * @author tianquan.shi<tianquan.shi@msxf.com>
 */
public class MyApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		// DO 
		LogUtil.getAppLogger().info(">>>>>>>>MyApplicationEnvironmentPreparedEvent");
		ConfigurableEnvironment envi = event.getEnvironment();
		MutablePropertySources mps = envi.getPropertySources();
		if (mps != null) {
			Iterator<PropertySource<?>> iter = mps.iterator(); 
			while (iter.hasNext()) {
				PropertySource<?> ps = iter.next();
				LogUtil.getAppLogger().info("ps.getName:{}; ps.getSource:{}; ps.getClass:{}", ps.getName(),
						JSONFormatUtil.formatJson(ps.getSource()), ps.getClass());
			}
		}
		LogUtil.getAppLogger().info("MyApplicationEnvironmentPreparedEvent<<<<<<<<");
	}

}
