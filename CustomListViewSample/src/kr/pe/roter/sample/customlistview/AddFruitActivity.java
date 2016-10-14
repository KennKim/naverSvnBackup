package kr.pe.roter.sample.customlistview;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddFruitActivity extends Activity{

	
	private EditText etName;
	private EditText etNum;
	private EditText etNote;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.add_fruit_activity);
		
		
		
		//EditText 연결해줌
		etName = (EditText)findViewById(R.id.afa_et_name);
		etNum = (EditText)findViewById(R.id.afa_et_num);
		etNote = (EditText)findViewById(R.id.afa_et_note);
		
	
		Button btnSubmit = (Button)findViewById(R.id.afa_btn_submit);
		btnSubmit.setOnClickListener(onClickBtnSubmit());
		
	}
	
	//Add Button Event Handler
	OnClickListener onClickBtnSubmit() {
		return new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//DB에 씀
				SQLiteDatabase db = openOrCreateDatabase("roter.db", Context.MODE_PRIVATE, null);
				ContentValues cv = new ContentValues();
				cv.put("name", etName.getText().toString());
				cv.put("num", etNum.getText().toString());
				cv.put("note", etNote.getText().toString());
				db.insert("fruit", null, cv);
				db.close();
				
				//액티비티 종료
				finish();				
			}
		};
	}
	
	
}
