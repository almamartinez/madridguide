package io.keepcoding.madridguide.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import io.keepcoding.madridguide.model.BaseModel;


public abstract class BaseDAO<E extends BaseModel> implements DAOPersistable<E> {
    protected WeakReference<Context> context;
    protected DBHelper dbHelper;
    protected SQLiteDatabase db;

    public BaseDAO(Context context, DBHelper dbHelper) {
        this.context = new WeakReference<Context>(context);
        this.dbHelper = dbHelper;
        this.db = dbHelper.getDB();
    }

    /**
     * Insert an element in DB
     *
     * @param element shouldn't be null
     * @return 0 if E is null, id if insert is OK, INVALID_ID if insert fails
     */
    @Override
    public long insert(@NonNull E element) {
        if (element == null) {
            return 0;
        }
        // insert

        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try { // Null Column Hack
            id = db.insert(getTableName(), null, getContentValues(element));
            element.setId(id);
            db.setTransactionSuccessful();  // COMMIT
        } finally {
            db.endTransaction();
        }

        return id;
    }

    @Override
    public void update(long id, @NonNull E data) {

    }

    @Override
    public int delete(long id) {
        return db.delete(getTableName(), getIdFieldName() + " = " + id, null);  // 1st way
        // db.delete(TABLE_SHOP, KEY_SHOP_ID + " = ?", new String[]{ "" + id });  // 2nd way
        //db.delete(TABLE_SHOP, KEY_SHOP_ID + " = ? AND " + KEY_SHOP_NAME + "= ?" ,
        //        new String[]{ "" + id, "pepito" });  // 2nd way

    }

    @Override
    public void deleteAll() {
        db.delete(getTableName(), null, null);
    }

    @Nullable
    @Override
    public Cursor queryCursor() {
        Cursor c = db.query(getTableName(), getAllColumnNames(), null, null, null, null, getIdFieldName());
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
        }
        return c;
    }

    @Override
    public
    @Nullable
    E query(final long id) {
        Cursor c = db.query(getTableName(), getAllColumnNames(), getIdFieldName() + " = " + id, null, null, null, getIdFieldName());

        if (c != null && c.getCount() == 1) {
            c.moveToFirst();
        } else {
            return null;
        }

        E e = getElementFromCursor(c);

        return e;
    }

    @Nullable
    @Override
    public List<E> query() {
        Cursor c = queryCursor();

        if (c == null || !c.moveToFirst()) {
            return null;
        }

        List<E> elements = new LinkedList<>();

        c.moveToFirst();
        do {
            E e = getElementFromCursor(c);
            elements.add(e);
        } while (c.moveToNext());

        return elements;
    }

    public Cursor queryCursor(long id) {
        Cursor c = db.query(getTableName(), getAllColumnNames(), getIdFieldName() + " = " + id, null, null, null, getIdFieldName());
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
        }
        return c;
    }

    public abstract @NonNull ContentValues getContentValues(final @NonNull E element);

    public abstract E getElementFromCursor(Cursor c);

    public abstract @NonNull String getTableName();
    public abstract @NonNull String getIdFieldName();
    public abstract @NonNull String[] getAllColumnNames();
}