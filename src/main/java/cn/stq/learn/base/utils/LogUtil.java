package cn.stq.learn.base.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 日志工具类
 * @see -----------------------------------------------------------------------------------------------------------
 * @see Java日志性能那些事-----http://www.infoq.com/cn/articles/things-of-java-log-performance
 * @see Log4j2.x比Logback好----http://donlianli.iteye.com/blog/1921735
 * @see SpringSide使用Logback--https://github.com/springside/springside4/wiki/Log
 * @see -----------------------------------------------------------------------------------------------------------
 * @history v1.0-->通过<code>java.lang.ThreadLocal</code>实现日志记录器
 * @update Aug 26, 2015 3:29:21 PM
 * @create Dec 18, 2012 6:19:31 PM
 * @author tianquan.shi
 */
public final class LogUtil {
	private LogUtil(){}

	//自定义线程范围内共享的对象
	//即它会针对不同线程分别创建独立的对象,此时每个线程得到的将是自己的实例,各线程间得到的实例没有任何关联
	private static ThreadLocal<Logger> currentLoggerMap = new ThreadLocal<Logger>();

	//定义日志记录器
	private static Logger appLogger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);;

	public static Logger getAppLogger() {
		Logger logger = currentLoggerMap.get();
		if(null == logger){
			currentLoggerMap.set(appLogger);
			return appLogger;
		}else{
			return logger;
		}
	}
}