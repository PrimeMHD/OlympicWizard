package chizaitongji.example.com.chizaitongji.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import chizaitongji.example.com.chizaitongji.R;

public class SetCompActivity extends AppCompatActivity {


    private ArrayList<String> Nations_Arraylist;
    private String GameName;
    private int RankValid;
    private Spinner spinner_1;
    private Spinner spinner_2;
    private Spinner spinner_3;
    private Spinner spinner_4;
    private Spinner spinner_5;
    private Button button_confirm;
    private Button button_cancel;
    private TextView textView_fourth_title;
    private TextView textView_fifth_title;
    private int GamePos;
    private int GameGender;



    private static final String TAG = "SetCompActivity";
    private Map<Integer, String> WinnerNames;
    private String[] nations_list;


    private boolean valid1 = false;
    private boolean valid2 = false;
    private boolean valid3 = false;
    private boolean valid4 = false;
    private boolean valid5 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //nations=new ArrayList<Nation>();
        setContentView(R.layout.layout_activity_set_comp);
        spinner_1 = (Spinner) findViewById(R.id.spinner_select_firstNation);
        spinner_2 = (Spinner) findViewById(R.id.spinner_select_secondNation);
        spinner_3 = (Spinner) findViewById(R.id.spinner_select_thirdNation);
        spinner_4 = (Spinner) findViewById(R.id.spinner_select_fourthNation);
        spinner_5 = (Spinner) findViewById(R.id.spinner_select_fifthNation);
        button_confirm = (Button) findViewById(R.id.button_confirm_setComp);
        button_cancel = (Button) findViewById(R.id.button_cancel_setComp);
        textView_fourth_title=(TextView)findViewById(R.id.textView_fourth_title);
        textView_fifth_title=(TextView)findViewById(R.id.textView_fifth_title);




        WinnerNames = new HashMap<Integer, String>();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Nations_Arraylist = bundle.getStringArrayList("nations");
        Log.d(TAG, "Nations_Arraylist是：" + Nations_Arraylist);
        GameName = bundle.getString("GameName");
        RankValid = bundle.getInt("RankValid");
        GamePos=bundle.getInt("GamePos");
        GameGender=bundle.getInt("GameGender");


        Log.d(TAG, "GameName是：" + GameName);
        Log.d(TAG, "RankValid是：" + RankValid);


        nations_list = new String[Nations_Arraylist.size() + 1];
        nations_list[0] = "请选择";
        for (int i = 1; i < Nations_Arraylist.size() + 1; i++) {
            nations_list[i] = Nations_Arraylist.get(i - 1);
            Log.d(TAG, nations_list[i]);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, android.R.id.text1, nations_list);
        spinner_1.setAdapter(adapter);
        spinner_2.setAdapter(adapter);
        spinner_3.setAdapter(adapter);

        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    valid1 = false;
                } else {
                    WinnerNames.put(1, nations_list[position]);

                    valid1 = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    valid2 = false;
                } else {
                    WinnerNames.put(2, nations_list[position]);
                    valid2 = true;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    valid3 = false;
                } else {
                    WinnerNames.put(3, nations_list[position]);
                    valid3 = true;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (RankValid == 5) {
            spinner_4.setAdapter(adapter);
            spinner_5.setAdapter(adapter);

            spinner_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        valid4 = false;
                    } else {
                        WinnerNames.put(4, nations_list[position]);
                        valid4 = true;

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        valid5 = false;
                    } else {
                        WinnerNames.put(5, nations_list[position]);
                        valid5 = true;

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } else {
            spinner_4.setVisibility(View.INVISIBLE);
            spinner_5.setVisibility(View.INVISIBLE);
            textView_fourth_title.setVisibility(View.INVISIBLE);
            textView_fifth_title.setVisibility(View.INVISIBLE);

        }


        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetCompActivity.this, "取消统计成绩", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RankValid == 3) {
                    if (valid1 && valid2 && valid3) {
                        Log.d(TAG, "winners:" + WinnerNames.get(1) + "," + WinnerNames.get(2) + "," + WinnerNames.get(3));
                        Intent result=new Intent();
                        result.putExtra("first",WinnerNames.get(1));
                        result.putExtra("second",WinnerNames.get(2));
                        result.putExtra("third",WinnerNames.get(3));
                        result.putExtra("GameName",GameName);
                        result.putExtra("GamePos",GamePos);
                        result.putExtra("GameGender",GameGender);
                        result.putExtra("RankValid",RankValid);

                        setResult(Activity.RESULT_OK,result);
                        finish();

                    }else{
                        Toast.makeText(SetCompActivity.this, "未填写充分", Toast.LENGTH_LONG).show();

                    }
                } else if (RankValid == 5) {
                    if(valid1 && valid2 && valid3&&valid4&&valid5){
                        Intent result=new Intent();
                        result.putExtra("first",WinnerNames.get(1));
                        result.putExtra("second",WinnerNames.get(2));
                        result.putExtra("third",WinnerNames.get(3));
                        result.putExtra("fourth",WinnerNames.get(4));
                        result.putExtra("fifth",WinnerNames.get(5));
                        result.putExtra("GameName",GameName);
                        result.putExtra("GamePos",GamePos);
                        result.putExtra("GameGender",GameGender);
                        result.putExtra("RankValid",RankValid);

                        setResult(Activity.RESULT_OK,result);
                        finish();
                    }else{
                        Toast.makeText(SetCompActivity.this, "未填写充分", Toast.LENGTH_LONG).show();

                    }

                }
            }
        });


    }


}
