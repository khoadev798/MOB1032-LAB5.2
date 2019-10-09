package vn.com.productdatabase.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.com.productdatabase.datamodel.Product;

public class ProductDAO {
    public static final String TABLE_NAME="products";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_NAME ="name";
    public static final String CREATE_TABLE_PRODUCTS =
                    "CREATE TABLE products( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ",name VARCHAR NOT NULL" +
                    ")";
    public static final String DROP_TABLE_PRODUCTS = "DROP TABLE IF EXIST"+TABLE_NAME;

    private  SQLiteDatabase db;
    public  ProductDAO(SQLiteDatabase db){
        this.db = db;
    }
    public long insert(Product product){
        ContentValues values  = new ContentValues();
        values.put(COLUMN_NAME_NAME, product.getName());
        return db.insert(TABLE_NAME, null, values);
    }
    public int update(Product product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAME, product.getName());
        String selection = COLUMN_NAME_ID + " - ?";
        String selectionArgs[]={product.getId()+""};
        return db.update(TABLE_NAME, values, selection,selectionArgs);
    }
    public int deleteAll(){
        return db.delete(TABLE_NAME,null,null);
    }
    public int delete(Product product){
        String selection = COLUMN_NAME_ID + " - ?";
        String selectionArgs[]={product.getId()+""};
        return db.delete(TABLE_NAME, selection,selectionArgs);
    }
    public List getAll(){
        List<Product> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
    while (cursor.moveToNext()){
        Product product = new Product();
        product.setId(cursor.getInt(0));
        product.setName(cursor.getString(1));
        list.add(product);
    }
    return list;
    }
}

