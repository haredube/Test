/**
 * 
 */
package com.denver.airport.route.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hdubey created on Jan 23, 2018
 *
 */
public abstract class RoutingSystem {

	public static Map<String, RouteNode> routeNodeCollection;
	private static List<RouteMap> listRoutes;

	public RoutingSystem(List<RouteMap> routes) {
		if (routeNodeCollection == null) {
			routeNodeCollection = new HashMap<>(routes.size());
		}
		// Populated all the routeNodes from the routeMaps
		for (RouteMap e : routes) {
			if (!routeNodeCollection.containsKey(e.getSource().getName()))
				routeNodeCollection.put(e.getSource().getName(), new RouteNode(e.getSource().getName()));
			if (!routeNodeCollection.containsKey(e.getDestination().getName()))
				routeNodeCollection.put(e.getDestination().getName(), new RouteNode(e.getDestination().getName()));
		}

		// Set all the adjacentNodes from RouteMap
		for (RouteMap e : routes) {
			routeNodeCollection.get(e.getSource().getName()).getAdjacentNodes()
					.put(routeNodeCollection.get(e.getDestination().getName()), e.getInterWeight());
		}

	}

	public RoutingSystem(String routeInstructions) {
		if (listRoutes == null) {
			setListRoutes(new ArrayList<>());
		}
		String[] eachLineIns = routeInstructions.split("\n");
		for (String routeIns : eachLineIns)
			try {
				String[] parts = routeIns.trim().split("\\s+");
				if (parts.length >= 3) {
					RouteMap routeMaps = new RouteMap(parts[0], parts[1], Integer.valueOf(parts[2]));
					getListRoutes().add(routeMaps);
					RouteMap rrouteMaps = new RouteMap(parts[1], parts[0], Integer.valueOf(parts[2])); // To accommodate
																										// reverse
																										// traversal of
																										// routeNodes.
					getListRoutes().add(rrouteMaps);
				} else {
					throw new IllegalArgumentException(
							"Illegal arguments or inputs. Please refer to readme for the input data format.");
				}

			} catch (Exception e) {
				e.printStackTrace();

			}

	}

	/**
	 * @return the listRoutes
	 */
	public List<RouteMap> getListRoutes() {
		return listRoutes;
	}

	/**
	 * @param listRoutes
	 *            the listRoutes to set
	 */
	public static void setListRoutes(List<RouteMap> listRoutes) {
		RoutingSystem.listRoutes = listRoutes;
	}

	public Map<String, RouteNode> getRouteNodeCollection() {
		return routeNodeCollection;
	}

	public static void setRouteNodeCollection(Map<String, RouteNode> routeNodeCollection) {
		RoutingSystem.routeNodeCollection = routeNodeCollection;
	}

}
