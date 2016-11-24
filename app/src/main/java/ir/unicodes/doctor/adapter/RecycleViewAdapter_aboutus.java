package ir.unicodes.doctor.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.unicodes.doctor.R;
import ir.unicodes.doctor.objects.Object_Aboutus;


public class RecycleViewAdapter_aboutus extends RecyclerView.Adapter<RecycleViewAdapter_aboutus.ViewHolder> {

    List<Object_Aboutus> ItemsList;
    Typeface San;
    Context mContext;

    public RecycleViewAdapter_aboutus(List<Object_Aboutus> row, Typeface San, Context context) {
        this.ItemsList = row;
        this.San = San;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        CircleImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            txtTitle    = (TextView) itemView.findViewById(R.id.txtTitle);
            img         = (CircleImageView) itemView.findViewById(R.id.imgCircleImage);

            txtTitle.setTypeface(San);

        }// Cunstrator()

    }// end class ViewHolder{}


    @Override
    public int getItemCount() {
        return ItemsList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.row_recycleview_aboutus, viewGroup, false);

        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder RowViewHolder, final int position) {
        RowViewHolder.txtTitle.setText(ItemsList.get(position).getTitle());
        Glide.with(mContext).load(ItemsList.get(position).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                //.placeholder(R.mipmap.ic_launcher)
                .into(RowViewHolder.img);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void add(Object_Aboutus ob) {
        ItemsList.add(ob);
        notifyDataSetChanged();
    }

    public void clear() {
        ItemsList.clear();
        notifyDataSetChanged();
    }
}
