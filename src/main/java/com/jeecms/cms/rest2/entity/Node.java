package com.jeecms.cms.rest2.entity;
   
import java.util.List;

public class Node implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	public List getHanderUserId() {
		return handerUserId;
	}
	public void setHanderUserId(List handerUserId) {
		this.handerUserId = handerUserId;
	}
	private String nodeName;
	private String nodeLabel;
	private List handerUserId; // 当前环节处理人ID
	private String preNodeName;
	private String nextNodeName;
	//状态：0-未办 1-在办 2-已办 3、超期
     private String state;
	

	
	public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
	public String getPreNodeName() {
		return preNodeName;
	}
	public void setPreNodeName(String preNodeName) {
		this.preNodeName = preNodeName;
	}
	public String getNextNodeName() {
		return nextNodeName;
	}
	public void setNextNodeName(String nextNodeName) {
		this.nextNodeName = nextNodeName;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeLabel() {
		return nodeLabel;
	}
	public void setNodeLabel(String nodeLabel) {
		this.nodeLabel = nodeLabel;
	}
	
}