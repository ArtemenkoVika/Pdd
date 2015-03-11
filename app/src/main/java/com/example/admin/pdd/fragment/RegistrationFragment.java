package com.example.admin.pdd.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.pdd.R;

import java.util.ArrayList;
import java.util.Map;

public class RegistrationFragment extends BaseFragment implements View.OnClickListener,
        AdapterView.OnItemClickListener {
    private String keyPreferencesUserName = "user_name_1";
    private SharedPreferences userNamePreferences;
    private EditText editText;
    private Button button;
    private ListView listView;
    private TextView textView;
    private ArrayList arrayListNameUsers;
    private ArrayAdapter arrayAdapter;
    private Map<String, ?> allPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        editText = (EditText) view.findViewById(R.id.edit_user_name);
        button = (Button) view.findViewById(R.id.button_registration2);
        listView = (ListView) view.findViewById(R.id.list_of_users_name);
        textView = (TextView) view.findViewById(R.id.text_info);

        button.setOnClickListener(this);
        listView.setOnItemClickListener(this);

        dialogMessage = getActivity().getResources().getString(R.string.text_dialog_registration);

        arrayListNameUsers = new ArrayList();
        userNamePreferences = getActivity().getSharedPreferences("userName", Context.MODE_PRIVATE);
        allPreferences = userNamePreferences.getAll();
        if (allPreferences.size() != 0) {
            for (int i = 1; i <= allPreferences.size(); i++) {
                keyPreferencesUserName = keyPreferencesUserName.substring(0, 10) + i;
                arrayListNameUsers.add(allPreferences.get(keyPreferencesUserName));
            }
            textView.setVisibility(View.VISIBLE);
        }
        arrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, arrayListNameUsers);
        listView.setAdapter(arrayAdapter);
        return view;
    }

    public String getKeyPreferencesUserName() {
        return keyPreferencesUserName;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_registration2:
                if (editText.getText().toString().equals(""))
                    showDialogInfo(dialogTitle, dialogMessage);
                else {
                    Editor editor = userNamePreferences.edit();
                    if (userNamePreferences.contains(keyPreferencesUserName)) {
                        allPreferences = userNamePreferences.getAll();
                        int i = allPreferences.size() + 1;
                        keyPreferencesUserName = keyPreferencesUserName.substring(0, 10) + i;
                    }
                    editor.putString(keyPreferencesUserName, editText.getText().toString());
                    editor.commit();
                    user.setLoginUserName(editText.getText().toString());
                    getActivity().onBackPressed();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        keyPreferencesUserName = keyPreferencesUserName.substring(0, 10) + ++position;
        user.setLoginUserName(userNamePreferences.getString(keyPreferencesUserName, ""));
        getActivity().onBackPressed();
    }
}
