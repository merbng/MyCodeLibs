package com.app.merbng.mycodelibs.music;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.app.merbng.mycodelibs.R;

import butterknife.BindView;

public class TopListFragment extends Fragment {

    @BindView(R.id.imageView)
    ImageView mImageView;

    public static TopListFragment newInstance() {
        TopListFragment fragment = new TopListFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_list, container, false);
//        ButterKnife.bind(this, view);

        //http://p4.music.126.net/QivqazvQqfjhCRZX4nxINA==/1369991498895149.jpg?param=1024y1024

//        Glide.with(this)
//                .load("http://p4.music.126.net/QivqazvQqfjhCRZX4nxINA==/1369991498895149.jpg?param=1024y1024")
//                .placeholder(R.drawable.playlist_placeholder)
//                .into(mImageView);

        return view;
    }

}
