package com.example.imageration.bean;

public class Node {
	private String nodeId;
	private String nodeName;
	private String address;
	private String group;
	
	private String status;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Node [nodeId=" + nodeId + ", nodeName=" + nodeName
				+ ", address=" + address + ", group=" + group + ", status="
				+ status + "]";
	}

}
