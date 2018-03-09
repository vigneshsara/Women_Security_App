package com.example.sasikumar.alerti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SASIKUMAR on 22-02-2018.
 */
public class SQLiteListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> name;
    ArrayList<String>mobile;


    public SQLiteListAdapter(
            Context context2,
            ArrayList<String> name,ArrayList<String> mobile) {

        this.context = context2;
        this.name = name;
        this.mobile=mobile;

    }

    public int getCount() {

        return name.size();
    }

    public Object getItem(int position) {

        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);

            holder = new Holder();

            holder.textviewid = (TextView) child.findViewById(R.id.textView1);
            holder.textviewmobile=(TextView)child.findViewById(R.id.textViewmobilelist);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(name.get(position));
        holder.textviewmobile.setText(mobile.get(position));
        return child;
    }

    public class Holder {
        TextView textviewid;
        TextView textviewmobile;

    }
}
