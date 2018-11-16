package com.liubao.picker.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.liubao.picker.R;

/**
 * * Created by liubao on 2018/11/16.
 */
public class PickerDialogFragment extends DialogFragment {
    private OnViewCreatedListener onViewCreatedListener;
    private OnBtnClickListener onBtnClickListener;

    public void setOnViewCreatedListener(OnViewCreatedListener onViewCreatedListener) {
        this.onViewCreatedListener = onViewCreatedListener;
    }

    public static PickerDialogFragment getInstance() {
        return new PickerDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog);
    }


    private static final float DEFAULT_DIM = 0.6f;

    public float getDimAmount() {
        return DEFAULT_DIM;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(getCancelOutside());
        Window window = dialog.getWindow();
        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = getDimAmount();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        if (getHeight() > 0) {
            params.height = getHeight();
        } else {
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
    }

    public int getHeight() {
        return -1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final PickerLinearLayout pickerLinearLayout = new PickerLinearLayout(getActivity());
        pickerLinearLayout.cancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        pickerLinearLayout.selectTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPedigree model1 = pickerLinearLayout.getFirstSelectedItem();
                IPedigree model2 = pickerLinearLayout.getSecondSelectedItem();
                IPedigree model3 = pickerLinearLayout.getThirdSelectedItem();
                if (onBtnClickListener != null) {
                    onBtnClickListener.onBtnClick(model1, model2, model3);
                }
            }
        });
        if (onViewCreatedListener != null) {
            onViewCreatedListener.onViewCreated(pickerLinearLayout);
        }
        return pickerLinearLayout;
    }

    private boolean getCancelOutside() {
        return true;
    }

    public interface OnViewCreatedListener {
        void onViewCreated(PickerLinearLayout pickerLinearLayout);
    }

    public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
        this.onBtnClickListener = onBtnClickListener;
    }

    public interface OnBtnClickListener {
        void onBtnClick(IPedigree item1, IPedigree item2, IPedigree item3);
    }
}
