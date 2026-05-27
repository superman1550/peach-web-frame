package cn.jpeach.frame.entity;

public class SynchroData {
	private SynchroStackData[] stacks;
	private SynchroEventData event;

	public SynchroStackData[] getStacks() {
		return stacks;
	}

	public void setStacks(SynchroStackData[] stacks) {
		this.stacks = stacks;
	}

	public SynchroEventData getEvent() {
		return event;
	}

	public void setEvent(SynchroEventData event) {
		this.event = event;
	}
}
