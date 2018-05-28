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
        StarbuzzDatabaseCreator creator = new StarbuzzDatabaseCreator(db);
        creator.createTable();
        creator.populateTable();
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static class StarbuzzDatabaseCreator {

        private final SQLiteDatabase db;

        private StarbuzzDatabaseCreator(SQLiteDatabase db) {
            this.db = db;
        }

        private void createTable() {
            db.execSQL("CREATE TABLE DRINK ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER)");
        }


        private void populateTable() {
            insert(new Drink("Latte",
                    "A couple of espresso shots with steamed milk",
                    R.drawable.latte));

            insert(new Drink("Cappuccino",
                    "Espresso, hot milk, and a steamed milk foam",
                    R.drawable.cappuccino));

            insert(new Drink("Filter",
                    "Highest quality beans roasted and brewed fresh",
                    R.drawable.filter));
        }

        private void insert(Drink drink) {
            ContentValues latteValues = new ContentValues();
            latteValues.put("NAME", drink.getName());
            latteValues.put("DESCRIPTION", drink.getDescription());
            latteValues.put("IMAGE_RESOURCE_ID", drink.getImageResourceId());
            db.insert("DRINK", null, latteValues);
        }
    }
}
