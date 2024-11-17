package com.example.frontend.ui.order;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.R;
import com.example.frontend.data.models.Order;
import com.example.frontend.utils.CurrencyUtils;
import com.google.android.material.chip.Chip;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orders;

    OrderAdapter() {
        orders = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderNumber;
        private final Chip statusChip;
        private final TextView totalAmount;
        private final TextView orderDate;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            statusChip = itemView.findViewById(R.id.statusChip);
            totalAmount = itemView.findViewById(R.id.totalAmount);
            orderDate = itemView.findViewById(R.id.orderDate);
        }

        public void bind(Order order) {
            orderNumber.setText(String.valueOf(order.getId()));
            statusChip.setText(order.getStatus());
            statusChip.setChipBackgroundColorResource(getStatusColor(order.getStatus()));
            totalAmount.setText(CurrencyUtils.formatToARCurrency(order.getTotalAmount()));
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy", new Locale("es", "ES"));
                Date date = inputFormat.parse(order.getCreatedAt());
                String formattedDate = outputFormat.format(date);
                orderDate.setText(formattedDate);
            } catch (ParseException e) {
                orderDate.setText(order.getCreatedAt());
            }
        }

        private int getStatusColor(String status) {
            switch (status.toLowerCase()) {
                case "enviado":
                    return R.color.status_shipped;
                case "entregado":
                    return R.color.status_delivered;
                default:
                    return R.color.status_preparation;
            }
        }
    }
}
