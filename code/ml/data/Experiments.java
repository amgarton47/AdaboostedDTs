package ml.data;

import ml.classifiers.DecisionTreeClassifier;

public class Experiments {
	public static void main(String[] args) {
		String rootDir = "/Users/aidangarton/Desktop/Java/final-project-starter/data/new/";
		DataSet d1 = new DataSet(rootDir + "new.csv", DataSet.CSVFILE);

		double count = 0, total = 0;
		for (Example e : d1.getData()) {
			if (e.getLabel() == 5) {
				count++;
			}
			total++;
//			System.out.println(e.getFeatureSet().size());
		}
		System.out.println(count / total);

		CrossValidationSet cvs = new CrossValidationSet(d1, 10);

		double c = 0, t = 0;

		for (int j = 0; j < 15; j++) {

			for (int i = 0; i < 10; i++) {
				DataSetSplit dss = cvs.getValidationSet(i);

				DecisionTreeClassifier dtc = new DecisionTreeClassifier();
				dtc.setDepthLimit(j);

				dtc.train(dss.getTrain());
//				System.out.println(dtc);

				for (Example e : dss.getTest().getData()) {
					if (dtc.classify(e) == e.getLabel()) {
						c++;
					}
					t++;
				}
			}
			System.out.println("(" + j + "," + 100 * c / t + ")");
		}
	}
}
