package vn.com.productdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vn.com.productdatabase.dao.ProductDAO;
import vn.com.productdatabase.datamodel.CustomAdapter;
import vn.com.productdatabase.datamodel.Product;

public class MainActivity extends AppCompatActivity {
    private ListView lvProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvProduct = (ListView) findViewById(R.id.lv_product);
        final String Tag="Check";
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(Tag, ""+db.getVersion());
        ProductDAO productDAO = new ProductDAO(dbHelper.getWritableDatabase());

        ArrayList<Product> products = (ArrayList<Product>) productDAO.getAll();
        for(Product product : products){
            Log.d(Tag, product.toString());
        }

        CustomAdapter customAdapter = new CustomAdapter(this,R.layout.row_product,products);
        lvProduct.setAdapter(customAdapter);
    }
}
