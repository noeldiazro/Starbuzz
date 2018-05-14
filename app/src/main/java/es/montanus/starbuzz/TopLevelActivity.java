package es.montanus.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TopLevelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        initListView();
    }

    private void initListView() {
        ListView listView = (ListView)findViewById(R.id.list_options);
        listView.setOnItemClickListener(makeOnItemClickListener());
    }

    private AdapterView.OnItemClickListener makeOnItemClickListener() {
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
}
