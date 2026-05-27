package cn.jpeach.frame.comp;

import java.util.List;
import java.util.Objects;

import cn.jpeach.frame.action.SelectListener;
import cn.jpeach.frame.comp.tree.TreeNode;
import cn.jpeach.frame.core.Constant;
import cn.jpeach.frame.core.WebForm;
import cn.jpeach.frame.core.WebWidget;

public final class PxTree extends WebWidget {
	private List<TreeNode> nodes;

	public PxTree(WebForm form) {
		super(form);
	}

	public TreeNode getSelected() {
		return this.findNode(this.nodes, this.getAppString(Constant.PRO_SELECTED));
	}

	public List<TreeNode> getData() {
		return this.nodes;
	}

	public void select(String id) {
		String currSelected = this.getAppString(Constant.PRO_SELECTED);
		if (currSelected.equals(id)) {
			return;
		}
		if (this.findNode(this.nodes, id) != null) {
			this.setAppObject(Constant.PRO_SELECTED, id);
			this.addChange(Constant.ACTION_SELECT, id);
		}
	}

	public void check(String[] ids) {
		this.setAppObject(Constant.PRO_CHECKED, ids);
		this.addChange(Constant.ACTION_SELECT, ids);
	}

	public void collapse(String id) {
		this.addChange(Constant.ACTION_COLLAPSE, id);
	}

	public void expand(String id) {
		this.addChange(Constant.ACTION_EXPAND, id);
	}

	public void setData(List<TreeNode> nodes) {
		this.nodes = nodes;
		this.setAppObject(Constant.PRO_DATA, nodes);
		this.addChange(Constant.ACTION_SET_DATA, nodes);
	}

	public void addSelectListener(SelectListener listener) {
		this.addListener(listener);
	}

	@Override
	protected void setAppProperty(String key, Object[] values) {
		if (Constant.PRO_SELECTED.equals(key)) {
			this.setAppString(Constant.PRO_SELECTED, Objects.toString(values[0], null));
		}
	}

	/**
	 * 根据ID查找节点
	 * 
	 * @param nodes
	 * @param id
	 * @return
	 */
	private TreeNode findNode(List<TreeNode> nodes, String id) {
		for (TreeNode node : nodes) {
			if (node.getId().equals(id)) {
				return node;
			}
			TreeNode find = this.findNode(node.getChildren(), id);
			if (find != null) {
				return find;
			}
		}
		return null;
	}
}
