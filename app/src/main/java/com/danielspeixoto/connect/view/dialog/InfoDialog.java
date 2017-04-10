package com.danielspeixoto.connect.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.danielspeixoto.connect.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by danielspeixoto on 3/15/17.
 */

public class InfoDialog extends Dialog {

    @BindView(R.id.infoText)
    TextView infoText;

    public InfoDialog(Context context) {
        super(context);
        this.setContentView(R.layout.info_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.closeButton)
    public void close() {
        dismiss();
    }

    public void setInfoText(String text) {
        infoText.setText(text);
    }
}

