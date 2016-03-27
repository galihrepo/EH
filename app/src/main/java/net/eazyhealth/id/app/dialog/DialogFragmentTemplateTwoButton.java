package net.eazyhealth.id.app.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.custom.CustomTextView;
import net.eazyhealth.id.app.interfaces.OnDialogTemplateTwoButton;

/**
 * Created by GALIH ADITYO on 3/26/2016.
 */
public class DialogFragmentTemplateTwoButton extends DialogFragment implements View.OnClickListener {
    private CustomTextView tvTitle;
    private CustomTextView tvInfo;
    private CustomTextView btnNo;
    private CustomTextView btnYes;

    private String title;
    private String content;
    private String strBtnNo;
    private String strBtnYes;

    private OnDialogTemplateTwoButton listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_template_two_button, container, false);

        tvTitle = (CustomTextView) rootView.findViewById(R.id.title_tv);
        tvInfo = (CustomTextView) rootView.findViewById(R.id.content_tv);
        btnNo = (CustomTextView) rootView.findViewById(R.id.cancel_btn);
        btnYes = (CustomTextView) rootView.findViewById(R.id.ok_btn);

        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);

        tvTitle.setText(Html.fromHtml(title));
        tvInfo.setText(Html.fromHtml(content));
        btnNo.setText(strBtnNo);
        btnYes.setText(strBtnYes);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (v == btnNo) {
            listener.onTemplateDialogNo();
        } else if (v == btnYes) {
            listener.onTemplateDialogYes();
        }
    }

    public void setListener(OnDialogTemplateTwoButton listener) {
        this.listener = listener;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setButtonNoText(String strBtnNo) {
        this.strBtnNo = strBtnNo;
    }

    public void setButtonYesText(String strBtnYes) {
        this.strBtnYes = strBtnYes;
    }

}
