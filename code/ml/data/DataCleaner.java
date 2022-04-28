package ml.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ml.classifiers.DecisionTreeClassifier;

public class DataCleaner {
	public static void main(String[] args) {
		DataSet d1 = new DataSet("/Users/aidangarton/Desktop/Java/final-project-starter/data/new/student-mat.csv",
				DataSet.CSVFILE);

		try {
			String newFileName = "../final-project-starter/data/new/new.csv";
			File newFile = new File(newFileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));

			writer.write(
					"school,sex,age,address,famsize,Pstatus,Medu,Fedu,Mjob,Fjob,reason,guardian,traveltime,studytime,failures,schoolsup,famsup,paid,activities,nursery,higher,internet,romantic,famrel,freetime,goout,Dalc,Walc,health,absences,G1,G2,G3\n");

			for (Example e : d1.getData()) {
				for (int feature : e.getFeatureSet()) {
					String s = e.getFeature(feature) + ", ";
					if (feature == 0) {
						System.out.println(s);
					}
					writer.write(s);
				}
				writer.write(e.getLabel() + "\n");
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
