package com.example.imageration.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;

import com.example.imageration.IExampleAppContext;
import com.example.imageration.bean.Node;
import com.example.imageration.service.INodeManagementService;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;

public class NodeManagementServiceImpl extends
		AbstractModule<IExampleAppContext> implements INodeManagementService {
	private JChannel channel;
	private final static Trace log = Trace.register(NodeManagementServiceImpl.class);
	private Map<Address,Node> nodes = new ConcurrentHashMap<Address, Node>();
	
	@Override
	public Node getNode(String nodeId) {
		return nodes.get(nodeId);
	}

	@Override
	public List<Node> getAllNode() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.addAll(this.nodes.values());
		return nodes;
	}

	@Override
	protected void initServiceDependency() {
		
	}
	private void createNode(Address addr, boolean createAbsent){
		Node node = nodes.get(addr);
		if (createAbsent&&node==null) {
			log.debug("==="+addr);
			node = new Node();
			nodes.put(addr, node);
			node.setAddress(addr.toString());
		}
	}
	private Receiver r = new Receiver() {
		public void viewAccepted(View view) {
			log.debug("===viewAccepted:"+view);
			if (view!=null) {
				List<Address> addrs = view.getMembers();
				
				if (addrs!=null) {
					for (Address address : addrs) {
						createNode(address, true);
					}
				}
			}
			log.debug("==="+nodes);
		}
		
		@Override
		public void suspect(Address arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void block() {
			
			
		}
		
		
		@Override
		public void receive(Message msg) {
			log.info("======"+msg);
		}
		@Override
		public void getState(OutputStream arg0) throws Exception {
			
			
		}
		@Override
		public void setState(InputStream arg0) throws Exception {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void unblock() {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	protected void startService() {
		try {
			channel = new JChannel("assets/udp.xml");
			channel.setReceiver(r);
			channel.connect("Microsun");
		} catch (Exception e) {
			log.error("Failed to create channel", e);
		}
		context.registerService(INodeManagementService.class, this);
	}

	@Override
	protected void stopService() {
		context.unregisterService(INodeManagementService.class, this);
		if (channel!=null) {
			channel.disconnect();
			channel = null;
		}
	}

	@Override
	public Map<String, Object> getChannelConfigInfo() {
		return null;
	}

	@Override
	public JChannel getChannel() {
		return channel;
	}

	

}
