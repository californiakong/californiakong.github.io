package com.cs360.brycezimbelman.mochamoment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDBHandler extends SQLiteOpenHelper {
    // Initialize variables
    private static final int DB_VER = 1;
    private static final String DB_NAME = "orderDB.db";

    private static final String TABLE_ORDER = "orderDB";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ITEM = "item";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_TYPE = " type";
    private static final String COLUMN_USER = " user";
    private static final String COLUMN_QUANTITY = "quantity";

    ItemDBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, DB_VER);
    }

    // Initialize SQL Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_ITEM + " TEXT," + COLUMN_PRICE + " TEXT," + COLUMN_TYPE + " TEXT," + COLUMN_USER + " TEXT," + COLUMN_QUANTITY + " TEXT" + ")";
        db.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        onCreate(db);
    }

    // add espresso to order
    void addEspresso(Espresso espresso) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM, espresso.getEspresso());
        values.put(COLUMN_PRICE, espresso.getPrice());
        values.put(COLUMN_TYPE, espresso.getType());
        values.put(COLUMN_USER, espresso.getUser());
        values.put(COLUMN_QUANTITY, espresso.getQuantity());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_ORDER, null, values);
        db.close();
    }

    // add frost to order
    void addFrost(Frost frost) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM, frost.getFrost());
        values.put(COLUMN_PRICE, frost.getPrice());
        values.put(COLUMN_TYPE, frost.getType());
        values.put(COLUMN_USER, frost.getUser());
        values.put(COLUMN_QUANTITY, frost.getQuantity());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_ORDER, null, values);
        db.close();
    }

    // add mocha to order
    void addMocha(Mocha mocha) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM, mocha.getMocha());
        values.put(COLUMN_PRICE, mocha.getPrice());
        values.put(COLUMN_TYPE, mocha.getType());
        values.put(COLUMN_USER, mocha.getUser());
        values.put(COLUMN_QUANTITY, mocha.getQuantity());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_ORDER, null, values);
        db.close();
    }

    // Implements read feature for espresso
    String readEspresso(String itemName) {
        String query = "SELECT * FROM " + TABLE_ORDER + " WHERE " + COLUMN_ITEM + " = \"" + itemName + "\"";
        String espressoQuantity;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            espressoQuantity = cursor.getString(5);
            cursor.close();
        } else {
            espressoQuantity = null;
        }

        db.close();
        return espressoQuantity;
    }

    // Implements read feature for frost
    String readFrost(String itemName) {
        String query = "SELECT * FROM " + TABLE_ORDER + " WHERE " + COLUMN_ITEM + " = \"" + itemName + "\"";
        String frostQuantity;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            frostQuantity = cursor.getString(5);
            cursor.close();
        } else {
            frostQuantity = null;
        }

        db.close();
        return frostQuantity;
    }

    // Implements read feature for espresso for mocha
    String readMocha(String itemName) {
        String query = "SELECT * FROM " + TABLE_ORDER + " WHERE " + COLUMN_ITEM + " = \"" + itemName + "\"";
        String mochaQuantity;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            mochaQuantity = cursor.getString(5);
            cursor.close();
        } else {
            mochaQuantity = null;
        }

        db.close();
        return mochaQuantity;
    }

    // Implements delete feature for espresso
    boolean deleteEspresso(String itemName) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_ORDER + " WHERE " + COLUMN_ITEM + " = \"" + itemName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Espresso espresso = new Espresso();

        if (cursor.moveToFirst()) {
            espresso.setEspresso(cursor.getString(0));
            db.delete(TABLE_ORDER, COLUMN_ITEM + " = ?", new String[] {
                    "Espresso"
            });

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    // Implements delete feature for frost
    boolean deleteFrost(String itemName) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_ORDER + " WHERE " + COLUMN_ITEM + " = \"" + itemName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Frost frost = new Frost();

        if (cursor.moveToFirst()) {
            frost.setFrost(cursor.getString(0));
            db.delete(TABLE_ORDER, COLUMN_ITEM + " = ?", new String[] {
                   "Frost"
            });

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    // Implements delete feature for mocha
    boolean deleteMocha(String itemName) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_ORDER + " WHERE " + COLUMN_ITEM + " = \"" + itemName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Mocha mocha = new Mocha();

        if (cursor.moveToFirst()) {
            mocha.setMocha(cursor.getString(0));
            db.delete(TABLE_ORDER, COLUMN_ITEM + " = ?", new String[] {
                    "Mocha"
            });

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}