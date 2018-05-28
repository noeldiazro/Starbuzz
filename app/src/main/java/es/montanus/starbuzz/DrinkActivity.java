package es.montanus.starbuzz;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        initViews();
    }

    private void initViews() {
        long id = getIntent().getLongExtra(DrinkCategoryActivity.DRINK_ID, -1);

        SQLiteOpenHelper startbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = startbuzzDatabaseHelper.getReadableDatabase();
        }
        catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }

        Drink drink = Drink.drinks[(int) id];
        setName(drink.getName());
        setDescription(drink.getDescription());
        setImage(drink.getImageResourceId(), drink.getName());
    }

    private void setName(String name) {
        TextView nameView = findViewById(R.id.name);
        nameView.setText(name);
    }

    private void setDescription(String description) {
        TextView descriptionView = findViewById(R.id.description);
        descriptionView.setText(description);
    }

    private void setImage(int imageResourceId, String contentDescription) {
        ImageView photo = findViewById(R.id.photo);
        photo.setImageResource(imageResourceId);
        photo.setContentDescription(contentDescription);
    }

}
