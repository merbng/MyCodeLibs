package com.app.merbng.mycodelibs.A_StudyProcessButton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.merbng.mycodelibs.A_StudyProcessButton.utils.ProgressGenerator;
import com.app.merbng.mycodelibs.R;
import com.dd.processbutton.iml.SubmitProcessButton;


public class MessageActivity extends Activity implements ProgressGenerator.OnCompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_message);

        final EditText editMessage = (EditText) findViewById(R.id.editMessage);

        final ProgressGenerator progressGenerator = new ProgressGenerator(this);
        final SubmitProcessButton btnSend = (SubmitProcessButton) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator.start(btnSend);
                btnSend.setEnabled(false);
                editMessage.setEnabled(false);
            }
        });
    }

    @Override
    public void onComplete() {
        Toast.makeText(this, R.string.Loading_Complete, Toast.LENGTH_LONG).show();
    }

}
