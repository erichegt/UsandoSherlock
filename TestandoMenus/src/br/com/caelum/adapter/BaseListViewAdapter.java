package br.com.caelum.adapter;

import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

public abstract class BaseListViewAdapter<T> extends BaseAdapter{
	private List<T> elements;
	private ListView listView;
	private T selected;
	private AfterItemClicked<T> afterClickListener;
	private AfterItemLongClicked<T> afterLongClickListener;

	public BaseListViewAdapter(ListView listView, List<T> elements) {
		this.listView = listView;
		this.elements = elements;
		this.listView.setAdapter(this);
		this.listView.setOnItemClickListener(new MyItemClickListener());
		this.listView.setOnItemLongClickListener(new MyItemLongClickListener());
	}
	
	public BaseListViewAdapter(ListView listView, List<T> elements, AfterItemClicked<T> clickListener) {
		this(listView, elements);
		this.afterClickListener = clickListener;
	}
	
	public BaseListViewAdapter(ListView listView, List<T> elements, AfterItemLongClicked<T> longClickListener) {
		this(listView, elements);
		this.afterLongClickListener = longClickListener;
	}
	
	public BaseListViewAdapter(ListView listView, List<T> elements, AfterItemClicked<T> clickListener, AfterItemLongClicked<T> longClickListener) {
		this(listView, elements);
		this.afterLongClickListener = longClickListener;
	}
	
	public void setAfterClickListener(AfterItemClicked<T> afterClickListener) {
		this.afterClickListener = afterClickListener;
	}
	
	public void setAfterLongClickListener(
			AfterItemLongClicked<T> afterLongClickListener) {
		this.afterLongClickListener = afterLongClickListener;
	}
	
	public T getSelected() {
		return selected;
	}
	
	protected List<T> getElements() {
		return elements;
	}
	
	public void reload(List<T> list) {
		this.elements = list;
		//TODO rola ou precisa limpar a lista e depois adicionar?
		this.notifyDataSetChanged();
	}

	@Override
	public long getItemId(int position) {
		return elements.get(position).hashCode();
	}
	
	@Override
	public int getCount() {
		return elements.size();
	}

	@Override
	public Object getItem(int position) {
		return elements.get(position);
	}
	
	@SuppressWarnings("unchecked")
	private class MyItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
			selected = (T) adapter.getItemAtPosition(position);
			
			if (afterClickListener!=null) {
				afterClickListener.handleClick(selected);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private class MyItemLongClickListener implements OnItemLongClickListener {
		@Override
		public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
			selected = (T) adapter.getItemAtPosition(position);
			
			if (afterLongClickListener != null) {
				afterLongClickListener.handleClick(selected);
			}
			
			return false;
		}
	}
	
	public interface AfterItemClicked<T> {
		void handleClick(T selected);
	}
	
	public interface AfterItemLongClicked<T> {
		void handleClick(T selected);
	}
}


