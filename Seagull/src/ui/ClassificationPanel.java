package ui;

import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

public class ClassificationPanel extends MasterPanel{
	
	private JLabel lblClassifier;
	private JCheckBox checkBoxNaiveBayes;
	private JCheckBox checkBoxJ48;
	private JCheckBox checkBoxKnn;
	
	private JCheckBox checkBoxEuclideanDist;
	private JCheckBox checkBoxCosSimilarity;
	
	private JLabel lblEvaluationMethod;
	private JRadioButton radioBtnCrossValidation;
	private JRadioButton radioBtnPercentageSplit;
	private JRadioButton radioBtnLOOCV;
	private ButtonGroup btnGroupEM;
	private JFormattedTextField txtFolds;
	private JLabel lblFolds;
	private JFormattedTextField txtSplit;
	private JLabel lblSplit;
	
	private JLabel lblReport;
	private JScrollPane paneReport;
	private JTextArea txtAreaReport;
	
	private JButton btnSave;
	private JButton btnChart;
	
	public ClassificationPanel() {
		setLayout(null);
		
		btnBrowse = new JButton("Browse...");
		
		panelOptions = new JPanel();	
		
		lblClassifier = new JLabel("Classifier:");
		checkBoxNaiveBayes = new JCheckBox("Naïve Bayes");
		checkBoxJ48 = new JCheckBox("J48");
		checkBoxKnn = new JCheckBox("K-nn");
		
		checkBoxEuclideanDist = new JCheckBox("Euclidean Distance");
		checkBoxCosSimilarity = new JCheckBox("Cosine Similarity");
		
		lblEvaluationMethod = new JLabel("Evaluation Method:");
		radioBtnCrossValidation = new JRadioButton("Cross-validation");
		radioBtnPercentageSplit = new JRadioButton("Percentage Split");
		radioBtnLOOCV = new JRadioButton("LOOCV");
		btnGroupEM = new ButtonGroup();
		
		NumberFormat format = NumberFormat.getNumberInstance(); 
		NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(99);
	    formatter.setCommitsOnValidEdit(true);
		
		txtFolds = new JFormattedTextField(formatter);
		lblFolds = new JLabel("folds");
		txtSplit = new JFormattedTextField(formatter);
		lblSplit = new JLabel("% training set");
		
		btnProcess = new JButton("Process!");
		
		lblReport = new JLabel("Report:");
		txtAreaReport = new JTextArea();
		paneReport = new JScrollPane(txtAreaReport);
		
		progressBar = new JProgressBar();
		
		btnSave = new JButton("Save to file");
		btnChart = new JButton("Generate chart");
		
		createPanel();
	}
	
