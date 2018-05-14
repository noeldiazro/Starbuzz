package es.montanus.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DrinkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        long id = getIntent().getLongExtra(DrinkCategoryActivity.DRINK_ID, -1);
        TextView textId = (TextView)findViewById(R.id.text_id);
        textId.setText(String.valueOf(id));
    }
}
