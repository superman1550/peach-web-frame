package cn.jpeach.frame.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.jpeach.frame.comp.layout.LayoutOptiones;

public abstract class FrameWindow extends FrameObject {
	private static final String LAYOUT_CENTER = "center";
	private static final String LAYOUT_NORTH = "north";
	private static final String LAYOUT_EAST = "east";
	private static final String LAYOUT_SOUTH = "south";
	private static final String LAYOUT_WEST = "west";
	private final Map<String, WebForm> forms = new HashMap<String, WebForm>();

	public void refresh() {
		this.addChange(Constant.ACTION_REFRESH_WIN, new Object[] {});
	}

	protected abstract void create(String[] parameters);

	public void addCenterFrom(WebForm form) {
		this.addForm(LAYOUT_CENTER, form, LayoutOptiones.getDefaultOption());
	}

	public void addNorthFrom(WebForm form) {
		this.addNorthFrom(form, LayoutOptiones.getDefaultOption());
	}

	public void addNorthFrom(WebForm form, LayoutOptiones optiones) {
		this.addForm(LAYOUT_NORTH, form, optiones);
	}

	public void addSouthFrom(WebForm form) {
		this.addSouthFrom(form, LayoutOptiones.getDefaultOption());
	}

	public void addSouthFrom(WebForm form, LayoutOptiones optiones) {
		this.addForm(LAYOUT_SOUTH, form, optiones);
	}

	public void addEastFrom(WebForm form) {
		this.addEastFrom(form, LayoutOptiones.getDefaultOption());
	}

	public void addEastFrom(WebForm form, LayoutOptiones optiones) {
		this.addForm(LAYOUT_EAST, form, optiones);
	}

	public void addWestFrom(WebForm form) {
		this.addWestFrom(form, LayoutOptiones.getDefaultOption());
	}

	public void addWestFrom(WebForm form, LayoutOptiones optiones) {
		this.addForm(LAYOUT_WEST, form, optiones);
	}

	public void removeNorthForm() {
		this.removeForm(LAYOUT_NORTH);
	}

	public void removeSouthForm() {
		this.removeForm(LAYOUT_SOUTH);
	}

	public void removeEastForm() {
		this.removeForm(LAYOUT_EAST);
	}

	public void removeWestForm() {
		this.removeForm(LAYOUT_WEST);
	}

	public void setCenterFormTitle(String title) {
		this.setFormTitle(LAYOUT_CENTER, title);
	}

	public void setNorthFormTitle(String title) {
		this.setFormTitle(LAYOUT_NORTH, title);
	}

	public void setSouthFormTitle(String title) {
		this.setFormTitle(LAYOUT_SOUTH, title);
	}

	public void setWestFormTitle(String title) {
		this.setFormTitle(LAYOUT_WEST, title);
	}

	public void setEastFormTitle(String title) {
		this.setFormTitle(LAYOUT_EAST, title);
	}

	public WebForm getCenterForm() {
		return this.getForm(LAYOUT_CENTER);
	}

	public WebForm gettNorthForm() {
		return this.getForm(LAYOUT_NORTH);
	}

	public WebForm getSouthForm() {
		return this.getForm(LAYOUT_SOUTH);
	}

	public WebForm getWestForm() {
		return this.getForm(LAYOUT_WEST);
	}

	public WebForm getEastForm() {
		return this.getForm(LAYOUT_EAST);
	}

	private void addForm(String postion, WebForm form, LayoutOptiones optiones) {
		FrameInstance instance = FrameInstance.getContext();
		if (this.forms.containsKey(postion)) {
			instance.removeSection(form);
		}
		this.forms.put(postion, form);
		instance.addSection(form);
		SerializeComponentPeer peer = SerializeComponent.create(form);
		this.addChange(Constant.ACTION_ADD_FORM, new Object[] { postion, form.appId, peer, optiones });
	}

	private void removeForm(String postion) {
		FrameInstance instance = FrameInstance.getContext();
		if (this.forms.containsKey(postion)) {
			instance.removeSection((WebForm) this.forms.get(postion));
			this.forms.remove(postion);
		}
		this.addChange(Constant.ACTION_REMOVE_FORM, postion);
	}

	private void setFormTitle(String postion, String title) {
		this.addChange(Constant.ACTION_SET_FORM_TITLE, new Object[] { postion, title });
	}

	private WebForm getForm(String postion) {
		return this.forms.get(postion);
	}

	private void addChange(String action, Object data) {
		this.addChange(StringUtils.EMPTY, StringUtils.EMPTY, action, data);
	}
}
