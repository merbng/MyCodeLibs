package com.app.merbng.mycodelibs.A_circle_float_menu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

public class CircularMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_menu);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ContentFragment())
                    .commit();
        }
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ContentFragment extends Fragment implements AdapterView.OnItemClickListener {

        private ListView demosListView;

        public ContentFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_demo, container, false);

            String[] items = { "Menu with FloatingActionButton",
                               "Menu attached to custom button",
                               "Menu with custom animation",
                               "Menu in ScrollView",
                               "Menu as system overlay"
                            };
            ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
            demosListView = (ListView) rootView.findViewById(R.id.demosListView);
            demosListView.setAdapter(simpleAdapter);
            demosListView.setOnItemClickListener(this);
            return rootView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    startActivity(new Intent(getActivity(), MenuWithFABActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), MenuWithCustomActionButtonActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getActivity(), MenuWithCustomAnimationActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(getActivity(), MenuInScrollViewActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(getActivity(), SystemOverlayMenuActivity.class));
                    break;
            }
        }
    }
}
