package com.danielspeixoto.connect.view.recycler.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.danielspeixoto.connect.view.activity.BaseActivity;
import com.danielspeixoto.connect.view.recycler.holder.BaseHolder;

import java.util.ArrayList;
import java.util.Iterator;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by danielspeixoto on 17/11/16.
 */
public abstract class BaseAdapter<H extends BaseHolder, O>
		extends RecyclerView.Adapter<H> {

    @Getter
    protected BaseActivity activity;
    @Getter
    @Setter
    private ArrayList<O> data = new ArrayList<>();
    protected RecyclerView mRecyclerView;

    public BaseAdapter(BaseActivity activity) {
        this.activity = activity;
    }

    // It's public because it is used in interfaces
    public void addItem(O o) {
        data.add(o);
        notifyDataSetChanged();
    }
	
	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
		mRecyclerView = recyclerView;
	}
	
	protected final O getItem(int position) {
        return data.get(position);
    }
    
    protected final void removeItem(int position) {
        data.remove(position);
        notifyDataSetChanged();
    }
    
    protected final void clearData() {
        data.clear();
        notifyDataSetChanged();
    }
    
    @NonNull
    protected final Iterator<O> getIterator() {
        return data.iterator();
    }

    public abstract void getItems();

    @Override
    public void onBindViewHolder(H holder, int position) {
        holder.setItem(data.get(position));
        holder.setPosition(position);
        holder.onPostCreated();
    }

    @Override
    public abstract H onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void goToActivity(Class clazz) {
        getActivity().goToActivity(clazz);
    }
}
