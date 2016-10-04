package cn.stq.learn.springboot.boot.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		filterChain.doFilter(request, response);
		//响应应答
	}
}
