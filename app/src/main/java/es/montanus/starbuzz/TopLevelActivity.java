package es.montanus.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TopLevelActivity extends Activity {

    private CursorManager cursorManager;
    private ListView favoritesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        setupOptionsListView();
        setupFavoritesListView();
    }

    private void setupOptionsListView() {
        ListView optionsList = (ListView) findViewById(R.id.list_options);
        optionsList.setOnItemClickListener(makeOnOptionClickListener());
    }

    private AdapterView.OnItemClickListener makeOnOptionClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(TopLevelActivity.this,
                            DrinkCategoryActivity.class));
                }
            }
        };
    }

    private void setupFavoritesListView() {
        favoritesList = findViewById(R.id.favorite_list);
        cursorManager = new CursorManager(this);
        final SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(this,
                        android.R.layout.simple_list_item_1,
                        getCursorForFavoriteDrinks(),
                        new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
        favoritesList.setAdapter(adapter);
        favoritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkCategoryActivity.DRINK_ID, id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        CursorAdapter cursorAdapter = (CursorAdapter) favoritesList.getAdapter();
        cursorAdapter.changeCursor(getCursorForFavoriteDrinks());
    }

    private Cursor getCursorForFavoriteDrinks() {
        return cursorManager.getCursor("FAVORITE = ?", new String[]{Integer.toString(1)});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursorManager.close();
    }
}
