package com.example.imageration.service;

import java.util.List;
import java.util.Map;

import org.jgroups.JChannel;

import com.example.imageration.bean.Node;

public interface INodeManagementService {
	Node getNode(String node);
	List<Node> getAllNode();
	Map<String,Object> getChannelConfigInfo();
	JChannel getChannel();
}
