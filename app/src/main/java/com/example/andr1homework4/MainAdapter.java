package com.example.andr1homework4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter <MainAdapter.ItemHolder> {

    public List<Title> list = new ArrayList<>();
    Context context;
    OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;

    }

    public MainAdapter(Context c) {
        context = c;
    }

    @Override
    @NonNull
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_holder, parent, false);
        return new ItemHolder(view);
    }

    public void setList(List<Title> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(int pos, Title title) {
        list.set(pos, title);
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        public TextView txtView;
        public Title title;
        public TextView txtData;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            txtData = itemView.findViewById(R.id.txtData);
            txtView = itemView.findViewById(R.id.txtView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(getAdapterPosition(), title);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showPopup(v,getAdapterPosition());
                    return true;
                }
            });
        }

        public void onBind(Title title) {
            this.title = title;
            txtView.setText(title.name);
            if (title.data != null) {
                txtData.setText(title.data);
            }


        }

        private void showPopup(View view, final int position) {
            PopupMenu popup = new PopupMenu(context, view);
            MenuInflater inflate = popup.getMenuInflater();
            inflate.inflate(R.menu.item_delete, popup.getMenu());

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.delete:
                            list.remove(position);
                            notifyItemRemoved(position);
                            break;
                        default:
                            return false;
                    }
                    return false;
                }
            });
            popup.show();
        }
    }
}
