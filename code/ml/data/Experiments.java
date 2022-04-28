package ml.data;

import ml.classifiers.DecisionTreeClassifier;

public class Experiments {
	public static void main(String[] args) {
		DataSet d1 = new DataSet("/Users/aidangarton/Desktop/Java/final-project-starter/data/new/student-mat.csv",
				DataSet.CSVFILE);
//		
//		System.out.println(d1.getFeatureMap());
//
//		System.out.println(d1.getData().size());
//		for (Example e : d1.getData()) {
//			System.out.println(e.getFeatureSet().size());
//		}

		CrossValidationSet cvs = new CrossValidationSet(d1, 10);

		double c = 0, t = 0;

		for (int i = 0; i < 1; i++) {
			DataSetSplit dss = cvs.getValidationSet(i);

			DecisionTreeClassifier dtc = new DecisionTreeClassifier();
			dtc.setDepthLimit(3);

			dtc.train(dss.getTrain());
			System.out.println(dtc);

			for (Example e : dss.getTrain().getData()) {
				if (dtc.classify(e) == e.getLabel()) {
					c++;
				}
				t++;
			}
		}

		System.out.println(c / t);
	}
}
