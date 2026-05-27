package cn.jpeach.frame.util;

import javax.servlet.http.HttpServletRequest;

public class ClientUtil {
	private static final String[] HEADERS_TO_TRY = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };

	/**
	 * 获取客户端真实 IP 地址
	 * 
	 * @param request HttpServletRequest
	 * @return 客户端 IP，如果无法获取则返回 "unknown"
	 */
	public static String getClientIp(HttpServletRequest request) {
		for (String header : HEADERS_TO_TRY) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				// 多级代理情况下，X-Forwarded-For 可能返回多个 IP，取第一个（最左边）
				if (ip.contains(",")) {
					ip = ip.split(",")[0].trim();
				}
				return ip;
			}
		}
		// 没有代理头时，直接获取远程地址（可能是客户端 IP，也可能是代理 IP）
		return request.getRemoteAddr();
	}
}
