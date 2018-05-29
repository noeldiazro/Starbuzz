package es.montanus.starbuzz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DrinkRepository {
    private final Context context;

    public DrinkRepository(Context context) {
        this.context = context;
    }

    public Drink get(long id) {
        Drink drink = null;

        SQLiteOpenHelper startbuzzDatabaseHelper = new StarbuzzDatabaseHelper(context);
        SQLiteDatabase db = startbuzzDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query("DRINK",
                new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                "_id = ?",
                new String[]{Long.toString(id)},
                null, null, null);

        if (cursor.moveToFirst()) {
            drink = new Drink(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2));
            drink.setFavorite(cursor.getInt(3) == 1);
        }

        cursor.close();
        db.close();

        return drink;
    }
}
