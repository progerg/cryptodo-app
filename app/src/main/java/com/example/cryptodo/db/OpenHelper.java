package com.example.cryptodo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {
    // Данные базы данных и таблиц
    private static final String DATABASE_NAME = "cryptodo.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contract";

    private static final String TABLE_NAME_2 = "nft_contract";

    // Названия столбцов для обычных контрактов
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BLOCKCHAIN = "blockchain";
    private static final String COLUMN_TOTAL_SUPPLY = "total_supply";
    private static final String COLUMN_BURN = "burn";
    private static final String COLUMN_MINT = "mint";
    private static final String COLUMN_SAFEMOON = "safemoon";
    private static final String COLUMN_FEE_PERCENT = "fee_percent";
    private static final String COLUMN_FEE_ADDRESS = "fee_address";
    private static final String COLUMN_BURNING_PERCENT = "burning_percent";
    private static final String COLUMN_STAKING_PERCENT = "staking_percent";
    private static final String COLUMN_STANDARD = "standard";
    private static final String COLUMN_OWNER = "owner";
    private static final String COLUMN_SYMBOL = "symbol";
    private static final String COLUMN_TEST_MODE = "test_mode";
    private static final String COLUMN_DECIMALS = "decimals";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CHECK_CODE = "check_code";

    // Названия столбцов для нфт контрактов
    private static final String COLUMN_PER_TX = "per_tx";
    private static final String COLUMN_PER_WALLET = "per_wallet";
    private static final String COLUMN_START_PRICE = "start_price";
    private static final String COLUMN_FOUNDER = "founder";
    private static final String COLUMN_URI = "uri";
    private static final String COLUMN_INCREMENT_MAX_AMOUNT = "inc_max_amount";
    private static final String COLUMN_PRESALE = "presale";
    private static final String COLUMN_TIME_FOR_GROWN = "time_for_grown";

    OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BURN + " BOOLEAN DEFAULT 0," +
                COLUMN_MINT + " BOOLEAN DEFAULT 0," +
                COLUMN_SAFEMOON + " BOOLEAN DEFAULT 0," +
                COLUMN_TEST_MODE + " BOOLEAN DEFAULT 1," +
                COLUMN_BLOCKCHAIN + " TEXT DEFAULT \"bsc\"," +
                COLUMN_FEE_ADDRESS + " TEXT," +
                COLUMN_BURNING_PERCENT + "INTEGER," +
                COLUMN_FEE_PERCENT + "INTEGER," +
                COLUMN_STAKING_PERCENT + "INTEGER," +
                COLUMN_STANDARD + "TEXT," +
                COLUMN_OWNER + "TEXT," +
                COLUMN_SYMBOL + "TEXT," +
                COLUMN_TOTAL_SUPPLY + "INTEGER," +
                COLUMN_NAME + "TEXT," +
                COLUMN_DECIMALS + "INTEGER," +
                COLUMN_CHECK_CODE + "TEXT);";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_NAME_2 + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PER_TX + " INTEGER DEFAULT 0," +
                COLUMN_PER_WALLET + " INTEGER DEFAULT 0," +
                COLUMN_START_PRICE + " REAL DEFAULT 0," +
                COLUMN_TEST_MODE + " BOOLEAN DEFAULT 1," +
                COLUMN_BLOCKCHAIN + " TEXT DEFAULT \"bsc\"," +
                COLUMN_FOUNDER + " TEXT," +
                COLUMN_URI + " TEXT," +
                COLUMN_INCREMENT_MAX_AMOUNT+ " BOOLEAN," +
                COLUMN_PRESALE + " BOOLEAN," +
                COLUMN_TIME_FOR_GROWN + " TEXT," +
                COLUMN_STANDARD + "TEXT," +
                COLUMN_OWNER + "TEXT," +
                COLUMN_SYMBOL + "TEXT);";
        db.execSQL(query);
    }


    public void setValue(SQLiteDatabase db, String tableName, String column, int value) {
        String query = "UPDATE " + tableName + "SET " + column + "=" + String.valueOf(value) + ";";
    }

    public void setValue(SQLiteDatabase db, String tableName, String column, float value) {

    }

    public void setValue(SQLiteDatabase db, String tableName, String column, String value) {

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}