package com.thenewcoder.linearcalc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import java.util.regex.Pattern;
import java.lang.Math;
public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String display="";
    private String currentOperator="";
    private String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView)findViewById(R.id.MyText);
        screen.setText(display);
    }
    //()=
    private void UpdateScreen(){
        screen.setText(display);
    }
    private void clear(){
        display="";
        currentOperator="";
        result="";
    }
    public void ClearButton(View v){
        clear();
        UpdateScreen();
    }

    public void ClickedNumber(View v){
        if (result!="") {
          clear();
            UpdateScreen();
        }
        Button b = (Button)v;
        display+=((Button) v).getText();
        UpdateScreen();
    }
    private boolean isOperator(char o){
        switch (o){
            case '+':
            case '-':
            case '*':
            case 'π':
            case '÷':return true;
            default:return false;
        }
    }

    private double operate(String a,String b,String o){
       switch (o){
           case "+": return Double.valueOf(a) + Double.valueOf(b);
           case "-": return Double.valueOf(a) - Double.valueOf(b);
           case "*": return Double.valueOf(a) * Double.valueOf(b);
           case "÷":try {
                   return Double.valueOf(a)/Double.valueOf(b);
           }catch (Exception e){
               Log.d("",e.getMessage());
           }
               default: return -1;
       }
    }
    private boolean GetResult(){
        if (currentOperator ==""){
            return false;
        }
        String[] operation = display.split(Pattern.quote(currentOperator));
        if (operation.length<2){
              return false;
        }
        result = String.valueOf(operate(operation[0],operation[1],currentOperator));
        return true;
    }

    public void ClickedOperator(View v){
         if (display == ""){
             return;
         }
        Button b=(Button)v;
        if (result!=""){
           String display1 =result;
            clear();
            display = display1;
        }
        if (currentOperator!=""){
            Log.d("",""+display.charAt(display.length()-1));
            if (isOperator(display.charAt(display.length()-1))){
                display = display.replace(display.charAt(display.length()-1),b.getText().charAt(0));
                UpdateScreen();
                return;
            }
            else {
                GetResult();
                display = result;
                result ="";
            }
            currentOperator = b.getText().toString();
        }
        display += b.getText();
        currentOperator = b.getText().toString();
        UpdateScreen();
    }
    public void ClickedEqual(View v){
        if (display=="") return;
        else if (!GetResult()) return;
        screen.setText(display + "\n" + String.valueOf(result));
    }
    public void ClickedPi(View v){
        Button b=(Button)v;
        if (display == ""){
            screen.setText(display+""+Math.PI);
        }
        else if (result!=""){
            String display1 =result;
            clear();
            display = display1;
        }
        else if (currentOperator!=""){
            Log.d("",""+display.charAt(display.length()-1));
            if (isOperator(display.charAt(display.length()-1))){
                display = display.replace(display.charAt(display.length()-1),b.getText().charAt(0));
                UpdateScreen();
                return;
            }
            else {
                GetResult();
                display = result;
                result ="";
            }
            currentOperator = b.getText().toString();
        }
        display += b.getText();
        currentOperator = b.getText().toString();
        UpdateScreen();
    }
    public void ClickedLog(View v){
        display = screen.getText().toString();
        double d = 0.0;
        try{
            d = Double.parseDouble(display);
            screen.setText(""+Math.log10(d));
            clear();

        }catch (Exception e){
            Log.d("",e.getMessage());
        }
    }
}
