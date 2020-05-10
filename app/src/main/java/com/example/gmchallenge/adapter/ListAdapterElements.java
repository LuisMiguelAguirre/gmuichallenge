package com.example.gmchallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmchallenge.R;
import com.example.gmchallenge.model.Element;
import com.example.gmchallenge.view.ElementCallback;

import java.util.List;

public class ListAdapterElements extends RecyclerView.Adapter<ListAdapterElements.ListViewHolder> {
    private final LayoutInflater mInflater;
    private final List<Element> elements;
    private final ElementCallback elementCallback;
    private int selectedItem;

    public ListAdapterElements(Context context, List<Element> elements, int initialElementPosition, ElementCallback elementCallback) {
        mInflater = LayoutInflater.from(context);
        this.elements = elements;
        this.selectedItem = initialElementPosition;
        this.elementCallback = elementCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item, parent, false);
        return new ListViewHolder(mItemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        String mCurrent = elements.get(position).name;
        holder.itemView.setText(mCurrent);

        holder.itemView.setTextColor(mInflater.getContext().getResources().getColor(R.color.rowUnselected));

        if (selectedItem == position) {
            holder.itemView.setTextColor(mInflater.getContext().getResources().getColor(R.color.rowSelected));
        }
    }

    @Override
    public int getItemCount() {
        return elements.size();
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
            int previousItem = selectedItem;
            selectedItem = getAdapterPosition();

            notifyItemChanged(previousItem);
            notifyItemChanged(selectedItem);

            Toast.makeText(v.getContext(), "Element " + (getAdapterPosition() + 1), Toast.LENGTH_SHORT).show();
            elementCallback.onClickElementCallBack(getAdapterPosition());
        }
    }
}
