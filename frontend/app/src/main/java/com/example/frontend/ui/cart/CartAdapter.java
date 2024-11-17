package com.example.frontend.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frontend.R;
import com.example.frontend.utils.CurrencyUtils;


public class CartAdapter extends ListAdapter<CartViewModel.CartItem, CartAdapter.CartViewHolder> {
    private final CartItemListener listener;

    public interface CartItemListener {
        void onQuantityChanged(int productId, int newQuantity);

        void onRemoveItem(int productId);
    }

    protected CartAdapter(CartItemListener listener) {
        super(new DiffUtil.ItemCallback<CartViewModel.CartItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull CartViewModel.CartItem oldItem,
                                           @NonNull CartViewModel.CartItem newItem) {
                return oldItem.product.getId() == newItem.product.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull CartViewModel.CartItem oldItem,
                                              @NonNull CartViewModel.CartItem newItem) {
                return oldItem.product.getId() == newItem.product.getId() &&
                        oldItem.quantity == newItem.quantity;
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivProduct;
        private final TextView tvProductName;
        private final TextView tvPrice;
        private final TextView tvQuantity;
        private final ImageButton btnDecrease;
        private final ImageButton btnIncrease;
        private final ImageButton btnRemove;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }

        void bind(CartViewModel.CartItem item) {
            tvProductName.setText(item.product.getName());
            tvPrice.setText(CurrencyUtils.formatToARCurrency(Double.parseDouble(item.product.getPrice())));
            tvQuantity.setText(String.valueOf(item.quantity));

            Glide.with(itemView.getContext())
                    .load(item.product.getImageUrl())
                    .into(ivProduct);

            btnDecrease.setOnClickListener(v ->
                    listener.onQuantityChanged(item.product.getId(), item.quantity - 1));

            btnIncrease.setOnClickListener(v ->
                    listener.onQuantityChanged(item.product.getId(), item.quantity + 1));

            btnRemove.setOnClickListener(v ->
                    listener.onRemoveItem(item.product.getId()));
        }
    }
}