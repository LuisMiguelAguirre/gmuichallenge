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

public class ListAdapterElements extends RecyclerView.Adapter<ListAdapterElements.ListViewHolder> {
    private final LayoutInflater mInflater;
    private final List<Element> elements;
    private final LandscapeView landscapeView;
    private  int selectedItem;

    public ListAdapterElements(Context context, List<Element> elements, LandscapeView landscapeView, int elementPosition) {
        mInflater = LayoutInflater.from(context);
        this.elements = elements;
        this.landscapeView = landscapeView;
        this.selectedItem = elementPosition;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mInflater.inflate(R.layout.item, parent, false);

        return new ListViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        String mCurrent = elements.get(position).name;
        holder.itemView.setText(mCurrent);

        holder.itemView.setBackgroundColor(mInflater.getContext().getResources().getColor(R.color.colorAccent));

        if (selectedItem == position) {
            holder.itemView.setBackgroundColor(mInflater.getContext().getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ListAdapterElements listAdapter;
        private TextView itemView;

        public ListViewHolder(@NonNull View itemView, ListAdapterElements listAdapter) {
            super(itemView);
            this.itemView = itemView.findViewById(R.id.item_name);
            this.itemView.setOnClickListener(this);
            this.listAdapter = listAdapter;
        }

        @Override
        public void onClick(View v) {

            int previousItem = selectedItem;
            selectedItem = getAdapterPosition();

            notifyItemChanged(previousItem);
            notifyItemChanged(selectedItem);

            Toast.makeText(v.getContext(), getAdapterPosition() + "", Toast.LENGTH_SHORT).show();
            landscapeView.onClickElementCallBack(getAdapterPosition());

        }
    }
}
