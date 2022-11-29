package com.example.nomosoloapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    // Tables Name
    public static final String MUSICIAN_REG_TABLE = "MUSICIAN_REG";
    public static final String MUSICIAN_INFO_TABLE = "MUSICIAN_INFO";

    // Musician Registration Columns
    public static final String ID = "_id";
    public static final String FIRSTNAME = "fn";
    public static final String LASTNAME = "ln";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String SALT = "salt";
    public static final String PHONE = "phone";
    public static final String SECURITY_QUESTION = "security_question";
    public static final String SECURITY_ANSWER = "security_answer";
    public static final String CREATION_DATE = "creation_date";

    // Musician Info Columns
    public static final String INSTRUMENT = "instrument";
    public static final String BIO = "bio";
    public static final String PHOTO = "photo";
    public static final String SKILL_LEVEL = "skill_level";
    public static final String GENRE1 = "genre1";
    public static final String GENRE2 = "genre2";
    public static final String INSTRUMENT_DESIRED = "instrument_desired";
    public static final String SKILL_DESIRED = "skill_desired";
    public static final String GENRE_DESIRED = "genre_desired";

    private static final String CREATE_MUSICIAN_REG_TABLE = "create table " + MUSICIAN_REG_TABLE + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIRSTNAME + " TEXT NOT NULL, "
            + LASTNAME + " TEXT NOT NULL, " + EMAIL + " TEXT NOT NULL UNIQUE, " + PASSWORD + " TEXT NOT NULL, " + SALT + " TEXT NOT NULL, "
            + PHONE + " TEXT NOT NULL, " + SECURITY_QUESTION + " TEXT NOT NULL,"
            + SECURITY_ANSWER + " TEXT NOT NULL, " + CREATION_DATE + " TEXT NOT NULL);";

    private static final String CREATE_MUSICIAN_INFO_TABLE = "create table " + MUSICIAN_INFO_TABLE + "(" + ID
            + " INTEGER PRIMARY KEY, " + BIO + " TEXT NOT NULL, " + INSTRUMENT + " TEXT NOT NULL, " + PHOTO + " BLOB, "
            + SKILL_LEVEL + " INTEGER NOT NULL, " + GENRE1 + " TEXT NOT NULL, " + GENRE2 + " TEXT NOT NULL, "
            + INSTRUMENT_DESIRED + " TEXT NOT NULL, " + SKILL_DESIRED + " TEXT NOT NULL, " + GENRE_DESIRED + " TEXT NOT NULL);";

    // Database Information
    static final String DB_NAME = "NoMoSolo.DB";

    // database version
    static final int DB_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDB) {
        sqLiteDB.execSQL(CREATE_MUSICIAN_REG_TABLE);
        sqLiteDB.execSQL(CREATE_MUSICIAN_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDB, int oldVersion, int newVerion) {
        Log.d("version change", "oldVersion: " + oldVersion + ", newVersion: " + newVerion);
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + MUSICIAN_REG_TABLE);
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + MUSICIAN_INFO_TABLE);
        onCreate(sqLiteDB);
    }
}