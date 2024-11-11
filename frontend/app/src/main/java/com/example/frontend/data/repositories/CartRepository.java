package com.example.frontend.data.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.frontend.data.services.ApiService;
import com.example.frontend.data.sqlite.CartDBHelper;

import java.util.HashMap;
import java.util.Map;

public class CartRepository {
    private final CartDBHelper dbHelper;
    private final ApiService apiService;

    public CartRepository(Context context, ApiService apiService) {
        this.dbHelper = new CartDBHelper(context);
        this.apiService = apiService;
    }

    public void addToCart(int productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CartDBHelper.COLUMN_PRODUCT_ID, productId);
        values.put(CartDBHelper.COLUMN_QUANTITY, 1);

        Cursor cursor = db.query(CartDBHelper.TABLE_CART,
                new String[]{CartDBHelper.COLUMN_QUANTITY},
                CartDBHelper.COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(productId)},
                null, null, null);

        if (cursor.moveToFirst()) {
            int currentQuantity = cursor.getInt(0);
            values.put(CartDBHelper.COLUMN_QUANTITY, currentQuantity + 1);
            db.update(CartDBHelper.TABLE_CART, values,
                    CartDBHelper.COLUMN_PRODUCT_ID + "=?",
                    new String[]{String.valueOf(productId)});
        } else {
            db.insert(CartDBHelper.TABLE_CART, null, values);
        }
        cursor.close();
        db.close();
    }

    public void removeFromCart(int productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(CartDBHelper.TABLE_CART,
                CartDBHelper.COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(productId)});
        db.close();
    }

    public void updateQuantity(int productId, int quantity) {
        if (quantity <= 0) {
            removeFromCart(productId);
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CartDBHelper.COLUMN_QUANTITY, quantity);

        db.update(CartDBHelper.TABLE_CART, values,
                CartDBHelper.COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(productId)});
        db.close();
    }

    public Map<Integer, Integer> getCartProductsWithQuantities() {
        Map<Integer, Integer> productQuantities = new HashMap<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(CartDBHelper.TABLE_CART,
                new String[]{CartDBHelper.COLUMN_PRODUCT_ID, CartDBHelper.COLUMN_QUANTITY},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            int productId = cursor.getInt(0);
            int quantity = cursor.getInt(1);
            productQuantities.put(productId, quantity);
        }

        cursor.close();
        db.close();
        return productQuantities;
    }

    public ApiService getApiService() {
        return apiService;
    }
}
