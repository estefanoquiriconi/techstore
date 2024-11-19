package com.example.frontend.ui.order;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frontend.R;
import com.example.frontend.data.models.OrderDetail;
import com.example.frontend.utils.CurrencyUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetail detail = orderDetails.get(position);
        holder.bind(detail);
    }

    @Override
    public int getItemCount() {
        if (orderDetails != null && !orderDetails.isEmpty()) return orderDetails.size();
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvProductName;
        private final TextView tvQuantity;
        private final TextView tvPrice;
        private final TextView tvSubtotal;
        private final ImageView ivProductImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
        }

        public void bind(OrderDetail detail) {
            tvProductName.setText(detail.getProduct().getName());
            String quantityStr = "Cantidad: ".concat(String.valueOf(detail.getQuantity()));
            tvQuantity.setText(quantityStr);
            tvPrice.setText(String.format("Precio: %s", CurrencyUtils.formatToARCurrency(Double.parseDouble(detail.getPrice()))));
            tvSubtotal.setText(String.format("Subtotal: %s", CurrencyUtils.formatToARCurrency(Double.parseDouble(detail.getSubtotal()))));

            Glide.with(itemView.getContext())
                    .load(detail.getProduct().getImageUrl())
                    .into(ivProductImage);
        }
    }
}