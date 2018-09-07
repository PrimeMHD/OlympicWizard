package chizaitongji.example.com.chizaitongji.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import chizaitongji.example.com.chizaitongji.Bean.SportsEvent;
import chizaitongji.example.com.chizaitongji.R;

public class AddGameActivity extends AppCompatActivity {

    private Button button_confirm;
    private Button button_cancel;
    private EditText edit_gamename;
    private Spinner spinner_select_gamegender;
    private Spinner spinner_select_rankvalid;
    private SportsEvent sportsEventToReturn;
    private boolean RankValid_dataisvalid=false;
    private boolean Gender_dataisvalid=false;
private static final String TAG="AddGameActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sportsEventToReturn=new SportsEvent();
        setContentView(R.layout.activity_add_game);
        button_confirm =(Button)findViewById(R.id.button_confirm_addgame);
        button_cancel=(Button)findViewById(R.id.button_cancel_addgame);
        edit_gamename=(EditText)findViewById(R.id.edit_gamename);
        spinner_select_gamegender=(Spinner)findViewById(R.id.spinner_select_gamegender);
        spinner_select_rankvalid=(Spinner)findViewById(R.id.spinner_select_rankvalid);


        spinner_select_rankvalid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] rankvalids=getResources().getStringArray(R.array.RankValid);
                if(rankvalids[position].equals("前五名计分"))
                    sportsEventToReturn.setRankValid(SportsEvent.RankValid.FIVE_VALID);
                else if(rankvalids[position].equals("前三名计分")){
                    sportsEventToReturn.setRankValid(SportsEvent.RankValid.THREE_VALID);
                }
                RankValid_dataisvalid=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                RankValid_dataisvalid=false;
            }
        });


        spinner_select_gamegender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] genders=getResources().getStringArray(R.array.GameGenders);
                if(genders[position].equals("男子项目"))
                    sportsEventToReturn.setSportsGender(SportsEvent.SportsGender.MALE);
                else if(genders[position].equals("女子项目")){
                    sportsEventToReturn.setSportsGender(SportsEvent.SportsGender.FAMALE);

                }
                Gender_dataisvalid=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Gender_dataisvalid=false;
            }
        });







        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_gamename.getText().toString().trim().equals("")||!RankValid_dataisvalid||!Gender_dataisvalid){
                    Toast.makeText(AddGameActivity.this,"输入不完整!",Toast.LENGTH_LONG).show();
                    Log.d(TAG,"输入不完整");
                }
                else{
                    Intent result=new Intent();
                    result.putExtra("GameName",edit_gamename.getText().toString());


                    if(sportsEventToReturn.getSportsGender()==SportsEvent.SportsGender.FAMALE){
                        result.putExtra("GameGender","FEMALE");
                        Log.d(TAG,"临时标记1"+"FEMALE");
                    }else {
                        result.putExtra("GameGender","MALE");

                    }

                    //result.putExtra("GameGender",sportsEventToReturn.getSportsGender().toString());
                    if(sportsEventToReturn.getRankValid()==SportsEvent.RankValid.FIVE_VALID){
                        result.putExtra("RankValid","FIVE_VALID");
                    }else{
                        result.putExtra("RankValid","THREE_VALID");

                    }


                    //result.putExtra("RankValid",sportsEventToReturn.getRankValid());
                    setResult(Activity.RESULT_OK,result);
                    finish();
                }






            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddGameActivity.this,"取消添加项目",Toast.LENGTH_LONG).show();
                finish();
            }
        });



    }
}
