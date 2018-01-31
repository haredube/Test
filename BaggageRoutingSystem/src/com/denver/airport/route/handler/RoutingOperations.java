/**
 * 
 */
package com.denver.airport.route.handler;

/**
 * @author hdubey created on Jan 26, 2018
 *
 */
public interface RoutingOperations {

	// String extractInstruction(String ins, String extractingIns, String
	// terminatingIns);
	// String findShortestPath(String entryNode, String exitNode);

	String findOptimizedRoute(String instructionSet);
}
