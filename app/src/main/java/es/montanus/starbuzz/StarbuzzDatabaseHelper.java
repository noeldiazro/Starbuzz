package es.montanus.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 1;

    public StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int fromVersion) {
        if (fromVersion < 1) {
            db.execSQL("CREATE TABLE DRINK ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");

            insert(db, new Drink("Latte",
                    "A couple of espresso shots with steamed milk",
                    R.drawable.latte));

            insert(db, new Drink("Cappuccino",
                    "Espresso, hot milk, and a steamed milk foam",
                    R.drawable.cappuccino));

            insert(db, new Drink("Filter",
                    "Highest quality beans roasted and brewed fresh",
                    R.drawable.filter));
        }

        if (fromVersion < 2) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }

    private void insert(SQLiteDatabase db, Drink drink) {
        ContentValues latteValues = new ContentValues();
        latteValues.put("NAME", drink.getName());
        latteValues.put("DESCRIPTION", drink.getDescription());
        latteValues.put("IMAGE_RESOURCE_ID", drink.getImageResourceId());
        db.insert("DRINK", null, latteValues);
    }
}
