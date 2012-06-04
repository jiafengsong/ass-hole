package com.tmall.asshole.zkclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import com.tmall.asshole.zkclient.data.NodeData;
import com.tmall.asshole.zkclient.data.PersistenceUtil;

/****
 * 
 * @author jiuxian.tjo
 * 
 */
public class ZKManager {
	private static transient Log log = LogFactory.getLog(ZKManager.class);
	private ZooKeeper zk;
	private List<ACL> acl = new ArrayList<ACL>();
	private ZKConfig zKConfig;

	public ZooKeeper getZk() {
		return zk;
	}
	private ZKClient zKClient;
	
	public ZKManager(ZKClient client,ZKConfig zKConfig) {
		this.zKClient = client;
		this.zKConfig = zKConfig;
	}
	

	public void close() throws InterruptedException {
		log.info("Zooker close");
		this.zk.close();
	}

	public ZKConfig getZkConfig() {
		return zKConfig;
	}

	public boolean checkZookeeperState() {
		return zk.getState().isAlive() && (zk.getState() == States.CONNECTED);
	}

	public void init() throws Exception {
		if(zKConfig==null){
			throw new NullPointerException("zkConfig ����Ϊ��");
		}
		
		zk = new ZooKeeper(zKConfig.getZkConnectString(),zKConfig.getZkSessionTimeout(), zKClient);
		
		//����������Ҫһ��ʱ�� ��ҪSleepһ��ʱ��
		Thread.sleep(2000);
		
		if(zKConfig.getUsePermissions()==true){
			  String authString = zKConfig.getUsername() + ":"
					+ zKConfig.getPassword();
			   zk.addAuthInfo("digest", authString.getBytes());
		       acl.add(new ACL(ZooDefs.Perms.ALL, new Id("digest",
				            DigestAuthenticationProvider.generateDigest(authString))));
		       acl.add(new ACL(ZooDefs.Perms.READ, Ids.ANYONE_ID_UNSAFE));
		}
		
		//���û��root ��Ҫ����rootPath rootPathΪ�־û���
		if(zk.exists(zKConfig.getRootPath(), false)==null){
			zk.create(zKConfig.getRootPath(), null, acl.size()==0?Ids.OPEN_ACL_UNSAFE:acl,CreateMode.PERSISTENT);
			return ;
		}
		
		if (zk.exists(zKConfig.getFullPath(), false) == null) {
			// ������watch�ı䶯����һ������ʱ��û�д���pathData
			ZKTools.createPath(zk, zKConfig.getFullPath(), PersistenceUtil.serializable(createPathData()),
					CreateMode.EPHEMERAL, acl.size()>0?acl:null);
		}
		
		
	}

	private NodeData createPathData() {
		NodeData nodeData=new NodeData(zKConfig.getLocalIPAddress());
		return nodeData;
	}
	

	public List<ACL> getAcl() {
		return acl;
	}

	public ZooKeeper getZooKeeper() throws Exception {
		return this.zk;
	}

	public ZKConfig getzKConfig() {
		return zKConfig;
	}

	public void setzKConfig(ZKConfig zKConfig) {
		this.zKConfig = zKConfig;
	}




}