package cn.jpeach.frame.core;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpeach.frame.conf.ConfigCache;
import cn.jpeach.frame.entity.GridData;
import cn.jpeach.frame.entity.RemoteData;
import cn.jpeach.frame.entity.RemoteParam;
import cn.jpeach.frame.entity.RequestData;
import cn.jpeach.frame.entity.ResponseData;
import cn.jpeach.frame.entity.SynchroData;
import cn.jpeach.frame.util.ClientUtil;
import cn.jpeach.frame.util.JsonUtil;

public final class FrameServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(FrameServlet.class);
	private static final long serialVersionUID = -1592375701934499923L;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			ConfigCache.parse();
			FrameDataSource.init();
			FrameContainer.init();
			logger.debug("初始化成功！");
		} catch (Exception e) {
			logger.error("初始化异常:", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		FrameInstance context = (FrameInstance) session.getAttribute(Constant.SESSION_CONTEXT);
		if (context == null) {
			resp.sendRedirect(req.getContextPath() + "/start");
			return;
		}
		context.setClientIp(ClientUtil.getClientIp(req));
		context.setSessionId(session.getId());
		FrameInstance.init(context);
		try {
			String type = req.getParameter("type");
			if ("1".equals(type)) {
				try {
					RemoteParam info = JsonUtil.readValue(req.getInputStream(), RemoteParam.class);
					logger.trace("接收=" + JsonUtil.writeValueAsString(info));
					RemoteData data = RemoteData.success();
					data.setData(this.remote(context, info));
					String respData = JsonUtil.writeValueAsString(data);
					logger.trace("返回=" + respData);
					resp.getWriter().write(respData);
				} catch (Exception e) {
					logger.error("系统异常", e);
					RemoteData data = RemoteData.error(String.format("系统异常：%s", e.getMessage()));
					String respData = JsonUtil.writeValueAsString(data);
					logger.trace("返回=" + respData);
					resp.getWriter().write(respData);
				}
			} else {
				try {
					RequestData info = JsonUtil.readValue(req.getInputStream(), RequestData.class);
					logger.trace("接收=" + JsonUtil.writeValueAsString(info));
					ResponseData responseInfo = ResponseData.success();
					responseInfo.setData(this.sync(context, info.getSync()));
					String respData = JsonUtil.writeValueAsString(responseInfo);
					logger.trace("返回=" + respData);
					resp.getWriter().write(respData);
				} catch (Exception e) {
					logger.error("系统异常", e);
					ResponseData responseInfo = ResponseData.error(String.format("系统异常：%s", e.getMessage()));
					String respData = JsonUtil.writeValueAsString(responseInfo);
					logger.trace("返回=" + respData);
					resp.getWriter().write(respData);
				}
			}
		} finally {
			FrameInstance.clean();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try {
			resp.getWriter().write(this.initSession(req));
		} catch (Exception e) {
			logger.error("加載异常:", e);
			resp.getWriter().write(e.getMessage());
		}
	}

	private String initSession(HttpServletRequest req) throws Exception {
		ServletContext context = this.getServletContext();
		FrameInstance instance = new FrameInstance();
		req.getSession().setAttribute(Constant.SESSION_CONTEXT, instance);
		return instance.start(context);
	}

	private Object[] sync(FrameInstance context, SynchroData object) {
		return context.exceute(object);
	}

	private GridData remote(FrameInstance context, RemoteParam info) {
		return context.remote(info);
	}
}
