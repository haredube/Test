/**
 * 
 */
package com.denver.airport.routers;

import com.denver.airport.route.handler.RoutingObject;

/**
 * @author hdubey created on Jan 26, 2018
 *
 */
public class Bag extends RoutingObject {
	private final String Id;

	public Bag(String Id, String entryNode, String exitNode) {
		super(entryNode, exitNode);
		this.Id = Id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

}
