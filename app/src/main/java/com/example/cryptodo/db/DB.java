package com.example.cryptodo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;


class CurrentState {
    public String blockchain;
    public String type;

    CurrentState(String blockchain, String type) {
        this.blockchain = blockchain;
        this.type = type;
    }
}

public class DB {

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
    // private static final String COLUMN_FEE_PERCENT = "fee_percent";
    // private static final String COLUMN_FEE_ADDRESS = "fee_address";
    // private static final String COLUMN_BURNING_PERCENT = "burning_percent";
    // private static final String COLUMN_STAKING_PERCENT = "staking_percent";
    private static final String COLUMN_STANDARD = "standard";
    private static final String COLUMN_OWNER = "owner";
    private static final String COLUMN_SYMBOL = "symbol";
    private static final String COLUMN_TEST_MODE = "test_mode";
    private static final String COLUMN_DECIMALS = "decimals";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CHECK_CODE = "check_code";
    private static final String COLUMN_STATUS = "status";

    // Названия столбцов для нфт контрактов
    private static final String COLUMN_PER_TX = "per_tx";
    private static final String COLUMN_PER_WALLET = "per_wallet";
    private static final String COLUMN_START_PRICE = "start_price";
    private static final String COLUMN_FOUNDER = "founder";
    private static final String COLUMN_URI = "uri";
    private static final String COLUMN_INCREMENT_MAX_AMOUNT = "inc_max_amount";
    private static final String COLUMN_PRESALE = "presale";
    private static final String COLUMN_TIME_FOR_GROWN = "time_for_grown";

    // Названия солбцов временной таблицы
    private static final String TABLE_NAME_3 = "current";
    private static final String COLUMN_CURRENT_BLOCKCHAIN = "current_blockchain";
    private static final String COLUMN_CURRENT_TYPE = "current_type";

    private SQLiteDatabase mDataBase;

    public DB(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
        System.out.println(Environment.getDataDirectory());
    }

    public void updateValue(String tableName, String column, int value, int id) {
        ContentValues cv = new ContentValues();
        cv.put(column, value);
        mDataBase.update(tableName, cv, "id=?", new String[]{Integer.toString(id)} );
    }

    public void updateValue(String tableName, String column, float value, int id) {
        ContentValues cv = new ContentValues();
        cv.put(column, value);
        mDataBase.update(tableName, cv, "id=?", new String[]{Integer.toString(id)} );
    }

    public void updateValue(String tableName, String column, String value, int id) {
        ContentValues cv = new ContentValues();
        cv.put(column, value);
        mDataBase.update(tableName, cv, "id=?", new String[]{Integer.toString(id)} );
    }

