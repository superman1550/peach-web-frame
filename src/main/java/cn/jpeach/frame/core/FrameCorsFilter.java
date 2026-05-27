package cn.jpeach.frame.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class FrameCorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 1. 设置 CORS 响应头（务必在 chain.doFilter 之前）
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // 生产环境建议替换为具体域名
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
		response.setHeader("Access-Control-Allow-Credentials", "true"); // 允许携带Cookie时设为true，且Allow-Origin不能为*
		response.setHeader("Access-Control-Max-Age", "3600"); // 预检请求缓存1小时
		// 2. 处理 OPTIONS 预检请求（直接返回成功，不继续执行业务逻辑）
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		}
		// 3. 其他请求正常放行
		chain.doFilter(req, res);
	}

}
