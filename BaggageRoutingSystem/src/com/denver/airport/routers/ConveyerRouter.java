/**
 * 
 */
package com.denver.airport.routers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.denver.airport.route.handler.RouteMap;
import com.denver.airport.route.handler.RoutingSystem;

/**
 * @author hdubey created on Jan 26, 2018
 *
 */
public class ConveyerRouter extends RoutingSystem {
	private static Map<String, String> flightNodeMap;

	public ConveyerRouter(List<RouteMap> routes) {
		super(routes);
	}

	public ConveyerRouter(String routeInstructions) {
		super(routeInstructions);
	}

	/**
	 * @return the flightNodeMap
	 */
	public static Map<String, String> getFlightNodeMap() {
		return flightNodeMap;
	}

	/**
	 * @param flightNodeMap
	 *            the flightNodeMap to set
	 */
	public static void setFlightNodeMap(String flightIns) {

		String[] flightsIns = flightIns.split("\n");
		for (String fltIns : flightsIns)
			try {
				String[] flts = fltIns.trim().split("\\s+");
				if (flightNodeMap == null) {
					flightNodeMap = new HashMap<>();
					{
						if (flts.length >= 2) {
							flightNodeMap.put(flts[0], flts[1]);
						} else {
							throw new IllegalArgumentException(
									"Illegal arguments or inputs. Please refer to readme for the input data format.");
						}
					}

				} else {
					if (flts.length >= 2) {
						flightNodeMap.put(flts[0], flts[1]);
					} else {
						throw new IllegalArgumentException(
								"Illegal arguments or inputs. Please refer to readme for the input data format.");
					}

				}
			} catch (Exception e) {
				e.printStackTrace();

			}
	}

}
