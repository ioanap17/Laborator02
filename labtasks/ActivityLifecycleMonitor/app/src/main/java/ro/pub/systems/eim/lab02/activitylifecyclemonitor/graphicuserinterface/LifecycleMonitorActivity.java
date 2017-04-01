package ro.pub.systems.eim.lab02.activitylifecyclemonitor.graphicuserinterface;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;

import ro.pub.systems.eim.lab02.activitylifecyclemonitor.R;
import ro.pub.systems.eim.lab02.activitylifecyclemonitor.general.Constants;
import ro.pub.systems.eim.lab02.activitylifecyclemonitor.general.Utilities;

public class LifecycleMonitorActivity extends AppCompatActivity {

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText usernameEditText = (EditText)findViewById(R.id.username_edit_text);
            EditText passwordEditText = (EditText)findViewById(R.id.password_edit_text);
            if (((Button)view).getText().toString().equals(getResources().getString(R.string.ok_button_content))) {
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                View popupContent;
                if (Utilities.allowAccess(getApplicationContext(), username, password)) {
                    popupContent = layoutInflater.inflate(R.layout.popup_window_authentication_success, null);
                } else {
                    popupContent = layoutInflater.inflate(R.layout.popup_window_authentication_fail, null);
                }
                final PopupWindow popupWindow = new PopupWindow(popupContent, android.app.ActionBar.LayoutParams.WRAP_CONTENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
                Button dismissButton = (Button)popupContent.findViewById(R.id.dismiss_button);
                dismissButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
            if (((Button)view).getText().toString().equals(getResources().getString(R.string.cancel_button_content))) {
                usernameEditText.setText(getResources().getText(R.string.empty));
                passwordEditText.setText(getResources().getText(R.string.empty));
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_monitor);

        Button okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);

        if(savedInstanceState == null) {
            Log.d(Constants.TAG, "onCreate() method was invoked without a previous state");
        }
        else {
            Log.d(Constants.TAG, "onCreate() method was invoked. It has a previous state.");

            if (savedInstanceState.containsKey(Constants.USERNAME_EDIT_TEXT)) {
                EditText usernameEditText = (EditText)findViewById(R.id.username_edit_text);
                usernameEditText.setText(savedInstanceState.getString(Constants.USERNAME_EDIT_TEXT));
            }
            if(savedInstanceState.containsKey(Constants.PASSWORD_EDIT_TEXT)){
                EditText password = (EditText) findViewById(R.id.password_edit_text);
                password.setText(savedInstanceState.getString(Constants.PASSWORD_EDIT_TEXT));
            }
            if(savedInstanceState.containsKey(Constants.REMEMBER_ME_CHECKBOX)){
                CheckBox checkBox = (CheckBox) findViewById(R.id.remember_me_checkbox);
                checkBox.setChecked(savedInstanceState.getBoolean(Constants.REMEMBER_ME_CHECKBOX));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        CheckBox checkBox = (CheckBox) findViewById(R.id.remember_me_checkbox);
        EditText username = (EditText) findViewById(R.id.username_edit_text);
        EditText password = (EditText) findViewById(R.id.password_edit_text);

        if (checkBox.isChecked()) {
            outState.putString(Constants.USERNAME_EDIT_TEXT, username.getText().toString());
            outState.putString(Constants.PASSWORD_EDIT_TEXT, password.getText().toString());
            outState.putBoolean(Constants.REMEMBER_ME_CHECKBOX, checkBox.isChecked());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(Constants.TAG, "onRestart() method was invoked without a previous state");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(Constants.TAG, "onStart() method was invoked without a previous state");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(Constants.TAG, "onResume() method was invoked without a previous state");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(Constants.TAG, "onPause() method was invoked without a previous state");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(Constants.TAG, "onStop() method was invoked without a previous state");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(Constants.TAG, "onDestroy() method was invoked without a previous state");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lifecycle_monitor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
