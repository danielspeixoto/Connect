package com.danielspeixoto.connect.view.recycler.adapter

/**
 * Created by danielspeixoto on 4/21/17.
 */
/*class BaseAdapter<out H : BaseHolder<*,*>, O>
    (var activity : BaseActivity) :
        RecyclerView.Adapter<H>() {

    private var data : ArrayList<O> = ArrayList()
        get set

    protected fun addItem(o : O) {
        data.add(o)
        notifyDataSetChanged()
    }

    protected fun getItem(position : Int) = data.get(position)

    protected fun removeItem(position : Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    protected fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    protected fun getIterator(): Iterator<O> {
        return data.iterator()
    }

    protected fun goToActivity(clazz: Class<*>) {
        activity.goToActivity(clazz)
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.setItem(data[position] as Any)
        holder.setPosition(position);
        holder.onPostCreated()
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): H {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}*/