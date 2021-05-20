package com.hobarb.sountry.ui.signup.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hobarb.sountry.R;
import com.hobarb.sountry.utilities.constants;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    ArrayList<String> genres;
    Context context;

    public GridAdapter(Context context, ArrayList<String> genres){
        this.context = context;
        this.genres = genres;


    }
    @Override
    public int getCount() {
        return genres.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final boolean[] is_genre_selected = {false};
        String curr_genre = genres.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_genre_signup, null);
        TextView textView = view.findViewById(R.id.tv_lay_genreSignUp);
        textView.setText(""+curr_genre);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!is_genre_selected[0])
                {

                    constants.USER_PREFERRED_GENRES.add(curr_genre);
                    view.setBackgroundResource(R.drawable.bg_genre_signup_selected);
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    is_genre_selected[0] = true;
                }
                else {

                    constants.USER_PREFERRED_GENRES.remove(curr_genre);
                    view.setBackgroundResource(R.drawable.bg_genre_signup);
                    textView.setTextColor(Color.parseColor("#1A1A1D"));
                    is_genre_selected[0] = false  ;
                }

            }
        });

        return view;
    }
}
