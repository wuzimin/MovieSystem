package com.me.movieticket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.me.movieticket.R;
import com.me.movieticket.entity.AbstractItem;
import com.me.movieticket.entity.CenterItem;
import com.me.movieticket.entity.EdgeItem;
import com.me.movieticket.interfaces.OnSeatSelected;

import java.util.List;

/**
 * Created by qwtangwenqiang on 2016/6/6.
 */
public class AirplaneAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {
    private OnSeatSelected mOnSeatSelected;

    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;
        private final ImageView imgSeatBooked;


        public EdgeViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            imgSeatBooked = (ImageView) itemView.findViewById(R.id.img_seat_booked);
        }

    }

    private static class CenterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;
        private final ImageView imgSeatBooked;

        public CenterViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            imgSeatBooked = (ImageView) itemView.findViewById(R.id.img_seat_booked);
        }

    }

    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<AbstractItem> mItems;

    public AirplaneAdapter(Context context, List<AbstractItem> items) {
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new CenterViewHolder(itemView);
        } else if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new EdgeViewHolder(itemView);
        } else {
            View itemView = new View(mContext);
            return new EmptyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = mItems.get(position).getType();
        if (type == AbstractItem.TYPE_CENTER) {
            final CenterItem item = (CenterItem) mItems.get(position);
            CenterViewHolder holder = (CenterViewHolder) viewHolder;

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean isSelect = mItems.get(position).getIsOrdered();
                    if (isSelect == true) return;
                    toggleSelection(position);
                    if (getSelectedItemCount() > 4) {
                        Toast.makeText(mContext, "每人最多能订四张票", Toast.LENGTH_SHORT).show();
                        toggleSelection(position);
                    }
                    mOnSeatSelected.onSeatSelected(getSelectedItemCount(), getSelectedItems());
                }
            });
            Boolean isSelect = mItems.get(position).getIsOrdered();
            if (isSelect == true) {
                holder.imgSeatBooked.setVisibility(View.VISIBLE);
                return;
            }
            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

        } else if (type == AbstractItem.TYPE_EDGE) {
            final EdgeItem item = (EdgeItem) mItems.get(position);
            EdgeViewHolder holder = (EdgeViewHolder) viewHolder;

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean isSelect = mItems.get(position).getIsOrdered();
                    if (isSelect == true) return;
                    toggleSelection(position);
                    if (getSelectedItemCount() > 4) {
                        Toast.makeText(mContext, "每人最多能订四张票", Toast.LENGTH_SHORT).show();
                        toggleSelection(position);
                    }
                    mOnSeatSelected.onSeatSelected(getSelectedItemCount(), getSelectedItems());
                }
            });
            Boolean isSelect = mItems.get(position).getIsOrdered();
            if (isSelect == true) {
                holder.imgSeatBooked.setVisibility(View.VISIBLE);
                return;
            }
            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
