package com.example.admin.pdd.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.example.admin.pdd.entity.User;
import com.example.admin.pdd.pattern.Singleton;

public class BaseFragment extends Fragment implements DialogInterface.OnClickListener {
    protected Singleton singleton = Singleton.getInstance();
    protected AlertDialog dialogInfo;
    protected String dialogTitle = "";
    protected String dialogMessage = "";
    protected User user = User.getInstance();

    @Override
    public void onResume() {
        super.onResume();
        if (singleton.isShowDialog()) {
            showDialogInfo(dialogTitle, dialogMessage);
            singleton.setShowDialog(false);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (dialogInfo != null && dialogInfo.isShowing()) {
            singleton.setShowDialog(true);
            dialogInfo.cancel();
        }
    }

    public void showDialogInfo(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton("OK", this);
        dialogInfo = builder.create();
        dialogInfo.setOwnerActivity(getActivity());
        dialogInfo.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
    }
}
