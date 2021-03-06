/**
 * 
 */
package cn.stq.learn.utils;

import com.alibaba.fastjson.JSON;

/**
 * @update 
 * @create 2016年10月9日 上午11:08:49
 * @author tianquan.shi<tianquan.shi@msxf.com> 
 */
public class TestUtil {
	
	/** 获取当前方法名称 */
	public static final String getFunName() {
		return Thread.currentThread().getStackTrace()[2].toString();
	}
	
	/** 获取当前方法名称 */
	public static final String getFunName(int i) {
		return Thread.currentThread().getStackTrace()[i].toString();
	}
	
	/**
	 * 以JSON格式,在控制台输入对象.
	 * @param t
	 */
	public static <T> void out(T t){
		System.out.println(getFunName(3));
		System.out.println(t);
	}

	/**
	 * 以JSON格式,在控制台输入对象.
	 * @param t
	 */
	public static <T> void outAsJSON(T t){
		System.out.println(getFunName(3));
		System.out.println(formatJSON(t));
	}

	/**
	 * 格式化对象为JSON 
	 * @param t
	 * @return
	 */
	public static <T> String formatJSON(T t) {
		if(String.class == t.getClass()){
			return formatJson((String)t);
		}
		else{
			return formatJson(JSON.toJSONString(t));
		}
	}
	
	/**
	 * 格式化JSON 
	 * @param json - json
	 */
	public static String formatJson(String json){
		return formatJson(json, "   ");
	}
	
	/**
	 * 格式化JSON 
	 * @param json - json
	 * @param sep - 分隔符
	 */
	public static String formatJson(String json,final String sep)  
    {  
        StringBuilder result = new StringBuilder();  
        int length = json.length();  
        int number = 0;  
        char key = 0;  
          
        //遍历输入字符串。  
        for (int i = 0; i < length; i++)  
        {  
            //1、获取当前字符。  
            key = json.charAt(i);  
            //2、如果当前字符是前方括号、前花括号做如下处理：  
            if((key == '[') || (key == '{') )  
            {  
                //（1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。  
                if((i - 1 > 0) && (json.charAt(i - 1) == ':'))  
                {  
                    result.append('\n');  
                    result.append(indent(number,sep));  
                }  
                  
                //（2）打印：当前字符。  
                result.append(key);  
                //（3）前方括号、前花括号，的后面必须换行。打印：换行。  
                result.append('\n');  
                //（4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。  
                number++;  
                result.append(indent(number,sep));  
                //（5）进行下一次循环。  
                continue;  
            }  
              
            //3、如果当前字符是后方括号、后花括号做如下处理：  
            if((key == ']') || (key == '}') )  
            {  
                //（1）后方括号、后花括号，的前面必须换行。打印：换行。  
                result.append('\n');  
                //（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。  
                number--;  
                result.append(indent(number,sep));  
                //（3）打印：当前字符。  
                result.append(key);  
                //（4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。  
                if(((i + 1) < length) && (json.charAt(i + 1) != ','))  
                {  
                    result.append('\n');  
                }  
                  
                //（5）继续下一次循环。  
                continue;  
            }  
            //4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。  
            if((key == ','))  
            {  
                result.append(key);  
                result.append('\n');  
                result.append(indent(number,sep));  
                continue;  
            }  
            //5、打印：当前字符。  
            result.append(key);  
        }  
          
        return result.toString();  
    }  
      
    /** 
     * 返回指定次数的缩进字符串
     *  
     * @param number 缩进次数。 
     * @param sep 	  分隔符 
     * @return 指定缩进次数的字符串。 
     */  
    private static String indent(final int number,final String sep)  
    {  
        StringBuffer result = new StringBuffer();  
        for(int i = 0; i < number; i++)  
        {  
            result.append(sep);  
        }  
        return result.toString();  
    }
}
