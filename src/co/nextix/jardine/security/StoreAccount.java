package co.nextix.jardine.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class StoreAccount {
	public static final String PREFERENCE_FILENAME = "userDetails";
	public static final String PASSWORD_KEY = "password";
	public static final String USERNAME_KEY = "username";
	public static final String SESSION_EXISTS_KEY = "sessionExists";
	public static final String ID_KEY = "userid";
	public static final String ROWID_KEY = "userrowid";
	public static final String SESSIONNAME_KEY = "sessionName";

	public static void save(Context context, String username, String password,
			String id, String rowid, String sessionName) {
		final Editor editor = context.getSharedPreferences(PREFERENCE_FILENAME,
				Context.MODE_PRIVATE).edit();
		try {
			editor.putString(StoreAccount.USERNAME_KEY,
					AEShelper.encrypt(username));
			editor.putString(StoreAccount.PASSWORD_KEY,
					AEShelper.encrypt(password));
			editor.putString(StoreAccount.ID_KEY, AEShelper.encrypt(id));
			editor.putString(StoreAccount.ROWID_KEY, AEShelper.encrypt(rowid));
			editor.putString(StoreAccount.SESSIONNAME_KEY,
					AEShelper.encrypt(sessionName));
			editor.putBoolean(StoreAccount.SESSION_EXISTS_KEY, true);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveSession(Context context, String sessionName) {
		final Editor editor = context.getSharedPreferences(PREFERENCE_FILENAME,
				Context.MODE_PRIVATE).edit();
		try {
			editor.putString(StoreAccount.SESSIONNAME_KEY,
					AEShelper.encrypt(sessionName));
			editor.putBoolean(StoreAccount.SESSION_EXISTS_KEY, true);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Bundle restore(Context context) {
		final Bundle bundle = new Bundle();
		final SharedPreferences prefs = context.getSharedPreferences(
				PREFERENCE_FILENAME, Context.MODE_PRIVATE);
		final String username = prefs.getString(StoreAccount.USERNAME_KEY, "");
		final String password = prefs.getString(StoreAccount.PASSWORD_KEY, "");
		final String id = prefs.getString(StoreAccount.ID_KEY, "");
		final String rowid = prefs.getString(StoreAccount.ROWID_KEY, "");
		final String sessionname = prefs.getString(
				StoreAccount.SESSIONNAME_KEY, "");
		try {
			bundle.putString(Account.USERNAME, AEShelper.decrypt(username));
			bundle.putString(Account.PASSWORD, AEShelper.decrypt(password));
			bundle.putString(Account.ID, AEShelper.decrypt(id));
			bundle.putString(Account.ROWID, AEShelper.decrypt(rowid));
			bundle.putString(Account.SESSION_NAME,
					AEShelper.decrypt(sessionname));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bundle;
	}

	public static boolean exists(Context context) {
		return context.getSharedPreferences(PREFERENCE_FILENAME,
				context.MODE_PRIVATE).getBoolean(
				StoreAccount.SESSION_EXISTS_KEY, false);
	}

	public static void clear(Context context) {
		final SharedPreferences prefs = context.getSharedPreferences(
				PREFERENCE_FILENAME, context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.clear();
		editor.putBoolean(SESSION_EXISTS_KEY, false);
		editor.commit();

	}

	public static class Account {
		public static final String USERNAME = "username_key";
		public static final String PASSWORD = "password_key";
		public static final String ID = "userid_key";
		public static final String ROWID = "userrowid_key";
		public static final String SESSION_NAME = "sessionName_key";
	}
}
