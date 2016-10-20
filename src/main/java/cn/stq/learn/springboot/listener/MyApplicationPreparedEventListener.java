package cn.stq.learn.springboot.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import cn.stq.learn.utils.LogUtil;

/**
 * spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。
 * 上下文创建完成后执行的事件监听器
 * @update 
 * @create 2016年10月20日 上午10:18:12
 * @author tianquan.shi<tianquan.shi@msxf.com>
 */
public class MyApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {
		// TODO 传递上下文
		LogUtil.getAppLogger().info(">>>>>>>>MyApplicationPreparedEventListener");
		ConfigurableApplicationContext ctx = event.getApplicationContext();
        passContextInfo(ctx);
        LogUtil.getAppLogger().info("MyApplicationPreparedEventListener<<<<<<<<");
	}

	private void passContextInfo(ConfigurableApplicationContext ctx) {
		// TODO Auto-generated method stub
		LogUtil.getAppLogger().info(ctx.getId());
	}

}
