package cn.jpeach.frame.core;

import cn.jpeach.frame.entity.UpdateRecord;

public class FrameObject {
	void addChange(String winId, String compId, String action, Object data) {
		FrameInstance instance = FrameInstance.getContext();
		instance.addChange(new UpdateRecord(winId, compId, action, data));
	}
}
