package com.example.calculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button num0,num1, num2, num3, num4, num5, num6, num7, num8, num9, add, sub, equal;
    TextView textview;
    int symbol_count = 0; //使用 +/- 符號次數
    Boolean reset = false; //判斷是否有計算過

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 顯示面板
        textview = findViewById(R.id.textView);
        //判斷符號 =
        equal = findViewById(R.id.equal);
        //加減
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        //數字鍵
        num0 = findViewById(R.id.num0);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);

        equal.setOnClickListener(numListener);
        add.setOnClickListener(numListener);
        sub.setOnClickListener(numListener);
        num0.setOnClickListener(numListener);
        num1.setOnClickListener(numListener);
        num2.setOnClickListener(numListener);
        num3.setOnClickListener(numListener);
        num4.setOnClickListener(numListener);
        num5.setOnClickListener(numListener);
        num6.setOnClickListener(numListener);
        num7.setOnClickListener(numListener);
        num8.setOnClickListener(numListener);
        num9.setOnClickListener(numListener);
    }

    private Button.OnClickListener numListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.num0:
                    buttoncheck("0");
                    break;
                case R.id.num1:
                    buttoncheck("1");
                    break;
                case R.id.num2:
                    buttoncheck("2");
                    break;
                case R.id.num3:
                    buttoncheck("3");

                    break;
                case R.id.num4:
                   buttoncheck("4");
                    break;
                case R.id.num5:
                    buttoncheck("5");
                    break;
                case R.id.num6:
                    buttoncheck("6");
                    break;
                case R.id.num7:
                    buttoncheck("7");
                    break;
                case R.id.num8:
                    buttoncheck("8");
                    break;
                case R.id.num9:
                    buttoncheck("9");
                    break;
                case R.id.add:
                    String text = textview.getText().toString();
                    //如果最後一個字元是 + / - ，則無法在做加法動作
                    if (text.substring(text.length() - 1).equals("-") || text.substring(text.length() - 1).equals("+"))
                        return;
                    else {
                        textview.append("+");
                        symbol_count ++;
                    }
                    break;
                case R.id.sub:
                    String text1 = textview.getText().toString();
                    //如果最後一個字元是 + / - ，則無法在做減法動作
                    if (text1.substring(text1.length() - 1).equals("-") || text1.substring(text1.length() - 1).equals("+"))
                        return;
                    else {
                        textview.append("-");
                        symbol_count ++;
                    }
                    break;
                case R.id.equal:
                    String result = textview.getText().toString();
                    //如果最後一個字元是 + / -  ，則無法計算
                    if (result.substring(result.length() - 1).equals("-") || result.substring(result.length() - 1).equals("+"))
                        return;
                    else
                        textview.setText(calculate(result));
                    break;

            }
        }
    };
    private void buttoncheck(String a) {
        String string = textview.getText().toString();

        if (string.equals("0")) textview.setText(a);

        else if (string.charAt(string.length() - 1) == '0' &&
                ((string.charAt(string.length() - 2) == '+' || string.charAt(string.length() - 2) == '-'))) {
            string = string.substring(0, string.length() - 1);
            string += a;
            textview.setText(string);
        }
        else if (reset == true  && symbol_count == 0) {
            textview.setText(a);
            reset = false;
        }
         else {
                textview.append(a);
         }
    }


    private String calculate(String result) {

         int sum = 0; //計算完的數值

        ArrayList<Integer> number = new ArrayList<Integer>(); //存放數值

        for(int i = result.length()-1;i>=0;i--){ //從後面找到前面
            if(result.charAt(i) == '+' || result.charAt(i) == '-'){ //如果碰到 + 或 -
                number.add(Integer.valueOf(result.substring(i))); // 把數值丟進number
                symbol_count --; //減掉一個符號數
                result = result.substring(0,i); //result 切割掉以處理的數字
            }
            if(symbol_count == 0){ //如果符號數 == 0
                number.add(Integer.valueOf(result)); //把最後一個數字丟進number
                break;
            }
        }
        //計算總和
        for (int i = 0 ; i<number.size();i++){
            sum += number.get(i);
        }

        reset = true;
        return Integer.toString(sum);
    }
}

