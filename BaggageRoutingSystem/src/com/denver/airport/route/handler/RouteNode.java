/**
 * 
 */
package com.denver.airport.route.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hdubey created on Jan 24, 2018
 *
 */
public class RouteNode implements Comparable<RouteNode> {
	private final String name;
	private int totalWeightFromSource = Integer.MAX_VALUE;
	private RouteNode traversedNode = null;
	private final Map<RouteNode, Integer> adjacentNodes = new HashMap<>();

	/**
	 * @param name
	 */
	public RouteNode(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the totalWeightFromSource
	 */
	public int getTotalWeightFromSource() {
		return totalWeightFromSource;
	}

	/**
	 * @param totalWeightFromSource
	 *            the totalWeightFromSource to set
	 */
	public void setTotalWeightFromSource(int totalWeightFromSource) {
		this.totalWeightFromSource = totalWeightFromSource;
	}

	/**
	 * @return the traversedNode
	 */
	public RouteNode getTraversedNode() {
		return traversedNode;
	}

	/**
	 * @param traversedNode
	 *            the traversedNode to set
	 */
	public void setTraversedNode(RouteNode traversedNode) {
		this.traversedNode = traversedNode;
	}

	/**
	 * @return the adjacentNode
	 */
	public Map<RouteNode, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	@Override
	public int compareTo(RouteNode otherNode) {
		if (totalWeightFromSource == otherNode.totalWeightFromSource)
			return name.compareTo(otherNode.name);

		return Integer.compare(totalWeightFromSource, otherNode.totalWeightFromSource);
	}

	public List<RouteNode> getShortestPathTo() {
		List<RouteNode> path = new ArrayList<RouteNode>();
		path.add(this);
		RouteNode currentNode = this.getTraversedNode();
		while (currentNode != null && !path.contains(currentNode)) {
			path.add(currentNode);
			currentNode = currentNode.getTraversedNode();
		}

		Collections.reverse(path);
		return path;
	}

	@Override
	public String toString() {
		return this.name + ":" + this.totalWeightFromSource;
	}

}
