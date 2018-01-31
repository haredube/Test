/**
 * 
 */
package com.denver.airport.route.handler;

/**
 * @author hdubey created on Jan 26, 2018
 *
 */
public class RoutingObject {
	private final String entryNode;
	private final String exitNode;

	/**
	 * @param entryNode
	 * @param exitNode
	 */
	public RoutingObject(String entryNode, String exitNode) {
		super();
		this.entryNode = entryNode;
		this.exitNode = exitNode;
	}

	/**
	 * @return the entryNode
	 */
	public String getEntryNode() {
		return entryNode;
	}

	/**
	 * @return the exitNode
	 */
	public String getExitNode() {
		return exitNode;
	}

}
