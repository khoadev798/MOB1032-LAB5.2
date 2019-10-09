package vn.com.productdatabase.datamodel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import vn.com.productdatabase.R;

public class CustomAdapter extends ArrayAdapter<Product> {
    private Context context;
    private int resource;
    private ArrayList<Product> arrProduct;

    final private  String TAG = getClass().getSimpleName();
    public CustomAdapter(Context context, int resource, ArrayList<Product> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrProduct = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row_product,parent,false);
            viewHolder.tv_id =(TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tv_name =(TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
            Log.d(TAG, "getView"+(position+1));
        }
        else{
            viewHolder =(ViewHolder) convertView.getTag();
        }
        Product product = arrProduct.get(position);
        viewHolder.tv_id.setText((position+1)+"");
        viewHolder.tv_name.setText(product.getName());
        return convertView;
    }
    public class ViewHolder{
        TextView tv_id;
        TextView tv_name;
    }
}
