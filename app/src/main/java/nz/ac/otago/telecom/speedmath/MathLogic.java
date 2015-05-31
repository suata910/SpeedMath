package nz.ac.otago.telecom.speedmath;

import java.util.Random;

import android.view.inputmethod.CorrectionInfo;

public class MathLogic {
	public Random random = new Random();
	public int num1;
	public int num2;
	public int ans;
	public boolean correct;
	public int selectedAns;
	public int chosenAns;
	public String finalAns;
	public String operator;
	public String operator1="+";
	public String operator2="-";
	public String operator3="*";
	
	public MathLogic(){
		selectedAns = random.nextInt((3-0+1)+0);
		chosenAns = random.nextInt((1-0+1)+0);
		num1 = random.nextInt((10-1+1)+1);
		num2 = random.nextInt((10-1+1)+1);
	}
	
	public String printEquation(){	
//		selectedAns = random.nextInt((3-0+1)+0);
//		chosenAns = random.nextInt((1-0+1)+0);
//		num1 = random.nextInt((10-1+1)+1);
//		num2 = random.nextInt((10-1+1)+1);
		chooser();
		selectAns();
		finalAns = num1 + " " + operator + " " + num2 + " = " + ans;
		return finalAns;
	}
	
	
	public String chooser(){
		int chosenOperator = random.nextInt((3-1+1)+1);
		if(chosenOperator == 1){
			operator=operator1;
		}else if(chosenOperator == 2){
			operator=operator2;
		}else{
			operator = operator3;
		}
		return operator;
	}
	
	public void setCorrectAns(){
		if(operator.equals(operator1)){
			ans = num1 + num2;
		}else if(operator.equals(operator2)){
			ans = num1 - num2;
		}else{
			ans = num1 * num2;
		}
	}
	public void setWrongAns(){
		if(operator.equals(operator1)){
			ans = num1 + num2;
		}else if(operator.equals(operator2)){
			ans = num1 - num2;
		}else{
			ans = num1 * num2;
		}
		if(selectedAns == 0){
			ans += 1;
		}else if(selectedAns == 1){
			ans -= 1;
		}else if(selectedAns == 2){
			ans += 2;
		}else{
			ans -= 2;
		}
	}
	public void selectAns(){
		if (chosenAns == 1) {
			correct = true;
			setCorrectAns();
		} else {
			correct = false;
			setWrongAns();
		}
	}
}
