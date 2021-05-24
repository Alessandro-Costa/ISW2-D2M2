package entity;

import mainpackage.Controller;
import weka.core.Instances;

public class Walk {
	private int trainIndex;
	private Instances trainSet;
	private Instances testSet;
	private double percTrainingDataset;
	private double percDefectiveTrain;
	private double percDefectiveTest;
	
	public Walk(int trainIndex, Instances dataset){
		this.trainIndex = trainIndex;
		divideDataset(dataset);
		
		this.percDefectiveTrain = ((double)getDefectsNumber(this.trainSet))/((double)trainSet.numInstances());
		this.percDefectiveTest = ((double) getDefectsNumber(this.testSet))/((double) testSet.numInstances());
	}
	
	private void divideDataset(Instances dataset) {
		int endTrainIndex = 0;
		
		for(int i = 0; i < dataset.size(); i++) {
			if(dataset.get(i).value(0) > trainIndex){
				endTrainIndex = i;
				trainSet = new Instances(dataset, 0, endTrainIndex);
				break;
			}
		}
		int testSetElements = 0;
		for(int i = endTrainIndex; i < dataset.size(); i++) {
			if(dataset.get(i).value(0) > trainIndex +1) {
				break;
			}
			testSetElements ++;
		}
		testSet = new Instances(dataset, endTrainIndex, testSetElements);
		
		this.percTrainingDataset = (double) trainSet.size()/(double) dataset.size();
	}
	
	private int getDefectsNumber(Instances dataset) {
		
		int defects = 0;
		int defectTrueIndex = Controller.getDefectiveClassIndex(dataset);
		for(int i = 0; i!= dataset.size(); i++) {
			if(dataset.get(i).value(dataset.classIndex()) == defectTrueIndex) {
				defects++;
			}
		}
		
		
		
		return defects;
	}

		
	public int getTrainIndex() {
		return trainIndex;
	}
	
	public Instances getTrainSet() {
		return trainSet;
	}
	
	public Instances getTestSet() {
		return testSet;
	}
	
	public double getPercTrainingDataset() {
		return percTrainingDataset;
	}
	
	public double getPercDefectiveTrain() {
		return percDefectiveTrain;
	}
	
	public double getPercDefectiveTest() {
		return percDefectiveTest;
	}
}
