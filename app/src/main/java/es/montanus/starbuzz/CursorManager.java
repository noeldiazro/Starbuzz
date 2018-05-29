package es.montanus.starbuzz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class CursorManager {
    private final Context context;
    private SQLiteDatabase db;
    private Cursor cursor;

    CursorManager(Context context) {
        this.context = context;
    }

    public Cursor getCursor() {
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(context);
        cursor = null;
        try {
            db = starbuzzDatabaseHelper.getReadableDatabase();
            cursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    null, null,
                    null, null, null);
        } catch (SQLiteException e) {
            Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }

    public void close() {
        if (cursor != null)
            cursor.close();
        if (db != null)
            db.close();
    }
}
