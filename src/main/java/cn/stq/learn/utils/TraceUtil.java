/**
 * 
 */
package cn.stq.learn.utils;

/**
 * @update 
 * @create 2016年10月9日 上午11:08:49
 * @author tianquan.shi<tianquan.shi@msxf.com> 
 */
public class TraceUtil {
	
	/** 获取当前方法名称 */
	public static final String getFunName() {
		return Thread.currentThread().getStackTrace()[2].toString();
	}
}
