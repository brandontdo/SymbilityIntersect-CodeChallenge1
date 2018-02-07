package brandondo.cryptocharts;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import brandondo.cryptocharts.Models.CryptoCurrency;

import static android.view.View.GONE;

public class CryptoRecyclerAdapter extends RecyclerView.Adapter<CryptoRecyclerAdapter.CryptoViewHolder> {
    private List<CryptoCurrency> data;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CryptoViewHolder extends RecyclerView.ViewHolder {
        TextView currencyName;
        TextView currencyPrice;
        ImageView favouritedStar;
        ImageView notFavouritedStar;
        FrameLayout favouriteButton;

        public CryptoViewHolder(View view) {
            super(view);
            currencyName = (TextView) view.findViewById(R.id.currency_name);
            currencyPrice = (TextView) view.findViewById(R.id.currency_price);
            favouritedStar = (ImageView) view.findViewById(R.id.filled_star);
            notFavouritedStar = (ImageView) view.findViewById(R.id.unfilled_star);
            favouriteButton = (FrameLayout) view.findViewById(R.id.favourite_button);
            favouriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CryptoProvider.getInstance()
                            .getCurrencyData()
                            .get(getAdapterPosition())
                            .toggleFavourited();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CryptoRecyclerAdapter(List<CryptoCurrency> data) {
        this.data = data;
        Log.d("adapter data", "" + data.size());
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CryptoRecyclerAdapter.CryptoViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_crypto_currency, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CryptoViewHolder viewHolder = new CryptoViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CryptoViewHolder viewHolder, int position) {
        CryptoCurrency currency = data.get(position);
        String currencyName = currency.getCoinName();
        Double currencyPrice = currency.getPrice();
        boolean isFavourited = currency.isFavourited();

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        viewHolder.currencyName.setText(currencyName);
        if (currencyPrice != null) {
            viewHolder.currencyPrice.setText(String.valueOf(currencyPrice));
        }

        if(isFavourited) {
            viewHolder.notFavouritedStar.setVisibility(GONE);
            viewHolder.favouritedStar.setVisibility(View.VISIBLE);
        } else {
            viewHolder.favouritedStar.setVisibility(GONE);
            viewHolder.notFavouritedStar.setVisibility(View.VISIBLE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }
}