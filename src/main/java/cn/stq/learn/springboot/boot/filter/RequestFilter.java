package cn.stq.learn.springboot.boot.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;

import cn.stq.learn.utils.LogUtil;

public class RequestFilter extends OncePerRequestFilter {
	protected String filterURL;

	public RequestFilter(final String filterURL) {
		// TODO Auto-generated constructor stub
		this.filterURL = filterURL;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return StringUtils.isNotEmpty(filterURL) && !request.getServletPath().startsWith(this.filterURL);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 验证时间戳
		// 验证签名
		// 解密
		LogUtil.getAppLogger().info("得到请求参数--> {}",JSON.toJSONString(request.getParameterMap()));
		//处理其请求
//		RequestParameterWrapper requestWrapper = new RequestParameterWrapper(request);
//		ResponseContentWrapper responseWrapper = new ResponseContentWrapper(response);
		filterChain.doFilter(request, response);
		//响应应答
//		LogUtil.getAppLogger().error("应答--> {}",JSON.toJSONString(response));
		
	}
	
	/**
	 * 可手工设置HttpServletRequest入参的Wrapper
	 * @create Dec 19, 2015 9:06:04 PM
	 * @author 玄玉<http://blog.csdn.net/jadyer>
	 */
	private class RequestParameterWrapper extends HttpServletRequestWrapper{
		private Map<String, String[]> paramMap = new HashMap<>();
		RequestParameterWrapper(HttpServletRequest request) {
			super(request);
			this.paramMap.putAll(request.getParameterMap());
		}
		@Override
		public String getParameter(String name) {
			String[] values = this.paramMap.get(name);
			if(null==values || values.length==0){
				return null;
			}
			return values[0];
		}
		@Override
		public Map<String, String[]> getParameterMap() {
			return this.paramMap;
		}
		@Override
		public Enumeration<String> getParameterNames() {
			return new Vector<>(this.paramMap.keySet()).elements();
		}
		@Override
		public String[] getParameterValues(String name) {
			return this.paramMap.get(name);
		}
		void addParameter(String name, Object value){
			if(null != value){
				if(value instanceof String[]){
					this.paramMap.put(name, (String[])value);
				}else if(value instanceof String){
					this.paramMap.put(name, new String[]{(String)value});
				}else{
					this.paramMap.put(name, new String[]{String.valueOf(value)});
				}
			}
		}
		void addAllParameters(Map<String, Object> allParams){
			for(Map.Entry<String,Object> entry : allParams.entrySet()){
				this.addParameter(entry.getKey(), entry.getValue());
			}
		}
	}


	/**
	 * 可手工设置HttpServletResponse出参的Wrapper
	 * @create Dec 19, 2015 9:07:09 PM
	 * @author 玄玉<http://blog.csdn.net/jadyer>
	 */
	private class ResponseContentWrapper extends HttpServletResponseWrapper {
		private ResponsePrintWriter writer;
		private OutputStreamWrapper outputWrapper;
		private ByteArrayOutputStream output;
		ResponseContentWrapper(HttpServletResponse httpServletResponse) {
			super(httpServletResponse);
			output = new ByteArrayOutputStream();
			outputWrapper = new OutputStreamWrapper(output);
			writer = new ResponsePrintWriter(output);
		}
		public void finalize() throws Throwable {
			super.finalize();
			output.close();
			writer.close();
		}
		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			return outputWrapper;
		}
		public String getContent() {
			try {
				writer.flush();
				return writer.getByteArrayOutputStream().toString("utf-8");
			} catch (UnsupportedEncodingException e) {
				return "UnsupportedEncoding";
			}
		}
		public void close() throws IOException {
			writer.close();
		}
		public PrintWriter getWriter() throws IOException {
			return writer;
		}
		private class ResponsePrintWriter extends PrintWriter {
			ByteArrayOutputStream output;
			ResponsePrintWriter(ByteArrayOutputStream output) {
				super(output);
				this.output = output;
			}
			ByteArrayOutputStream getByteArrayOutputStream() {
				return output;
			}
		}
		private class OutputStreamWrapper extends ServletOutputStream {
			ByteArrayOutputStream output;
			OutputStreamWrapper(ByteArrayOutputStream output) {
				this.output = output;
			}
			@Override
			public boolean isReady() {
				return true;
			}
			@Override
			public void setWriteListener(WriteListener listener) {
				throw new UnsupportedOperationException("UnsupportedMethod setWriteListener.");
			}
			@Override
			public void write(int b) throws IOException {
				output.write(b);
			}
		}
	}
}
