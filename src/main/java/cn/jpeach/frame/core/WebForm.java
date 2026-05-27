package cn.jpeach.frame.core;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cn.jpeach.frame.action.ActionEvent;
import cn.jpeach.frame.action.FormInitListener;
import cn.jpeach.frame.action.FrameMessageListener;
import cn.jpeach.frame.cache.CacheManager;
import cn.jpeach.frame.cache.FrameCache;
import cn.jpeach.frame.dao.BaseDao;

public abstract class WebForm extends WebComponent {
	final FrameCache<String, BaseDao> daoCache = CacheManager.newInstance();
	protected final WebMessager messager = new WebMessager(this.appId);

	public static WebForm create(Class<? extends WebForm> cls) {
		return DesignForm.create(cls);
	}

	@Override
	protected void setAppProperty(String key, Object[] values) {

	}

	/**
	 * 获取当前Form的预设宽度
	 * 
	 * @return
	 */
	protected int getWidth() {
		return this.getAppInt(Constant.PRO_WIDTH);
	}

	/**
	 * 获取当前Form的预设高度
	 * 
	 * @return
	 */
	protected int getHeight() {
		return this.getAppInt(Constant.PRO_HEIGHT);
	}

	/**
	 * 获取当前Form的预设标题
	 * 
	 * @return
	 */
	protected String getTitle() {
		return this.getAppString(Constant.PRO_TITLE);
	}

	/**
	 * 获取Form内的組件
	 * 
	 * @param id
	 * @return
	 */
	protected WebComponent getComponent(String id) {
		return this.getComponent(this.children, id);
	}

	/**
	 * Form初始化调用
	 */
	protected abstract void beforeShow();

	/**
	 * 
	 * @param flag
	 */
	protected abstract void modalCallback(int flag, Object para);

	/**
	 * 初始化组件
	 */
	protected void initComponent() {

	}

	protected void initEvent() {
		this.addAppListener(new FormInitListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				beforeShow();
			}
		});
		this.addAppListener(new FrameMessageListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] parameters = arg0.getParameters();
				modalCallback(NumberUtils.toInt(parameters[0], -1), parameters[1]);
			}
		});
	}

	/**
	 * 根据XML的ID属性获取窗口内组件
	 * 
	 * @param components
	 * @param id
	 * @return
	 */
	private WebComponent getComponent(List<WebComponent> components, String id) {
		for (WebComponent component : components) {
			String cid = component.getAppString(Constant.PRO_ID);
			if (StringUtils.isNotBlank(cid) && cid.equals(id)) {
				return component;
			}
			WebComponent find = this.getComponent(component.children, id);
			if (find != null) {
				return find;
			}
		}
		return null;
	}
}
