import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class ReadRoster {
	public static void main(String[] args) {
		Map<String,String> actives = new TreeMap<String,String>();
		try {
			Scanner in = new Scanner(new File(args[0]));
			int x = 93;
			while(x > 0) {
				String line = in.nextLine().trim();
				String[] line1 = line.split(",");
				actives.put(line1[0], line1[1] + " " + line1[2]);
				x -= 1;
			}
			in.close();
			
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
		/*for (String key: actives.keySet()) {
			System.out.println(key + " " + actives.get(key));
		}
	*/
		findAttendance(args[1],actives);
	}

	public static void findAttendance(String attSheet, Map<String,String> names) {
		Set<String> presentId = new HashSet<String>();
		Set<String> didNotReg = new HashSet<String>();
		try {
			Scanner attFile = new Scanner(new File(attSheet));
			while (attFile.hasNextLine()) {
				String[] line = attFile.nextLine().split(",");
				if (line[0].startsWith("2") && line[0].length() == 8) {
					presentId.add(line[0]);
				}else {
					didNotReg.add(line[0]);
				}
			}
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
		System.out.println("Absent from chapter or did not swipe cat card:");
		System.out.println();
		for (String id: names.keySet()) {
			if (!presentId.contains(id)) {
				System.out.println(names.get(id));
			}
		}
		System.out.println();
		System.out.println("Did not register correctly: ");
		for (String it: didNotReg) {
			System.out.println(it);
		}
	}

}
