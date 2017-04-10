package com.danielspeixoto.connect.view.recycler.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.danielspeixoto.connect.view.activity.BaseActivity;
import com.danielspeixoto.connect.view.recycler.adapter.BaseAdapter;

import butterknife.ButterKnife;
import lombok.Setter;

/**
 * Created by danielspeixoto on 20/11/16.
 */
    public abstract class BaseHolder<A extends BaseAdapter, O> extends RecyclerView.ViewHolder {

    protected static final String EMPTY_STRING = "";

    protected A mAdapter;
    protected O mItem;
    @Setter
    protected int position;

    public BaseHolder(View itemView, A mAdapter) {
        super(itemView);
        this.mAdapter = mAdapter;
        ButterKnife.bind(this, itemView);
    }

    public void setItem(Object object) {
        mItem = (O) object;
    }

    protected boolean checkTextEmpty(EditText editText) {
        return editText.getText().toString().equals(EMPTY_STRING);
    }

    protected String getText(TextView textView) {
        return textView.getText().toString();
    }

    public abstract void onPostCreated();

    protected void clear(EditText editText) {
        editText.setText(EMPTY_STRING);
    }

    public BaseActivity getActivity() {
        return mAdapter.getActivity();
    }

    public void goToActivity(Class clazz) {
        mAdapter.getActivity().goToActivity(clazz);
    }
}
