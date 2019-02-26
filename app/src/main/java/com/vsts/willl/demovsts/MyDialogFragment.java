package com.vsts.willl.demovsts;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class MyDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Base_AlertDialog);
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        // 设置主题的构造方法
//        // AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
//        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
//        View view = layoutInflater.inflate(R.layout.my_dialog, null);
//        builder.setTitle("注意：")
//               // .setView(view)
//                .setMessage("是否退出应用？")
//                .setPositiveButton("确定", null)
//                .setNegativeButton("取消", null)
//                .setCancelable(false);
//        return builder.create();
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_dialog, container, false);
        //Do something
        // 设置宽度为屏宽、靠近屏幕底部。
//        final Window window = getDialog().getWindow();
//        window.setBackgroundDrawableResource(R.color.hockeyapp_text_light);
//        window.getDecorView().setPadding(0, 0, 0, 0);
//        WindowManager.LayoutParams wlp = window.getAttributes();
//        wlp.gravity = Gravity.BOTTOM;
//        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(wlp);

        return rootView;
    }
}