	public void createPanel() {
		btnBrowse.setBounds(10, 10, 100, 25);
		add(btnBrowse);
		
		panelOptions.setBounds(10, 45, 180, 300);
		panelOptions.setLayout(null);
		
		lblClassifier.setBounds(5, 5, 140, 20);
		checkBoxNaiveBayes.setBounds(10, 25, 140, 20);
		checkBoxJ48.setBounds(10, 50, 140, 20);
		checkBoxKnn.setBounds(10, 75, 140, 20);
		
		checkBoxKnn.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(checkBoxKnn.isSelected()) {
					checkBoxEuclideanDist.setEnabled(true);
					checkBoxCosSimilarity.setEnabled(true);
				} else {
					checkBoxEuclideanDist.setEnabled(false);
					checkBoxCosSimilarity.setEnabled(false);
				}
			}
		});
		
		checkBoxEuclideanDist.setBounds(25, 100, 160, 20);
		checkBoxCosSimilarity.setBounds(25, 125, 140, 20);
		checkBoxEuclideanDist.setEnabled(false);
		checkBoxCosSimilarity.setEnabled(false);
		
		lblEvaluationMethod.setBounds(5, 150, 140, 20);
		radioBtnCrossValidation.setBounds(10, 175, 140, 20);
		radioBtnCrossValidation.setSelected(true);
		txtFolds.setBounds(30, 200, 30, 20);
		txtFolds.setText("10");
		lblFolds.setBounds(60, 200, 100, 20);
		radioBtnPercentageSplit.setBounds(10, 225, 140, 20);
		txtSplit.setBounds(30, 250, 30, 20);
		txtSplit.setText("80");
		lblSplit.setBounds(60, 250, 100, 20);
		radioBtnLOOCV.setBounds(10, 275, 140, 20);
		btnGroupEM.add(radioBtnCrossValidation);
		btnGroupEM.add(radioBtnPercentageSplit);
		btnGroupEM.add(radioBtnLOOCV);
		
		panelOptions.add(lblClassifier);
		panelOptions.add(checkBoxNaiveBayes);
		panelOptions.add(checkBoxJ48);
		panelOptions.add(checkBoxKnn);
		
		panelOptions.add(checkBoxCosSimilarity);
		panelOptions.add(checkBoxEuclideanDist);
		
		panelOptions.add(lblEvaluationMethod);
		panelOptions.add(radioBtnCrossValidation);
		panelOptions.add(txtFolds);
		panelOptions.add(lblFolds);
		panelOptions.add(radioBtnPercentageSplit);
		panelOptions.add(txtSplit);
		panelOptions.add(lblSplit);
		panelOptions.add(radioBtnLOOCV);
		
		add(panelOptions);
		
		btnProcess.setBounds(10, 360, 100, 25);
		add(btnProcess);
		
		lblReport.setBounds(200, 10, 100, 25);
		add(lblReport);
		
		paneReport.setBounds(200, 40, 560, 370);
		txtAreaReport.setEditable(false);
		txtAreaReport.setFont(new Font("Courier New", Font.PLAIN, 12));
		txtAreaReport.setForeground(Color.black);
		add(paneReport);

		progressBar.setBounds(200, 415, 250, 25);
		add(progressBar);
		
		btnSave.setBounds(530, 415, 100, 25);
		add(btnSave);	
		
		btnChart.setBounds(640, 415, 120, 25);
		add(btnChart);		
	}

	public JButton getBtnBrowse() {
		return btnBrowse;
	}

	public void setBtnBrowse(JButton btnBrowse) {
		this.btnBrowse = btnBrowse;
	}

	public JPanel getPanelOptions() {
		return panelOptions;
	}

	public void setPanelOptions(JPanel panelOptions) {
		this.panelOptions = panelOptions;
	}
	
	public JCheckBox getCheckBoxNaiveBayes() {
		return checkBoxNaiveBayes;
	}

	public void setCheckBoxNaiveBayes(JCheckBox checkBoxNaiveBayes) {
		this.checkBoxNaiveBayes = checkBoxNaiveBayes;
	}

	public JCheckBox getCheckBoxJ48() {
		return checkBoxJ48;
	}

	public void setCheckBoxJ48(JCheckBox checkBoxJ48) {
		this.checkBoxJ48 = checkBoxJ48;
	}

	public JCheckBox getCheckBoxKnn() {
		return checkBoxKnn;
	}

	public void setCheckBoxKnn(JCheckBox checkBoxKnn) {
		this.checkBoxKnn = checkBoxKnn;
	}
	
	public JCheckBox getCheckBoxEuclideanDist() {
		return checkBoxEuclideanDist;
	}

	public void setCheckBoxEuclideanDist(JCheckBox checkBoxEuclideanDist) {
		this.checkBoxEuclideanDist = checkBoxEuclideanDist;
	}

	public JCheckBox getCheckBoxCosSimilarity() {
		return checkBoxCosSimilarity;
	}

	public void setCheckBoxCosSimilarity(JCheckBox checkBoxCosSimilarity) {
		this.checkBoxCosSimilarity = checkBoxCosSimilarity;
	}

	public JRadioButton getRadioBtnCrossValidation() {
		return radioBtnCrossValidation;
	}

	public void setRadioBtnCrossValidation(JRadioButton radioBtnCrossValidation) {
		this.radioBtnCrossValidation = radioBtnCrossValidation;
	}

	public JFormattedTextField getTxtFolds() {
		return txtFolds;
	}

	public void setTxtFolds(JFormattedTextField txtFolds) {
		this.txtFolds = txtFolds;
	}
	
	public JRadioButton getRadioBtnPercentageSplit() {
		return radioBtnPercentageSplit;
	}

	public void setRadioBtnPercentageSplit(JRadioButton radioBtnPercentageSplit) {
		this.radioBtnPercentageSplit = radioBtnPercentageSplit;
	}
	
	public JFormattedTextField getTxtSplit() {
		return txtSplit;
	}

	public void setTxtSplit(JFormattedTextField txtSplit) {
		this.txtSplit = txtSplit;
	}

	public JRadioButton getRadioBtnLOOCV() {
		return radioBtnLOOCV;
	}

	public void setRadioBtnLOOCV(JRadioButton radioBtnLOOCV) {
		this.radioBtnLOOCV = radioBtnLOOCV;
	}
	
	public JButton getBtnProcess() {
		return btnProcess;
	}

	public void setBtnProcess(JButton btnProcess) {
		this.btnProcess = btnProcess;
	}

	public JScrollPane getPaneReport() {
		return paneReport;
	}

	public void setPaneReport(JScrollPane paneReport) {
		this.paneReport = paneReport;
	}

	public JTextArea getTxtAreaReport() {
		return txtAreaReport;
	}

	public void setTxtAreaReport(JTextArea txtAreaReport) {
		this.txtAreaReport = txtAreaReport;
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnChart() {
		return btnChart;
	}

	public void setBtnChart(JButton btnChart) {
		this.btnChart = btnChart;
	}

}