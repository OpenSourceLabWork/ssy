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

	public App() {

//		Collections.addAll(production, arr);
	}

	public static void main(String[] args) {
//		production = new HashSet<>(Arrays.asList(arr));
//		packaging = new long[]{12354,12346,12345,12345,12346,12346,12349,12349,12349};
//		System.out.println("Orignal data");

		long stime = System.currentTimeMillis();

		production = DataLoader.loadXmlData("production.xml");
		packaging = DataLoader.loadXmlData("packaging.xml");

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

		Map<String, Integer> mainMap = new HashMap<String, Integer>();
		Set<String> itemloss = new HashSet<>();

		for (String p_key : p_production) {
			int ntimes = 0;
			for (String p_value : new ArrayList<>(p_packaging)) {
//				System.out.println(p_key+" VS "+p_value);
				if (p_key.trim().equals(p_value.trim())) {
					ntimes++;
					p_packaging.remove(p_value);
				}
//				System.out.println(p_key+" matched "+ntimes);
			}
//			System.out.println(p_key+" :: "+ntimes);
			if (ntimes == 0) {
				itemloss.add(p_key);
			} else {
				mainMap.put(p_key, ntimes);
			}
		}
		System.out.println("Number of Item Loss in Production : " + itemloss.size());

		System.out.println("ItemLoss are : ");
		for (String val : itemloss) {
			System.out.print(val + ", ");
		}
		System.out.println("\n\n");

		
		print(p_packaging, mainMap);
	}

	public static void print(List<String> p_packaging, Map<String, Integer> mainMap2) {
		System.out.println("Number of Counter Feeds in Packaging : " + p_packaging.size());

		System.out.println("Counter Feeds in Packaging");
		for (String val : p_packaging) {
			System.out.print(val + ", ");
		}
		System.out.println("\n\n");

		System.out.println("Matched Items");

		for (Entry<String, Integer> pair : mainMap2.entrySet()) {
			System.out.println(pair.getKey() + " : " + pair.getValue());
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
