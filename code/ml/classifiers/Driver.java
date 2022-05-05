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
//import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

public class Driver {
	private DataSource dataSource;

	public Driver() {
	}

	private Instances initDataSource(String path) {
		try {
			this.dataSource = new DataSource(path);

			// loads in arff data
			Instances data = dataSource.getDataSet();

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
		Driver driver = new Driver();
		String path1 = "/Users/aidangarton/Desktop/Java/final-project-starter/data/new.arff";
		String path2 = "/Users/aidangarton/Desktop/data/breast-cancer.arff";

		Instances data = driver.initDataSource(path1);

		// creates model and sets options
		AdaBoostM1 model = new AdaBoostM1();
//		String[] options = new String[1];
//		options[0] = "-W weka.classifiers.trees.DecisionStump";
		// set weak learner to be Decision stump

		String[] options = Utils.splitOptions("-W weka.classifiers.trees.RandomTree -I 3");
		model.setOptions(options);

		model.buildClassifier(data);
//		model.initializeClassifier(data);

		System.out.println(model);

		Evaluation eval = new Evaluation(data);

		eval.crossValidateModel(model, data, 10, new Random(1));

//		eval.evaluateModel(model, data1);

		System.out.println(eval.toMatrixString());
		System.out.println(eval.pctCorrect());
	}
}