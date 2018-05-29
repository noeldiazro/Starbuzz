package es.montanus.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.Toast;

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

    public void updateFavorite(long id, boolean favorite) {
        new UpdateDrinkTask(id, favorite).execute();
    }


    private class UpdateDrinkTask extends AsyncTask<Object, Void, Boolean> {
        private final long id;
        private final boolean favorite;
        private ContentValues values;

        private UpdateDrinkTask(long id, boolean favorite) {
            this.id = id;
            this.favorite = favorite;
        }

        @Override
        protected void onPreExecute() {
            values = new ContentValues();
            values.put("FAVORITE", favorite);
        }

        @Override
        protected Boolean doInBackground(Object... objects) {
            Boolean result = true;
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(context);
            try (SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase()) {
                db.update("DRINK", values, "_id = ?", new String[]{Long.toString(id)});
            } catch (SQLiteException e) {
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result)
                Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
    }
}
