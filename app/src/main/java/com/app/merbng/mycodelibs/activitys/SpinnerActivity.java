package com.app.merbng.mycodelibs.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.piotrek.customspinner.CustomSpinner;

/**自定义下拉框
 * https://github.com/piotrek1543/CustomSpinner
 */
public class SpinnerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        final TextView response = (TextView) findViewById(R.id.spinner_response);
        final TextView choice = (TextView) findViewById(R.id.spinner_choice);

        CustomSpinner colorSpinner = (CustomSpinner) findViewById(R.id.number_spinner);
        String[] types = getResources().getStringArray(R.array.number_array);
        colorSpinner.initializeStringValues(types, getString(R.string.color_spinner_hint));

        colorSpinner.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            @Override
            public void onSpinnerOpened() {
                response.setText(R.string.action_spinner_open);
            }

            @Override
            public void onSpinnerClosed() {
                response.setText(R.string.action_spinner_closed);
            }
        });

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!adapterView.getSelectedItem().equals(getString(R.string.color_spinner_hint)))
                    choice.setText(adapterView.getSelectedItem().toString());
                else onNothingSelected(adapterView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                choice.setText("Nothing was selected");
            }
        });
    }
}
