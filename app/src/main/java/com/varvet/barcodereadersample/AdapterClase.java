package com.varvet.barcodereadersample;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdapterClase extends BaseAdapter {

    List<Clase> clases = new ArrayList<>();
    Context context;

    public AdapterClase(){

    }

    public AdapterClase(List<Clase> clases, Context context){
        this.clases = clases;
        this.context = context;
    }


    @Override
    public int getCount() {
        return clases.size();
    }

    @Override
    public Object getItem(int position) {
        return clases.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final Context context = parent.getContext();
            final Clase claseActual = clases.get(position);
            LinearLayout view = new LinearLayout(context);
            view.setOrientation(LinearLayout.VERTICAL);
            view.setPadding(5, 5, 5, 5);

            LinearLayout view2a = new LinearLayout(context);
            view2a.setOrientation(LinearLayout.HORIZONTAL);
            view2a.setMinimumHeight(90);

            LinearLayout view3 = new LinearLayout(context);
            view3.setOrientation(LinearLayout.VERTICAL);
            view3.setGravity(Gravity.CENTER);
            view3.setPadding(5, 0, 50, 0);

            TextView name = new TextView(context);
            name.setText(claseActual.getName());
            view3.addView(name);
            TextView hour = new TextView(context);
            String textHour = claseActual.getHourStart() + "     -     " + (claseActual.getHourStart()+1);
            hour.setText(textHour);
            view3.addView(hour);
            view2a.addView(view3);
            view.addView(view2a);
            return view;
        }
        return convertView;
    }

    public void guardarClases(List<Clase> clases){

    }

    public List<Clase> getCourses() {
        return clases;
    }

    public void setCourses(List<Clase> clases) {
        this.clases = clases;
    }
}