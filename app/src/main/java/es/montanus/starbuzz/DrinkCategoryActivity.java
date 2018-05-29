package es.montanus.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DrinkCategoryActivity extends Activity {

    public static final String DRINK_ID = "id";
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        initListView();
    }

    private void initListView() {
        ListView listView = (ListView)findViewById(R.id.list_drinks);
        listView.setAdapter(makeListAdapter());
        listView.setOnItemClickListener(makeListener());
    }

    private ListAdapter makeListAdapter() {
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            db = starbuzzDatabaseHelper.getReadableDatabase();
            cursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    null, null,
                    null, null, null);

            return new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);
        }
        catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private AdapterView.OnItemClickListener makeListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DrinkCategoryActivity.this,
                        DrinkActivity.class);
                intent.putExtra(DRINK_ID, id);
                startActivity(intent);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
