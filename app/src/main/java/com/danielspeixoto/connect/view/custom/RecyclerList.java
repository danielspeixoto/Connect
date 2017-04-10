package com.danielspeixoto.connect.view.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.util.App;
import com.danielspeixoto.connect.view.recycler.adapter.BaseAdapter;

/**
 * Created by danielspeixoto on 4/8/17.
 */

public class RecyclerList extends LinearLayout {
	
	private CustomRecycler recycler;
	private LinearLayout emptyView;
	
	public RecyclerList(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		emptyView = new LinearLayout(context);
		recycler = new CustomRecycler(context);
		recycler.setLayoutManager(new LinearLayoutManager(context));
		LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		recycler.setLayoutParams(params);
		TextView textView = new TextView(context);
		textView.setTextSize(24);
		textView.setText(App.getStringResource(R.string.no_items));
		emptyView.setLayoutParams(params);
		emptyView.addView(textView);
		emptyView.setGravity(Gravity.CENTER);
		emptyView.setPadding(16, 16, 16, 16);
		recycler.setEmptyView(emptyView);
		addView(recycler);
		addView(emptyView);
	}
	
	public void setAdapter(BaseAdapter adapter) {
		recycler.setAdapter(adapter);
		recycler.checkIfEmpty();
	}
	
	public void setNestedScrollEnabled(boolean enabled) {
		recycler.setNestedScrollingEnabled(enabled);
	}
	
	private class CustomRecycler extends RecyclerView {
		
		private View emptyView;
		final private AdapterDataObserver observer = new AdapterDataObserver() {
			@Override
			public void onChanged() {
				checkIfEmpty();
			}
			
			@Override
			public void onItemRangeInserted(int positionStart, int itemCount) {
				checkIfEmpty();
			}
			
			@Override
			public void onItemRangeRemoved(int positionStart, int itemCount) {
				checkIfEmpty();
			}
		};
		
		public CustomRecycler(Context context) {
			super(context);
		}
		
		public CustomRecycler(Context context, @Nullable AttributeSet attrs) {
			super(context, attrs);
		}
		
		void checkIfEmpty() {
			if (emptyView != null && getAdapter() != null) {
				final boolean emptyViewVisible = getAdapter().getItemCount() == 0;
				emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
				setVisibility(emptyViewVisible ? GONE : VISIBLE);
			}
		}
		
		@Override
		public void setAdapter(RecyclerView.Adapter adapter) {
			super.setAdapter(adapter);
			adapter.registerAdapterDataObserver(observer);
			checkIfEmpty();
		}
		
		public void setEmptyView(View emptyView) {
			this.emptyView = emptyView;
			checkIfEmpty();
		}
		
		
	}
	
}
