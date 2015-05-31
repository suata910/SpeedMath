package nz.ac.otago.telecom.speedmath;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GameScreenFragmentB extends Fragment{
	
	Button trueBtn, falseBtn;
	TextView mathEquation;
	String mathStatement = "";
	int total = 20;
	public static int wrongAnswerCount = 0;
	int rightAnswerCount = 0;
	PGCommunicatorA commA;
	PGCommunicatorB commB;
	MathLogic mathL;
	String s;
	MDatabaseAdapter vikahelper;
	String answer, correct;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.game_screen_fragment_b_layout, container, false);
		TextView mEquation = (TextView) view.findViewById(R.id.math_equation_txt);
		if((savedInstanceState!=null)){
			mathStatement = savedInstanceState.getString("math_statement");
			mEquation.setText(mathStatement);
			total = savedInstanceState.getInt("total_questions",20);
			rightAnswerCount = savedInstanceState.getInt("correct_ans");
		}
		return view;
	}
	
	
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		//System.out.println("onSaveInstanceState was called, mathStatement is " + mathStatement);
		//String s = commB.getMathStatement();
		outState.putInt("total_questions", total);
		outState.putInt("correct_ans", rightAnswerCount);
		outState.putString("math_statement", s);
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		System.out.println("onActivityCreated was called, mathStatement is " + mathStatement);
		super.onActivityCreated(savedInstanceState);
		vikahelper = new MDatabaseAdapter(getActivity());
		commA=(PGCommunicatorA) getActivity();
		commB=(PGCommunicatorB) getActivity();
		trueBtn = (Button) getActivity().findViewById(R.id.trueBtn);
		falseBtn = (Button) getActivity().findViewById(R.id.falseBtn);
		mathEquation = (TextView) getActivity().findViewById(R.id.math_equation_txt);
		mathL = new MathLogic();
		changeEquations();
		s = commB.getMathStatement();
		//System.out.println(mathStatement);
		commA.numCorrectAns(rightAnswerCount);
		trueBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				total--;
				answer = "true";
				if (total >= 0) {
					commA.clickCounter(total);
					if (mathL.correct) {
						rightAnswer();
						mathL = new MathLogic();
						changeEquations();
						addGameDetails();
					} else {
						wrongAnswer();
						mathL = new MathLogic();
						changeEquations();
						addGameDetails();
					}
				} else {
					commA.stopTimer();
				}
			}
		});
		falseBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				total--;
				answer = "false";
				if (total >= 0) {
					commA.clickCounter(total);
					if (!mathL.correct) {
						rightAnswer();
						mathL = new MathLogic();
						changeEquations();
						addGameDetails();
					} else {
						wrongAnswer();
						mathL = new MathLogic();
						changeEquations();
						addGameDetails();
					}
				} else {
					commA.stopTimer();
				}
			}
		});
	}
	
	public int correctAnsers(){
		return rightAnswerCount;
	}

	public void changeEquations(){
		mathStatement = mathL.printEquation();
		mathEquation.setText(mathStatement);
	}
	
	public void wrongAnswer(){
		wrongAnswerCount++;
		commA.placeNegativeFeedback();
		correct = "false";
	}
	
	public void rightAnswer(){
		rightAnswerCount++;
		commA.numCorrectAns(rightAnswerCount);
		commA.placePositiveFeedback();
		correct = "true";
	}
	
	public int remainingQuestions(){
		return total;
	}
	
	public String currentMathEquation(){
		return mathStatement;
	}

	public void addGameDetails(){
		String eq = mathEquation.getText().toString();
		String ans = answer;
		String corr = correct;
		long id = vikahelper.insertGameDetails(eq, ans, corr);
		if(id < 0) {
//			Message.message(getActivity(), "Unsuccessful");
//			Toast.makeText(getActivity(), "Unsuccessful", Toast.LENGTH_SHORT).show();
		}else{
//			Toast.makeText(getActivity(), "Successfully added row: " + id, Toast.LENGTH_SHORT).show();
		}
	}
	
}
