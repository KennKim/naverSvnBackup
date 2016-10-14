package com.example.uiiwant.frag2.chat;

import com.example.uiiwant.R;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChatMain extends AppCompatActivity{
	
	int actionBarColor = 0x79cbf533;
	
	EditText keywordView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_main);
		
		
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setTitle("");
		getSupportActionBar().setIcon(android.R.drawable.ic_dialog_alert);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(actionBarColor));
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		
		TextView tt = new TextView(this);
		tt.setText("ActionBar Test");
		tt.setTextColor(Color.WHITE);
		tt.setTextSize(20);
		
		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(Gravity.LEFT);
		getSupportActionBar().setCustomView(tt, lp);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.chat_main_menu, menu);
		
		MenuItem item = menu.findItem(R.id.menu_2);
		View view = MenuItemCompat.getActionView(item);
		keywordView = (EditText)view.findViewById(R.id.keyword);
		Button btn = (Button)view.findViewById(R.id.btn_search);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(ChatMain.this, "keyword : " + keywordView.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		int id = item.getItemId();
		if (id == R.id.menu_1) {
			Toast.makeText(this, "Menu 1 select", Toast.LENGTH_SHORT).show();
			return true;
		} else if (id == R.id.menu_2) {
			Toast.makeText(this, "Menu 2 select", Toast.LENGTH_SHORT).show();
			return true;
		} else if (id == android.R.id.home) {
			Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
			return true;
		}
		
		
		return super.onOptionsItemSelected(item);
	}

}
