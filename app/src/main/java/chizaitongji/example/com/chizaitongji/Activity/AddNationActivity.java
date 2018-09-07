package chizaitongji.example.com.chizaitongji.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import chizaitongji.example.com.chizaitongji.R;

public class AddNationActivity extends AppCompatActivity {

    private Button button_confirm;
    private Button button_cancel;
    private EditText edit_nationname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nation);
        button_cancel=(Button)findViewById(R.id.button_cancel_addnation);
        button_confirm=(Button)findViewById(R.id.button_confirm_addnation);
        edit_nationname=(EditText)findViewById(R.id.edit_nationname);


        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_nationname.getText().toString().trim().equals("")){
                    Toast.makeText(AddNationActivity.this,"输入不完整!",Toast.LENGTH_LONG).show();

                }else{
                    Intent result=new Intent();
                    result.putExtra("NationName",edit_nationname.getText().toString());
                    setResult(Activity.RESULT_OK,result);
                    finish();


                }
            }
        });


        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddNationActivity.this,"取消添加国家",Toast.LENGTH_LONG).show();
                finish();
            }
        });




    }
}
