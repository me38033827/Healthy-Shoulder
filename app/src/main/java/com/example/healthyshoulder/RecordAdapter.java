package com.example.healthyshoulder;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zh on 2017/3/11.
 */

public class RecordAdapter extends ArrayAdapter<Record> {

    private int resourceId;

    public RecordAdapter(Context context, @LayoutRes int resource, @NonNull List<Record> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Record record=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);

        TextView date=(TextView)view.findViewById(R.id.textView_history_date);
        TextView index1=(TextView)view.findViewById(R.id.textView_history_index1);
        TextView index2=(TextView)view.findViewById(R.id.textView_history_index2);
        TextView index3=(TextView)view.findViewById(R.id.textView_history_index3);
        TextView index4=(TextView)view.findViewById(R.id.textView_history_index4);
        TextView index5=(TextView)view.findViewById(R.id.textView_history_index5);
        TextView index6=(TextView)view.findViewById(R.id.textView_history_index6);

        date.setText(record.getDate());
        index1.setText(record.getIndex1());
        index2.setText(record.getIndex2());
        index3.setText(record.getIndex3());
        index4.setText(record.getIndex4());
        index5.setText(record.getIndex5());
        index6.setText(record.getIndex6());


        return view;
    }

}
