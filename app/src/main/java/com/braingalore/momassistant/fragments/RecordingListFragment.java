package com.braingalore.momassistant.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braingalore.momassistant.R;
import com.braingalore.momassistant.adapters.RecordListAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by s92 on 11/27/2017.
 */

public class RecordingListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.list_fragment, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        recyclerView.addItemDecoration(horizontalDecoration);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        String sourcePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + getResources().getString(R.string.app_name) + "/";
        File folder_file = new File(sourcePath);
        File[] files = folder_file.listFiles();
        List<File> fileList = new ArrayList<File>();
        if (files != null) {
            for (File file : files) {
                // checking the File is file or directory
                if (file.isFile()) {
                    String path = file.getAbsolutePath();
                    String extension = path
                            .substring(path.lastIndexOf(".") + 1);
                    // if the file is audio type, then send it to adapter
                    if (extension.equalsIgnoreCase("mp4")) {
                        fileList.add(file);
                        System.out.println(path + " is a media file ");
                    }
                }
            }
        }
        mAdapter = new RecordListAdapter(fileList);
        recyclerView.setAdapter(mAdapter);
        return v;
    }
}
