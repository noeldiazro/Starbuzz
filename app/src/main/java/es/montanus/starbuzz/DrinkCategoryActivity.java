package es.montanus.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends Activity {

    public static final String DRINK_ID = "id";

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
        return new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1,
                    Drink.drinks);
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
}
