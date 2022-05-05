package ml.classifiers;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.ConfusionMatrix;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.meta.AdaBoostM1;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;

public class AdaBoostWrapper {
	private DataSource dataSource;
	private Instances data;
	private AdaBoostM1 model;
	private int numIterations = 10;

	public static final String STUMP = "weka.classifiers.trees.DecisionStump";
	public static final String TREE = "weka.classifiers.trees.RandomTree";

	public AdaBoostWrapper(String path, String weakLearnerType) {
		initDataSource(path);
		model = new AdaBoostM1();

		try {
			String[] options;
			options = Utils.splitOptions("-W " + weakLearnerType + ", -I " + numIterations);
			this.model.setOptions(options);

			model.buildClassifier(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return model.toString();
	}

	public void setNumIterations(int numIterations) {
		if (numIterations < 0) {
			throw new IllegalArgumentException("numIterations must be greater than 0");
		}

		this.numIterations = numIterations;
	}

	private void printModelStats() {
		System.out.println();
	}

	private Instances initDataSource(String path) {
		try {
			// load in data and retrieve examples
			this.dataSource = new DataSource(path);
			this.data = dataSource.getDataSet();

			// set data label index to that of last attribute
			if (data.classIndex() == -1)
				data.setClassIndex(data.numAttributes() - 1);

			return data;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
	}
}