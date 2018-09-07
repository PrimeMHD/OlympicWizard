package chizaitongji.example.com.chizaitongji.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

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

    private static final String TAG = "SetCompActivity";
    private ArrayList<String> WinnerNames;
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


        WinnerNames = new ArrayList<String>();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Nations_Arraylist = bundle.getStringArrayList("nations");
        Log.d(TAG, "Nations_Arraylist是：" + Nations_Arraylist);
        GameName = bundle.getString("GameName");
        RankValid = bundle.getInt("RankValid");

        Log.d(TAG, "GameName是：" + GameName);
        Log.d(TAG, "RankValid是：" + RankValid);


        String[] nations_list = new String[Nations_Arraylist.size() + 1];
        nations_list[0] = "请选择";
        for (int i = 1; i < Nations_Arraylist.size() + 1; i++) {
            nations_list[i] = Nations_Arraylist.get(i);
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } else {
            spinner_4.setVisibility(View.INVISIBLE);
            spinner_5.setVisibility(View.INVISIBLE);

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
                if(RankValid==3){

                }else if(RankValid==5){

                }
            }
        });


    }


}
