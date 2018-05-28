package es.montanus.starbuzz;

import android.app.Activity;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {

    private DrinkRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        repository = new DrinkRepository(this);
        initViews();
    }

    private void initViews() {
        long id = getIntent().getLongExtra(DrinkCategoryActivity.DRINK_ID, -1);
        Drink drink = null;
        try {
            drink = repository.get(id + 1);
        }
        catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }

        if (drink != null) {
            setName(drink.getName());
            setDescription(drink.getDescription());
            setImage(drink.getImageResourceId(), drink.getName());
        }
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
