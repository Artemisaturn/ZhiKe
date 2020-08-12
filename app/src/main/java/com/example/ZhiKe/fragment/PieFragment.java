package com.example.ZhiKe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PieFragment extends Fragment {
    private static final String DATA_KEY="piefragment_data_key";
    private String mData;
    private View mChart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mData=arguments.getString(DATA_KEY);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView=new TextView(getContext());
        textView.setText(mData);
        return textView;
    }

    public static PieFragment newInstance(String data) {

        Bundle args = new Bundle();
        args.putString(DATA_KEY,data);
        PieFragment fragment = new PieFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
