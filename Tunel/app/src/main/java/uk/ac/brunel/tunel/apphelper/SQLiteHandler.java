/*
 * Created by Mohamed Bushra on 17/01/17 12:59
 * Copyright (c) 2017. All rights reserved.
 *
 * Last Modified 17/01/17 12:58.
 */

package uk.ac.brunel.tunel.apphelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = SQLiteHandler.class.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "tunel";

	// Login table name
	private static final String TABLE_USER = "users";

	// Login Table Columns names
	private static final String KEY_ID = "id";
    private static final String KEY_UID = "unique_id";
	private static final String KEY_NAME = "name";
    private static final String KEY_STUDENTID = "student_id";
    private static final String KEY_COURSELEVEL = "course_LEVEL";
	private static final String KEY_EMAIL = "email";


	public SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_UID + " TEXT," + KEY_NAME + " TEXT,"
				+ KEY_STUDENTID + "TEXT" + KEY_COURSELEVEL + "TEXT" + KEY_EMAIL + " TEXT UNIQUE," + ")";
		db.execSQL(CREATE_LOGIN_TABLE);

		Log.d(TAG, "Database tables created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public void addUser(String name, String email, String uid, String student_id, String course_level) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
        values.put(KEY_UID, uid); // Uniqque ID
		values.put(KEY_NAME, name); // Name
        values.put(KEY_STUDENTID, student_id); // Student ID
        values.put(KEY_COURSELEVEL, course_level); // Course Level
		values.put(KEY_EMAIL, email); // Email


		// Inserting Row
		long id = db.insert(TABLE_USER, null, values);
		db.close(); // Closing database connection

		Log.d(TAG, "New user inserted into sqlite: " + id);
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<>();
		String selectQuery = "SELECT  * FROM " + TABLE_USER;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
            user.put("uid", cursor.getString(1));
			user.put("name", cursor.getString(2));
			user.put("student id", cursor.getString(3));
            user.put("course level", cursor.getString(4));
			user.put("email", cursor.getString(5));
		}
		cursor.close();
		db.close();
		// return user
		Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

		return user;
	}

	/**
	 * Re-create database Delete all tables and create them again
	 * */
	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_USER, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}

}
