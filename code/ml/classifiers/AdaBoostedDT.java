package ml.classifiers;

import java.util.ArrayList;
import java.util.HashSet;

import ml.data.DataSet;
import ml.data.Example;

public class AdaBoostedDT implements Classifier {

	@Override
	public void train(DataSet data) {

		// 1. Assign equal weights to all the data points

		for (Example e : data.getData()) {
			e.setWeight(1 / data.getData().size());
		}

		for (int i = 0; i < 100; i++) {

		}

		// 2. Find the stump that does the best job classifying the new collection of
		// samples by finding their Gini Index and selecting the one with the lowest Gin
		// index

		// 3. Calculate the "Amount of Say "and "Total error" to update the previous
		// sample weights.

		// 4. Normalize the new sample weights.

	}

	@Override
	public double classify(Example example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double confidence(Example example) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int getFeatureByGini(ArrayList<Example> data, HashSet<Integer> features) {
		int retFeature = (int) features.toArray()[0];
		for (int feature : features) {
			double maxImpurity = 1;

			ArrayList<Example> left = new ArrayList<Example>();
			ArrayList<Example> right = new ArrayList<Example>();

			// split the data on feature
			for (Example ex : data) {
				if (ex.getFeature(feature) == 0.0) {
					left.add(ex);
				} else {
					right.add(ex);
				}
			}

			// count label0 for left subtree
			int count1left = 0;
			for (Example ex : left) {
				if (ex.getFeature(feature) == 0.0) {
					count1left++;
				}
			}

			// count label0 for left subtree
			int count1right = 0;
			for (Example ex : right) {
				if (ex.getFeature(feature) == 0.0) {
					count1right++;
				}
			}

			// get proportion of label in left/right subtrees
			double p1, p2;

			if (left.size() == 0) {
				p1 = 0;
			} else {
				p1 = count1left / left.size();
			}

			if (right.size() == 0) {
				p2 = 0;
			} else {
				p2 = count1right / right.size();
			}

			// calculate gini index for both subtrees
			double giniLeft = 1 - p1 * p1 - (1 - p1) * (1 - p1);
			double giniRight = 1 - p2 * p2 - (1 - p2) * (1 - p2);

			// weight scores by proportion of data in each tree
			giniLeft *= (left.size() / data.size());
			giniRight *= (right.size() / data.size());

			double gini = giniLeft + giniRight;

			// set retTeature to feature that yields lowest impurity index
			if (gini < maxImpurity) {
				retFeature = feature;
			}

		}
		return retFeature;
	}

	public static void main(String[] args) {
		DataSet d1 = new DataSet("/Users/aidangarton/Desktop/Java/final-project-starter/data/new/student-mat.csv",
				DataSet.CSVFILE);
		
		System.out.println(d1.getData().size());
		for(Example e : d1.getData()) {
			System.out.println(e);
		}
	}

}
