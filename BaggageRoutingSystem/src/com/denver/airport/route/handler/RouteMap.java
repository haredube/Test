/**
 * 
 */
package com.denver.airport.route.handler;

/**
 * @author hdubey created on Jan 25, 2018
 *
 */
public class RouteMap {
	private final RouteNode source;
	private final RouteNode destination;
	private final int interWeight;

	public RouteMap(RouteNode source, RouteNode destination, int interWeight) {
		this.source = source;
		this.destination = destination;
		this.interWeight = interWeight;
	}

	public RouteMap(String source, String destination, int interWeight) {
		this.source = new RouteNode(source);
		this.destination = new RouteNode(destination);
		this.interWeight = interWeight;
	}

	/**
	 * @return the source
	 */
	public RouteNode getSource() {
		return source;
	}

	/**
	 * @return the destination
	 */
	public RouteNode getDestination() {
		return destination;
	}

	/**
	 * @return the interWeight
	 */
	public int getInterWeight() {
		return interWeight;
	}
}