    public long firstInsertNft(String wallet, long count, String name, String symbol, String founderWallet) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SYMBOL, symbol);
        cv.put(COLUMN_OWNER, wallet);
        cv.put(COLUMN_FOUNDER, founderWallet);
        cv.put(COLUMN_TOTAL_SUPPLY, count);
        cv.put(COLUMN_BLOCKCHAIN, selectCurrent().blockchain);
        return mDataBase.insert(TABLE_NAME_2, null, cv);
    }

    public long insertSimple(String wallet, long count, String name, String symbol,
                             int decimals, boolean burn, boolean mint, boolean safemoon) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SYMBOL, symbol);
        cv.put(COLUMN_OWNER, wallet);
        cv.put(COLUMN_DECIMALS, decimals);
        cv.put(COLUMN_TOTAL_SUPPLY, count);
        cv.put(COLUMN_BURN, burn);
        cv.put(COLUMN_MINT, mint);
        cv.put(COLUMN_SAFEMOON, safemoon);
        cv.put(COLUMN_BLOCKCHAIN, selectCurrent().blockchain);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    private int getMaxId() {
        String get_str = "SELECT MAX(id) AS id FROM " + TABLE_NAME_2;
        mDataBase.execSQL(get_str);
        Cursor mCursor = mDataBase.rawQuery(get_str, null);
        mCursor.moveToFirst();
        int id = mCursor.getInt(0);
        return id;
    }

    public int updateNft(int per_tx, int per_wallet, float start_price, String time_for_grown, String url, boolean fixed_token, boolean presale) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PER_TX, per_tx);
        cv.put(COLUMN_PER_WALLET, per_wallet);
        cv.put(COLUMN_START_PRICE, start_price);
        cv.put(COLUMN_TIME_FOR_GROWN, time_for_grown);
        cv.put(COLUMN_URI, url);
        cv.put(COLUMN_INCREMENT_MAX_AMOUNT, fixed_token);
        cv.put(COLUMN_PRESALE, presale);

        return mDataBase.update(TABLE_NAME_2, cv, COLUMN_ID + " = ?", new String[] { String.valueOf(getMaxId())});
    }

    public int updateCheckCodeNft(String check_code) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CHECK_CODE, check_code);
        return mDataBase.update(TABLE_NAME_2, cv, COLUMN_CHECK_CODE + " = ?", new String[] { String.valueOf(getMaxId())});
    }

    public int updateCheckCode(String check_code) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CHECK_CODE, check_code);
        return mDataBase.update(TABLE_NAME, cv, COLUMN_CHECK_CODE + " = ?", new String[] { String.valueOf(getMaxId())});
    }

    public CurrentState selectCurrent() {
        Cursor mCursor = mDataBase.query(TABLE_NAME_3, null, null, null, null, null, null);
        mCursor.moveToFirst();
        String blockchain = mCursor.getString(0);
        String type = mCursor.getString(1);
        return new CurrentState(blockchain, type);
    }


    public void insertNewCurrent(String blockchain){
        mDataBase.delete(TABLE_NAME_3, null, null);
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CURRENT_BLOCKCHAIN, blockchain);
        mDataBase.insert(TABLE_NAME_3, null, cv);
    }

    public void addTypeToCurrent(String type) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CURRENT_TYPE, type);
        mDataBase.update(TABLE_NAME_3,  cv, null, null);
    }


    public class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // deleted
            // COLUMN_FEE_ADDRESS + " TEXT," +
            // COLUMN_BURNING_PERCENT + "INTEGER," +
            // COLUMN_FEE_PERCENT + "INTEGER," +
            // COLUMN_STAKING_PERCENT + "INTEGER," +

            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_BURN + " BOOLEAN DEFAULT 0," +
                    COLUMN_MINT + " BOOLEAN DEFAULT 0," +
                    COLUMN_SAFEMOON + " BOOLEAN DEFAULT 0," +
                    COLUMN_TEST_MODE + " BOOLEAN DEFAULT 1," +
                    COLUMN_BLOCKCHAIN + " TEXT DEFAULT \"bsc\"," +
                    COLUMN_STANDARD + "TEXT," +
                    COLUMN_OWNER + "TEXT," +
                    COLUMN_SYMBOL + "TEXT," +
                    COLUMN_TOTAL_SUPPLY + "INTEGER," +
                    COLUMN_NAME + "TEXT," +
                    COLUMN_DECIMALS + "INTEGER," +
                    COLUMN_CHECK_CODE + "TEXT," +
                    COLUMN_STATUS + "TEXT DEFAULT \"not completed\");";

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
                    COLUMN_INCREMENT_MAX_AMOUNT + " BOOLEAN," +
                    COLUMN_PRESALE + " BOOLEAN," +
                    COLUMN_TIME_FOR_GROWN + " TEXT," +
                    COLUMN_STANDARD + "TEXT," +
                    COLUMN_OWNER + "TEXT," +
                    COLUMN_SYMBOL + "TEXT," +
                    COLUMN_STATUS + "TEXT DEFAULT \"not completed\");";
            db.execSQL(query);

            query = "CREATE TABLE " + TABLE_NAME_3 + " (" +
                     COLUMN_CURRENT_BLOCKCHAIN + " TEXT, " +
                     COLUMN_CURRENT_TYPE + " TEXT);";

            db.execSQL(query);
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        onCreate(db);
    }

    }
}