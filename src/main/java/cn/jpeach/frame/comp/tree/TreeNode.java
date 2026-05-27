package cn.jpeach.frame.comp.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	private String id;
	private String text;
	private String icon;
	private boolean checked;
	private boolean opened;
	private List<TreeNode> children = new ArrayList<TreeNode>();

	public TreeNode() {
		super();
	}

	public TreeNode(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

}
