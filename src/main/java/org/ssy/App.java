package org.ssy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {

	public static List<String> packaging;
	public static List<String> production;
//	public static Map<Long, Integer> mainMap;
	public static Long arr[] = { 12345l, 12346l, 12347l, 12348l, 12349l };
	
	// for testing only
	public static List<String> testing_packaging;

	public App() {

	}

	public static void main(String[] args) {

		long stime = System.currentTimeMillis();

		production = DataLoader.loadXmlData("production.xml");
		packaging = DataLoader.loadXmlData("packaging.xml");

		System.out.println("Total number of events in Production : " + production.size());
		System.out.println("Total number of events in Packaging : " + packaging.size());
		System.out.println("\n\n");
			
		System.out.println("Total number of events in Production : " + production.size());
		System.out.println("Total number of events in Packaging : " + packaging.size());
		System.out.println("\n\n");

		process(production, packaging);

		long etime = System.currentTimeMillis();

		long overalltime = etime - stime;

//		DecimalFormat df = new DecimalFormat("#.####");

		System.out.print("Excuation time is : " + overalltime + " ms");

	}

	public static void process(List<String> p_production, List<String> p_packaging) {
		
		//for testing only
		testing_packaging = new ArrayList<String>();

		Map<String, Integer> packaging_event = new HashMap<String, Integer>();
		Set<String> production_event = new HashSet<>();

		for (String production_key : p_production) {
			int ntimes = 0;
			for (String packaging_value : new ArrayList<>(p_packaging)) {
//				System.out.println(p_key+" VS "+p_value);
				if (production_key.trim().equals(packaging_value.trim())) {
					ntimes++;
					
					testing_packaging.add(packaging_value);
					
					p_packaging.remove(packaging_value);
				}
//				System.out.println(p_key+" matched "+ntimes);
			}
//			System.out.println(p_key+" :: "+ntimes);
			if (ntimes == 0) {
				production_event.add(production_key);
			} else {
				packaging_event.put(production_key, ntimes);
			}
		}
		System.out.println("Number of Item Loss in Production : " + production_event.size());

		System.out.println("ItemLoss are : ");
		for (String val : production_event) {
			System.out.print(val + ", ");
		}
		System.out.println("\n\n");

		
		print(p_packaging, packaging_event);
		
		
		//for testing purpose only
		System.out.println("this is for you only");
		for(String test : testing_packaging) {
			System.out.println(test+", ");
		}
	}

	public static void print(List<String> p_packaging, Map<String, Integer> mainMap2) {
		System.out.println("Number of Counter Feeds in Packaging : " + p_packaging.size());

		System.out.println("Counter Feeds are : ");
		for (String val : p_packaging) {
			System.out.print(val + ", ");
		}
		System.out.println("\n\n");

		System.out.println("Matched Items");

		for (Entry<String, Integer> pair : mainMap2.entrySet()) {
			if(pair.getValue() > 6) {
			System.out.println(pair.getKey() + " : " + pair.getValue());
			}
		}
	}

	public static void print(Set<Long> p_production, long[] p_packaing) {
		System.out.println("Prodcution");
		for (long key : p_production) {
			System.out.print(key + ", ");
		}
		System.out.println("\n");

		System.out.println("Packaging");
		for (long val : p_packaing) {
			System.out.print(val + ", ");
		}
		System.out.println("\n\n");
	}
}
