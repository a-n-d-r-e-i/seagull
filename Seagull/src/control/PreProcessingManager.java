package control;

import input.InstancesGenerator;
import model.ClassificationModel;
import model.PreProcessingModel;
import preprocessing.BigBagReducer;
import preprocessing.MatrixGenerator;
import preprocessing.StemmingMaster;
import preprocessing.StopwordRemover;
import preprocessing.Weighter;


public class PreProcessingManager {

	private PreProcessingModel preProcessingModel;
	private MatrixGenerator matrixGenerator;
	
	public PreProcessingManager() {
		preProcessingModel = PreProcessingModel.getInstance();
		matrixGenerator = new MatrixGenerator();
	}

	public PreProcessingModel getPreProcessingModel() {
		return preProcessingModel;
	}

	public void setPreProcessingModel(PreProcessingModel preProcessingModel) {
		this.preProcessingModel = preProcessingModel;
	}
	
	public MatrixGenerator getMatrixGenerator() {
		return matrixGenerator;
	}

	public void setMatrixGenerator(MatrixGenerator matrixGenerator) {
		this.matrixGenerator = matrixGenerator;
	}

	public void startPreProcessing(){
		InstancesGenerator ig = new InstancesGenerator();
		BigBagReducer bbr = new BigBagReducer();
		
		// Removing repeated tokens
		preProcessingModel.setBigBag(bbr.reduceBag(preProcessingModel.getBigBag()));
		
		
		// STOPWORDS
				if(preProcessingModel.isStopwords()){
					StopwordRemover swr = new StopwordRemover();
					swr.removeAllStopwords(preProcessingModel.getBigBag());
				}
		
		
		// STEMMING
		if(preProcessingModel.isStemming()){
			StemmingMaster sm = new StemmingMaster();
			sm.StemmBag(preProcessingModel.getBigBag());
			preProcessingModel.setBigBag(bbr.reduceBag(preProcessingModel.getBigBag()));
			
		}
		
		
		
		// NORMALIZATION
		Weighter w;
		switch (preProcessingModel.getWeightingType()) {
			case TF:
				w = new Weighter();
				preProcessingModel.setBigBag(w.weightTF(preProcessingModel.getBigBag()));
				break;
			case TF_IDF:
				w = new Weighter();
				preProcessingModel.setBigBag(w.weightTFIDF(preProcessingModel.getBigBag()));
				break;
			/*case IG:
				break;
				*/
		}
		
		ClassificationModel cm = ClassificationModel.getInstance();
		
		cm.setInstances(ig.generate(preProcessingModel.getBigBag()));
		
		PreProcessingModel p = PreProcessingModel.getInstance();
		p.setBagOfWords(ig.getMatrix());
		
		//matrixGenerator.generateMatrix(preProcessingModel.getBigBag());
	}
	
}
