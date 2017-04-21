package arrol.com.xiaomi.view.activity.alert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 2017/4/18.
 */

public class AlertDataBaseHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE_SQL=
            "create table alert(_id integer primary key autoincrement,"
                    +"time,remark,number)";
    public AlertDataBaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("-----------onUpdate Called------"+oldVersion+"--->"+newVersion);
    }
}
