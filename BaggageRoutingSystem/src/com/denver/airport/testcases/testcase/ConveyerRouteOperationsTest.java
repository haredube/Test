package com.denver.airport.testcases.testcase;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.denver.airport.routers.conveyer.ConveyerRouteOperations;


public class ConveyerRouteOperationsTest {

	@Test
	public void testFindOptimizedRoute() throws IOException {
		Path path= Paths.get("src/test/data/InputTestData.txt");
		//String stringFromFile = java.nio.file.Files.lines(path).collect(Collectors.joining());
		String stringFromFile = new String(Files.readAllBytes(path));
        System.out.println(stringFromFile);
        ConveyerRouteOperations cro = new ConveyerRouteOperations();

        String output = cro.findOptimizedRoute(stringFromFile);
        assertEquals("0001 Concourse_A_Ticketing A5 A1 : 11\n" +
                "0002 A5 A1 A2 A3 A4 : 9\n" +
                "0003 A2 A1 : 1\n" +
                "0004 A8 A9 A10 A5 : 6\n" +
                "0005 A7 A8 A9 A10 A5 BaggageClaim : 12\n",output);
	}

}
