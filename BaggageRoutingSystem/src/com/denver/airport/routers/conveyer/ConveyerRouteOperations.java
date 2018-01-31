package com.denver.airport.routers.conveyer;

import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.denver.airport.route.handler.RouteNode;
import com.denver.airport.route.handler.RoutingOperations;
import com.denver.airport.route.handler.RoutingSystem;
import com.denver.airport.routers.ConveyerRouter;

public class ConveyerRouteOperations implements RoutingOperations {

	private String instructionSet;

	/**
	 * @return the instructionSet
	 */
	public String getInstructionSet() {
		return instructionSet;
	}

	/**
	 * @param instructionSet
	 *            the instructionSet to set
	 */
	public void setInstructionSet(String instructionSet) {
		this.instructionSet = instructionSet;
	}

	@Override
	public String findOptimizedRoute(String instructionSet){
		String pathTrace = null;
		setInstructionSet(instructionSet);
		String convIns = extractInstruction(getInstructionSet(), "Conveyor System", "# Section:");
		String flightIns = extractInstruction(getInstructionSet(), "Departures", "# Section:");
		String bagsIns = extractInstruction(getInstructionSet(), "Bags", "# Section:");

		if (convIns != null) {
			new ConveyerRouter(new ConveyerRouter(convIns).getListRoutes());
		}
		// Map<String, RouteNode> conveyerRouteNodesMap =
		// RoutingSystem.routeNodeCollection;

		if (flightIns != null) {
			ConveyerRouter.setFlightNodeMap(flightIns);
		}

		if (bagsIns != null) {
			if (RoutingSystem.routeNodeCollection.isEmpty()) {
				throw new IllegalArgumentException(
						"Illegal arguments or inputs. Please refer to readme for the input data format.");
			}
			if (ConveyerRouter.getFlightNodeMap().isEmpty()) {
				throw new IllegalArgumentException(
						"Illegal arguments or inputs. Please refer to readme for the input data format.");
			}
			String[] bagIns = bagsIns.split("\n");

			StringBuilder sb = new StringBuilder(bagIns.length);			
			for (String bag : bagIns) {
				String[] details = bag.split("\\s+");
				String entryNode = details[1];
				String exitNode = (details[2].equals("ARRIVAL")) ? "BaggageClaim"
						: ConveyerRouter.getFlightNodeMap().get(details[2]);
				String detail=details[0] + ' ' + findShortestPath(entryNode, exitNode) + '\n';
				sb.append(detail) ;

			}
			pathTrace = sb.toString();
		}
		return pathTrace;
	}

	private String extractInstruction(String ins, String extractingIns, String terminatingIns) {
		if (ins.contains(extractingIns)) {
			int extInsLength = extractingIns.length() + 1;
			int terminatingInsIndex = (ins.indexOf(terminatingIns, (ins.indexOf(extractingIns) + extInsLength)) - 1) > 0
					? (ins.indexOf(terminatingIns, (ins.indexOf(extractingIns) + extInsLength)) - 1)
					: 0;
			if (terminatingInsIndex == 0)
				return ins.substring((ins.indexOf(extractingIns) + extInsLength));
			else
				return ins.substring((ins.indexOf(extractingIns) + extInsLength),
						(ins.indexOf(terminatingIns, (ins.indexOf(extractingIns) + extInsLength)) - 1));

		}

		else
			return null;
	}

	private String findShortestPath(String entryGate, String destGate) {
		if (!RoutingSystem.routeNodeCollection.containsKey(entryGate)) {
			throw new IllegalArgumentException(
					"Illegal arguments or inputs. Please refer to readme for the input data format.");
		}
		final RouteNode source = RoutingSystem.routeNodeCollection.get(entryGate);
		NavigableSet<RouteNode> queue = new TreeSet<>();

		// populate vertices to the queue
		for (RouteNode r : RoutingSystem.routeNodeCollection.values()) {
			r.setTraversedNode(r == source ? source : null);
			r.setTotalWeightFromSource(r == source ? 0 : Integer.MAX_VALUE);
			queue.add(r);
		}

		setTraverseNode(queue);
		//Map<String,RouteNode> routeCollection = RoutingSystem.routeNodeCollection;
		List<RouteNode> shortestpath = RoutingSystem.routeNodeCollection.get(destGate).getShortestPathTo();
		return generatePathLine(shortestpath);
	}

	private String generatePathLine(List<RouteNode> path) {
		StringBuffer line = new StringBuffer();

		for (RouteNode nodes : path) {
			line.append(nodes.getName()).append(" ");
		}
		line.append(": ").append(path.get(path.size() - 1).getTotalWeightFromSource());
		return line.toString();
	}

	// Implementation of dijkstra's algorithm using a binary heap.
	private void setTraverseNode(final NavigableSet<RouteNode> route) {
		RouteNode source, neighbour;
		while (!route.isEmpty()) {

			source = route.pollFirst(); // node with shortest distance
			if (source.getTotalWeightFromSource() == Integer.MAX_VALUE)
				break; // nodes are unreachable

			// setTotalweight of the nodes from source
			for (Map.Entry<RouteNode, Integer> a : source.getAdjacentNodes().entrySet()) {
				neighbour = a.getKey();

				final int alternateTime = source.getTotalWeightFromSource() + a.getValue();
				if (alternateTime < neighbour.getTotalWeightFromSource()) { // shorter path to neighbour found
					route.remove(neighbour);
					neighbour.setTotalWeightFromSource(alternateTime);
					neighbour.setTraversedNode(source);
					route.add(neighbour);
				}
			}
		}
	}

}
