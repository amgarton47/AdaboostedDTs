package ml.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataCleaner {
	public static void main(String[] args) {
		DataSet d1 = new DataSet("/Users/aidangarton/Desktop/Java/final-project-starter" + "/data/new/student-mat.csv",
				DataSet.CSVFILE);

		try {
			String newFileName = "../final-project-starter/data/new/new-binary.csv";
			File newFile = new File(newFileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));

			writer.write("school,sex,age,address,famsize,Pstatus,Medu,Fedu,Mjob,Fjob,reason,"
					+ "guardian,traveltime,studytime,failures,schoolsup,famsup,paid,activities,"
					+ "nursery,higher,internet,romantic,famrel,freetime,goout,Dalc,Walc,health,"
					+ "absences,G1,G2,G3\n");

			for (Example e : d1.getData()) {
				for (int feature : e.getFeatureSet()) {
					writer.write(e.getFeature(feature) + ", ");
				}

				double lbl = e.getLabel();
				int grade;
				if (lbl < 2) {
					grade = 0;
				} else if (lbl < 4) {
					grade = 1;
				} else if (lbl < 6) {
					grade = 2;
				} else if (lbl < 8) {
					grade = 3;
				} else if (lbl < 10) {
					grade = 4;
				} else if (lbl < 12) {
					grade = 5;
				} else if (lbl < 14) {
					grade = 6;
				} else if (lbl < 16) {
					grade = 7;
				} else if (lbl < 18) {
					grade = 8;
				} else {
					grade = 9;
				}

				writer.write(grade + "\n");
			}

			writer.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
}
