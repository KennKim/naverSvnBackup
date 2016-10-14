package com.example.uiiwant.frag1;

import com.example.uiiwant.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;

public class HeaderViewEditText extends FrameLayout {
	
	public EditText editText;

	public HeaderViewEditText(Context context) {
		super(context);
		LayoutInflater.from(getContext()).inflate(R.layout.fr_2_header_edit, this);
		
		editText=(EditText)findViewById(R.id.editText1);
		
		
	}
	

}
