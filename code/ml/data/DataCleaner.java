package ml.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataCleaner {
	public static void main(String[] args) {
		DataSet d1 = new DataSet("/Users/aidangarton/Desktop/Java/final-project-starter/data/new/student-mat.csv",
				DataSet.CSVFILE);

		System.out.println(d1.getData().size());
//		for (Example e : d1.getData()) {
//			System.out.println(e.getFeatureSet().size());
//		}

		try {
			String newFileName = "../final-project-starter/data/new/new.csv";
			File newFile = new File(newFileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));

			writer.write("school, sex, age, address, famsize, Pstatus, Medu, Fedu, Mjob, Fjob, reason, guardian, "
					+ "traveltime, studytime, failures, schoolsup, fa1up, paid, activities, nursery, higher, internet, "
					+ "romantic, famrel, " + "freetime, goout, Dalc, Walc, " + "health, absences, G1, G2, G3\n");

			for (Example e : d1.getData()) {
				for (int feature : e.getFeatureSet()) {
					writer.append(e.getFeature(feature) + ", ");
				}
				writer.newLine();
			}

//			writer.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
