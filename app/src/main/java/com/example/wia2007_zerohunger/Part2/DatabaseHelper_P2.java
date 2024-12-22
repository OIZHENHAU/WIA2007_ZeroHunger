package com.example.wia2007_zerohunger.Part2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper_P2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DonationInfo.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    private static final String TABLE_NAME = "donation_info";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ORGANIZATION_NAME = "organization_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_TAKEUP_ADDRESS = "takeup_address";
    private static final String COLUMN_REMARK = "remark";
    private static final String COLUMN_DONATION_TYPE = "donation_type";
    private static final String COLUMN_COMMUNITY_KITCHEN = "community_kitchen";
    private static final String COLUMN_AVAILABLE_PERIOD = "available_period";
    private static final String COLUMN_TRANSPORT_METHOD = "transport_method";

    public DatabaseHelper_P2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ORGANIZATION_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT, " +
                COLUMN_TAKEUP_ADDRESS + " TEXT, " +
                COLUMN_REMARK + " TEXT, " +
                COLUMN_DONATION_TYPE + " TEXT, " +
                COLUMN_COMMUNITY_KITCHEN + " TEXT, " +
                COLUMN_AVAILABLE_PERIOD + " TEXT, " +
                COLUMN_TRANSPORT_METHOD + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertData(String organizationName, String email, String phoneNumber, String takeupAddress,
                           String remark, String donationType, String communityKitchen,
                           String availablePeriod, String transportMethod) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORGANIZATION_NAME, organizationName);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(COLUMN_TAKEUP_ADDRESS, takeupAddress);
        values.put(COLUMN_REMARK, remark);
        values.put(COLUMN_DONATION_TYPE, donationType);
        values.put(COLUMN_COMMUNITY_KITCHEN, communityKitchen);
        values.put(COLUMN_AVAILABLE_PERIOD, availablePeriod);
        values.put(COLUMN_TRANSPORT_METHOD, transportMethod);
        return db.insert(TABLE_NAME, null, values);
    }
}
