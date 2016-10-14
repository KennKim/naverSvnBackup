package kr.pe.roter.sample.customlistview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CustomListViewSample extends Activity {

	private static final String TAG = "CustomListViewSample";

	private Cursor mCursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Log.i(TAG, "onCreate");

		// DB NAME : roter.db
		// TABLe NAME : fruit

		// Database create if not exists
		SQLiteDatabase db = openOrCreateDatabase("roter.db",
				Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS fruit ("
				+ "_id		INTEGER PRIMARY KEY," + "name		TEXT," + "num		INTEGER,"
				+ "note		TEXT" + ");");

		// BtnAdd
		Button btnAdd = (Button) findViewById(R.id.main_btn_add);
		btnAdd.setOnClickListener(onClickBtnAdd());

		db.close();

	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");

		// ListViewFruit
		SQLiteDatabase db = openOrCreateDatabase("roter.db",
				Context.MODE_PRIVATE, null); // DB Open
		ListView listView = (ListView) findViewById(R.id.main_listview_fruit); // ListView
																				// 열기
		mCursor = db.rawQuery("SELECT * FROM fruit", null); // 쿼리 날리고
		mCursor.moveToFirst(); // 커서 처음으로 보내고
		String[] from = new String[] { "name", "num" }; // 가져올 DB의 필드 이름
		int[] to = new int[] { R.id.lf_tv_name, R.id.lf_tv_num }; // 각각 대응되는
																	// xml의
																	// TextView의
																	// id
		final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				listView.getContext(), // ListView의 context
				R.layout.list_fruit, // ListView의 Custom layout
				mCursor, // Item으로 사용할 DB의 Cursor
				from, // DB 필드 이름
				to // DB필드에 대응되는 xml TextView의 id
		);
		listView.setAdapter(adapter); // 어댑터 등록
		db.close(); // DB를 닫음.
		// Cursor는 닫으면 안된다. Cursor 닫으면 리스트에 항목들 안뜬다. Cursor는

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				Cursor c = (Cursor) adapter.getItem(position);
				String note = c.getString(3); // note는 3번임.(4번째 필드)
				Toast.makeText(getApplicationContext(), note, Toast.LENGTH_LONG)
						.show();

			}
		});

	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop");

		if (mCursor != null)
			mCursor.close();
	}

	// Add Button event handler
	OnClickListener onClickBtnAdd() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						AddFruitActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		};
	}

}