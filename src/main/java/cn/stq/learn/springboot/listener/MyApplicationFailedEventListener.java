package cn.stq.learn.springboot.listener;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

import cn.stq.learn.utils.LogUtil;

/**
 * spring boot启动异常时执行事件 
 * @update 
 * @create 2016年10月20日 上午10:35:26
 * @author tianquan.shi<tianquan.shi@msxf.com>
 */
public class MyApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {

	@Override
	public void onApplicationEvent(ApplicationFailedEvent event) {
		// TODO Auto-generated method stub
		LogUtil.getAppLogger().error(">>>>>>>>MyApplicationFailedEventListener");
		LogUtil.getAppLogger().error("启动失败.");
		LogUtil.getAppLogger().error(event.getException().getMessage());
		LogUtil.getAppLogger().error("MyApplicationFailedEventListener<<<<<<<<");
	}

}
