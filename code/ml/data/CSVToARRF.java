package ml.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVToARRF {
	String csvPath, newPath;

	public CSVToARRF(String csvPath, String newPath) {
		this.csvPath = csvPath;
		this.newPath = newPath;

		try {
			BufferedReader br = new BufferedReader(new FileReader(csvPath));

			String l1 = br.readLine();
			String[] attributes = l1.split(";");

			System.out.println(attributes.length);

			File file = new File(newPath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(newPath));

			bw.write("% Portuguese Student Math Class");

			bw.newLine();
			bw.newLine();

			bw.write("@relation student-mat");

			bw.newLine();
			bw.newLine();

			bw.write("@attribute " + attributes[0] + " {\'GP\',\'MS\'}\n");
			bw.write("@attribute " + attributes[1] + " {\'F\',\'M\'}\n");
			bw.write("@attribute " + attributes[2] + " numeric\n");
			bw.write("@attribute " + attributes[3] + " {\'U\',\'R\'}\n");
			bw.write("@attribute " + attributes[4] + " {\'LE3\',\'GT3\'}\n");
			bw.write("@attribute " + attributes[5] + " {\'T\',\'A\'}\n");
			bw.write("@attribute " + attributes[6] + " numeric\n");
			bw.write("@attribute " + attributes[7] + " numeric\n");
			bw.write("@attribute " + attributes[8] + " {\'teacher\',\'health\',\'services\',\'at_home\',\'other\'}\n");
			bw.write("@attribute " + attributes[9] + " {\'teacher\',\'health\',\'services\',\'at_home\',\'other\'}\n");
			bw.write("@attribute " + attributes[10] + " {\'home\',\'reputation\',\'course\',\'other\'}\n");
			bw.write("@attribute " + attributes[11] + " {\'mother\',\'father\',\'other\'}\n");
			bw.write("@attribute " + attributes[12] + " numeric\n");
			bw.write("@attribute " + attributes[13] + " numeric\n");
			bw.write("@attribute " + attributes[14] + " numeric\n");

			for (int i = 15; i < 23; i++) {
				bw.write("@attribute " + attributes[i] + " {\'yes\',\'no\'}\n");
			}

			for (int i = 23; i < 29; i++) {
				bw.write("@attribute " + attributes[i] + " {\'1\' ,\'2\', \'3\', \'4\', \'5\'}\n");
			}

			bw.write("@attribute " + attributes[29] + " numeric\n");
			bw.write("@attribute " + attributes[30] + " numeric\n");
			bw.write("@attribute " + attributes[31] + " numeric\n");

//			bw.write("@attribute \'Class\' "
//					+ "{\'0-2\',\'2-4\',\'4-6\',\'6-8\',\'8-10\',\'10-12\',\'12-14\',\'14-16\',\'16-18\',\'18-20\'}");

			bw.write("@attribute \'Class\' " + "{\'0-5\',\'5-10\',\'10-15\',\'15-20\'}");
			bw.newLine();
			bw.newLine();

			bw.write("@data");

			bw.newLine();
			bw.newLine();

			String l = br.readLine();

			while (l != null) {
//				l = l.replaceAll(";", ",");
				l = l.replaceAll("\"", "\'");

				String[] vals = l.split(";");

				String s = "";

				for (int i = 0; i < vals.length - 1; i++) {
//					if (i == 2 || i == 6 || i == 7 || i == 12 || i == 13 || i == 14 || i == 31) {
//						s += vals[i] + ",";
//					}else {
//						s += vals[i] + ",";
//					}
					s += vals[i] + ",";
				}

				int lbl = Integer.parseInt(vals[32]);

				String b;

//				if (lbl < 2) {
//					b = "0-2";
//				} else if (lbl < 4) {
//					b = "2-4";
//				} else if (lbl < 6) {
//					b = "4-6";
//				} else if (lbl < 8) {
//					b = "6-8";
//				} else if (lbl < 10) {
//					b = "8-10";
//				} else if (lbl < 12) {
//					b = "10-12";
//				} else if (lbl < 14) {
//					b = "12-14";
//				} else if (lbl < 16) {
//					b = "14-16";
//				} else if (lbl < 18) {
//					b = "16-18";
//				} else {
//					b = "18-20";
//				}

				if (lbl < 5) {
					b = "0-5";
				} else if (lbl < 10) {
					b = "5-10";
				} else if (lbl < 15) {
					b = "10-15";
				} else {
					b = "15-20";
				}

				s += "\'" + b + "\'";

//				l = l.replaceAll("\"", "\'");

//				bw.write(l + "\n");
				bw.write(s);
				bw.newLine();

				l = br.readLine();
			}

			bw.write("%\n");
			bw.write("%\n");
			bw.write("%\n");

			// for(int i = 0; i < 10; i++) {
//				bw.write("@attribute " + attributes[i] + "{\'GP\' , \'MS\'}\n");
//			}

//			for (String a : attributes) {
//				bw.write("@attribute " + a + );
//			}

			bw.close();
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CSVToARRF cta = new CSVToARRF("/Users/aidangarton/Downloads/student/student-mat.csv",
				"/Users/aidangarton/Desktop/Java/final-project-starter/data/new.arff");
	}

}
