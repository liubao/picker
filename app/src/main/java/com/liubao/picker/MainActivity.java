package com.liubao.picker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liubao.picker.data.PickDateModel;
import com.liubao.picker.widget.IPedigree;
import com.liubao.picker.widget.PickerDialogFragment;
import com.liubao.picker.widget.PickerLinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public void showDialog() {
        PickerDialogFragment pickerDialogFragment = new PickerDialogFragment();
        pickerDialogFragment.setOnViewCreatedListener(new PickerDialogFragment.OnViewCreatedListener() {
            @Override
            public void onViewCreated(PickerLinearLayout pickerLinearLayout) {
                PickDateModel pickDateModel = new PickDateModel(2000, 1, 1
                        , 2018, 1, 1);
                pickerLinearLayout.setData(pickDateModel.data);
            }
        });
        pickerDialogFragment.setOnBtnClickListener(new PickerDialogFragment.OnBtnClickListener() {
            @Override
            public void onBtnClick(IPedigree item1, IPedigree item2, IPedigree item3) {

            }
        });
        pickerDialogFragment.show(getSupportFragmentManager(), "");
    }
}
