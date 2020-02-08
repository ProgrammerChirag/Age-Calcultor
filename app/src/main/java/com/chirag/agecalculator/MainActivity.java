package com.chirag.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public EditText date1,date2,month1,month2,yea1,year2;
    public Button btn;
    public TextView result;
    public int year,month,date;
    public boolean executable = false;
    int d1, m1, y1, d2, m2, y2;
    int mon[]={31,28,31,30,31,30,31,31,30,31,30,31};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date1=findViewById(R.id.date1);
        date2=findViewById(R.id.date2);
        month1=findViewById(R.id.month1);
        month2=findViewById(R.id.month2);
        yea1=findViewById(R.id.year1);
        year2=findViewById(R.id.year2);
        btn=findViewById(R.id.press);
        result=findViewById(R.id.result);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("Age Is:");
                if (date1.getText().toString().equals("") && month1.getText().toString().equals("") && yea1.getText().toString().equals("") && date2.getText().toString().equals("") && month2.getText().toString().equals("") && year2.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "please fill all first", Toast.LENGTH_LONG).show();
                } else {

                    try {
                        try {
                            d1 = Integer.parseInt(date1.getText().toString());
                            d2 = Integer.parseInt(date2.getText().toString());
                            m1 = Integer.parseInt(month1.getText().toString());
                            m2 = Integer.parseInt(month2.getText().toString());
                            y1 = Integer.parseInt(yea1.getText().toString());
                            y2 = Integer.parseInt(year2.getText().toString());
                            executable=true;
                        }catch (Exception e){

                            Toast.makeText(MainActivity.this,"please enter D.O.B and date at you want the result",Toast.LENGTH_LONG).show();
                            executable=false;
                        }
                        if(executable) {
                            if (validYear(y1, y2)) {
                                Toast.makeText(MainActivity.this,"please enter a valid year first",Toast.LENGTH_LONG).show();
                                executable=false;
                            } else {
                                if (y1 > y2) {
                                    Toast.makeText(MainActivity.this, " Birth Year should be less than or equal from current date", Toast.LENGTH_LONG).show();
                                } else {
                                    boolean valid_month = validMonth(m1, m2);
                                    if (valid_month == false)
                                        Toast.makeText(MainActivity.this, "month must be from 1 to 12 only", Toast.LENGTH_LONG).show();
                                    else {
                                        if (date1.getText().toString().equals("") || month1.getText().toString().equals("") || yea1.getText().toString().equals("") || date2.getText().toString().equals("") || month2.getText().toString().equals("") || year2.getText().toString().equals("")) {

                                        } else {
                                            boolean isvalid1 = valid(Integer.parseInt(date1.getText().toString()), Integer.parseInt(month1.getText().toString()), Integer.parseInt(yea1.getText().toString()));
                                            boolean isvalid2 = valid(Integer.parseInt(date2.getText().toString()), Integer.parseInt(month2.getText().toString()), Integer.parseInt(year2.getText().toString()));
                                            if (isvalid1 && isvalid2) {
                                                date = Integer.parseInt(date2.getText().toString()) - Integer.parseInt(date1.getText().toString());
                                                year = Integer.parseInt(year2.getText().toString()) - Integer.parseInt(yea1.getText().toString());
                                                if (d1 > d2) {
                                                    month = m2 - 1;
                                                    date = d2 + mon[m1 - 1];
                                                }
                                                if (m1 > m2) {
                                                    y2 = y2 - 1;
                                                    m2 = m2 + 12;
                                                }
                                                date = d2 - d1;
                                                month = m2 - m1;
                                                year = y2 - y1;
                                                result.setText("Age Is:");
                                                result.append(String.valueOf(date));
                                                result.append(" days ");
                                                result.append(String.valueOf(month));
                                                result.append(" month ");
                                                result.append(String.valueOf(year));
                                                result.append(" year ");
                                            } else
                                                Toast.makeText(MainActivity.this, "please enter valid date", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }
                        }
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this,"something went wrong",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public boolean valid(int a , int b , int c){
        if(c<1800||c>9999) return false;
        if(b<1||b>12) return false;
        if(a>31||a<1) return false;
        if(b==2){
            if(isLeap(c))
                return (a<=29);
                else return (a<=28);
        }
        if(b==4||b==6||b==9||b==11) return(a<=30);
        return true;
    }
    public boolean isLeap(int i){
    return (((i%4==0)&&(i%100!=0))||(i%400==0));
    }
    public boolean checkyear(int year){
        if(year>9999||year<1800) return true;
        else return false;
    }
    public boolean validMonth(int m1 , int m2){
        if(m1>13||m2>13||m1<1||m2<1) return  false;
        else return true;
    }
    public boolean validYear(int y1 ,int y2){
        if(y1>9999||y1<1800||y2>9999||y2<1800) return true;
        else return false;
    }
}