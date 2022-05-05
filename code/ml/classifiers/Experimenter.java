package ml.classifiers;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.AdaBoostM1;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * A driver class to experiment with Weka models. Two different data sets are
 * analyzed and performance is compared between Naive Bayes models and
 * AdaBoostM1 models with varying weak learners.
 * 
 * @author Aidan Garton
 *
 */
public class Experimenter {
	public static void main(String[] args) {
		// student-mat dataset
		String path1 = "../final-project-starter/data/student-mat.arff";
		// one of Weka's provided data sets
		String path2 = "/Users/aidangarton/Desktop/data/breast-cancer.arff";

		try {
			// Initialize the training data
			DataSource dataSource = new DataSource(path2);
			Instances data = dataSource.getDataSet();

			if (data.classIndex() == -1) {
				data.setClassIndex(data.numAttributes() - 1);
			}

			System.out.println(data.classIndex());

			// creates model and sets options
			AdaBoostM1 model = new AdaBoostM1();

			// -W flag specifies type of weak learner, -I specifies number of iterations
			String[] options = Utils.splitOptions("-W weka.classifiers.trees.RandomTree -I 10");
			model.setOptions(options);

			model.buildClassifier(data);
			System.out.println(model);

			Evaluation eval = new Evaluation(data);
			eval.crossValidateModel(model, data, 10, new Random(1));

			System.out.println(eval.toMatrixString());
			System.out.println("Correctly classified: " + eval.pctCorrect());
			System.out.println("Incorrectly classified: " + eval.pctIncorrect());

			// NAIVE BAYES EXPERIMENT
//			NaiveBayes nb = new NaiveBayes();
//			nb.buildClassifier(data);
//
//			System.out.println(nb);
//
//			Evaluation eval1 = new Evaluation(data);
//			eval1.crossValidateModel(nb, data, 10, new Random(1));
//
//			System.out.println(eval1.toMatrixString());
//			System.out.println("Correctly classified: " + eval1.pctCorrect());
//			System.out.println("Incorrectly classified: " + eval1.pctIncorrect());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
