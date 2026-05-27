package cn.jpeach.frame.core;

import java.util.ArrayList;

public class SerializeComponent {
	static SerializeComponentPeer create(WebComponent component) {
		SerializeComponentPeer peer = new SerializeComponentPeer();
		peer.setProperties(component.properties);
		peer.setChildren(new ArrayList<SerializeComponentPeer>());
		peer.setName(component.getClass().getSimpleName());
		peer.setId(component.appId);
		peer.setListeners(new ArrayList<String>());
		for (FrameListener listener : component.listeners) {
			peer.getListeners().add(listener.name);
		}
		for (WebComponent child : component.children) {
			peer.getChildren().add(create(child));
		}
		return peer;
	}
}
