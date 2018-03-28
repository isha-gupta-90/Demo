package test.learn.com.demo.ui;


import android.content.Context;
import android.demo.com.demo.R;
import test.learn.com.demo.data.model.Row;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Row> responses;
    private Context context;

    public RecyclerAdapter(List<Row> responses, Context context) {
        this.responses = responses;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        ButterKnife.bind(this,v);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        System.out.println(",,,xx,,"+ responses.get(position).getImageHref());

        if (null!=responses.get(position).getImageHref()) {
            String imageUrl = responses.get(position).getImageHref();

            //add https if required manually

//            Picasso.with(context).setLoggingEnabled(true);

            Picasso.with(context). load(imageUrl). noFade().fit().centerInside().
                        error(R.drawable.ic_launcher_background).into(holder.imageView);
        }
        holder.descTv.setText(responses.get(position).getDescription());
        holder.titleTv.setText(responses.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.descTv)
        protected TextView descTv;

        @BindView(R.id.imageView)
        protected ImageView imageView;

        @BindView(R.id.titleTv)
        protected TextView titleTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
