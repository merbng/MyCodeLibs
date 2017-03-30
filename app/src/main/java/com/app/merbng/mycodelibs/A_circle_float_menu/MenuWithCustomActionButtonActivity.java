package com.app.merbng.mycodelibs.A_circle_float_menu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MenuWithCustomActionButtonActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_with_custom_action_button);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new CustomButtonDemoFragment())
                    .commit();
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class CustomButtonDemoFragment extends Fragment {

        public CustomButtonDemoFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_menu_with_custom_action_button, container, false);
            Button centerActionButton = (Button) rootView.findViewById(R.id.centerActionButton);
            // Add some items to the menu. They are regular views as well!
            ImageView a = new ImageView(getActivity());a.setBackgroundResource(R.drawable.ic_launcher);
            TextView b = new TextView(getActivity()); b.setText("b"); b.setBackgroundResource(android.R.drawable.btn_default_small);
            TextView c = new TextView(getActivity()); c.setText("c"); c.setBackgroundResource(android.R.drawable.btn_default_small);
//            TextView d = new TextView(getActivity()); d.setText("d"); d.setBackgroundResource(android.R.drawable.btn_default_small);
//            TextView e = new TextView(getActivity()); e.setText("e"); e.setBackgroundResource(android.R.drawable.btn_default_small);
//            TextView f = new TextView(getActivity()); f.setText("f"); f.setBackgroundResource(android.R.drawable.btn_default_small);
//            TextView g = new TextView(getActivity()); g.setText("g"); g.setBackgroundResource(android.R.drawable.btn_default_small);
//            TextView h = new TextView(getActivity()); h.setText("h"); h.setBackgroundResource(android.R.drawable.btn_default_small);
            FrameLayout.LayoutParams tvParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            a.setLayoutParams(tvParams);
            b.setLayoutParams(tvParams);
            c.setLayoutParams(tvParams);
//            d.setLayoutParams(tvParams);
//            e.setLayoutParams(tvParams);
//            f.setLayoutParams(tvParams);
//            g.setLayoutParams(tvParams);
//            h.setLayoutParams(tvParams);

            SubActionButton.Builder subBuilder = new SubActionButton.Builder(getActivity());

            FloatingActionMenu circleMenu = new FloatingActionMenu.Builder(getActivity())
                    .setStartAngle(-20) // A whole circle!
                    .setEndAngle(-160)
                    .setRadius(getResources().getDimensionPixelSize(R.dimen.radius_large))
                    .addSubActionView(a)
                    .addSubActionView(b)
                    .addSubActionView(c)
//                    .addSubActionView(d)
//                    .addSubActionView(e)
//                    .addSubActionView(f)
//                    .addSubActionView(g)
//                    .addSubActionView(h)
                    .attachTo(centerActionButton)
                    .build();

            return rootView;
        }
    }
}