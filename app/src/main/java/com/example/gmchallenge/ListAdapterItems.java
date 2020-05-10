package com.example.gmchallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapterItems extends RecyclerView.Adapter<ListAdapterItems.ListViewHolder> {
    private final LayoutInflater mInflater;
    private final ItemCallback itemCallback;
    private List<Item> items;
    private  int selectedItem;


    public ListAdapterItems(Context context, List<Item> items, int initialItemPosition, ItemCallback itemCallback) {
        mInflater = LayoutInflater.from(context);
        this.items = items;
        this.selectedItem = initialItemPosition;
        this.itemCallback = itemCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mInflater.inflate(R.layout.item, parent, false);

        return new ListViewHolder(mItemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        String mCurrent = items.get(position).name;
        holder.itemView.setText(mCurrent);

        holder.itemView.setBackgroundColor(mInflater.getContext().getResources().getColor(R.color.colorAccent));

        if (selectedItem == position) {
            holder.itemView.setBackgroundColor(mInflater.getContext().getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
        selectedItem = 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView itemView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView.findViewById(R.id.item_name);
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(v.getContext(), getAdapterPosition() + "", Toast.LENGTH_SHORT).show();

            int previousItem = selectedItem;
            selectedItem = getAdapterPosition();

            notifyItemChanged(previousItem);
            notifyItemChanged(selectedItem);
            itemCallback.onClickItemCallBack(getAdapterPosition());
        }
    }
}
