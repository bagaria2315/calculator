package com.example.calculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class display extends Fragment {

    private TextView textViewExp;
    private TextView textViewAns;

    public display() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        textViewExp = view.findViewById(R.id.dispExp);
        textViewAns = view.findViewById(R.id.dispResult);
        return view;
    }

    public void clearText(){
        textViewAns.setText("");
        textViewExp.setText("");
    }

    public void appendText(String text){
        String currentText = textViewExp.getText().toString();
        textViewExp.setText(currentText + text);
    }

    public String getText() {
        return textViewExp.getText().toString();
    }

    public void setExpression(String expression){
        textViewExp.setText(expression);
    }

    public void setResult(String result){
        textViewAns.setText(result);
    }

    public void setText(String exp){
        textViewExp.setText(exp);
    }



}