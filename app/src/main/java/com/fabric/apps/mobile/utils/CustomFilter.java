package com.fabric.apps.mobile.utils;

import android.widget.Filter;

import com.fabric.apps.mobile.adapter.TransaksiListPendingAdapter;
import com.fabric.apps.mobile.model.transaksiGetModel.TransactionItemGetModel;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    TransaksiListPendingAdapter adapter;
    ArrayList<TransactionItemGetModel> filterlist;

    public CustomFilter(ArrayList<TransactionItemGetModel> filterlist, TransaksiListPendingAdapter adapter) {
        this.adapter=adapter;
        this.filterlist=filterlist;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();

            ArrayList<TransactionItemGetModel> filterItem = new ArrayList<>();
            for (int i = 0; i < filterItem.size(); i++) {
                if (filterItem.get(i).getStatus().equalsIgnoreCase("Pendding")) {
                    filterItem.add(filterlist.get(i));
                }
            }
            results.count = filterItem.size();
            results.values = filterItem;
        } else {
            results.count = filterlist.size();
            results.values = filterlist;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
//        adapter.transaksi= (ArrayList<TransactionItemGetModel> results.values);

        adapter.notifyDataSetChanged();
    }
}
