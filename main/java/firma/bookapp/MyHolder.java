package firma.bookapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView img;
    TextView nameTxt,posTxt,tarih,adres;
    ItemClickListener ıtemClickListener;
    CardView cardView;

    public MyHolder(View itemView) {
        super(itemView);

        img= (ImageView) itemView.findViewById(R.id.image);
        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        posTxt = (TextView) itemView.findViewById(R.id.adresad);
        adres = (TextView) itemView.findViewById(R.id.adres);
        tarih = (TextView) itemView.findViewById(R.id.tarih);
        cardView = (CardView) itemView.findViewById(R.id.card_view1);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.ıtemClickListener.onItemClick(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.ıtemClickListener=ic;
    }
}