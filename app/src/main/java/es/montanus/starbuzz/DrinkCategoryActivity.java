package es.montanus.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DrinkCategoryActivity extends Activity {

    public static final String DRINK_ID = "id";
    private CursorManager cursorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        initListView();
    }

    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.list_drinks);
        listView.setAdapter(makeListAdapter());
        listView.setOnItemClickListener(makeListener());
    }

    private ListAdapter makeListAdapter() {
        cursorManager = new CursorManager(this);
        return new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                cursorManager.getCursor(),
                new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
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
        cursorManager.close();
    }

}
