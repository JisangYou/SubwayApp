package orgs.androidtown.subwaypractice2.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import orgs.androidtown.subwaypractice2.R;
import orgs.androidtown.subwaypractice2.model.RealtimeArrivalList;

/**
 * Created by Jisang on 2017-10-23.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    Context context;
    List<RealtimeArrivalList> data;

    public RecyclerAdapter(Context context, List<RealtimeArrivalList> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_list_item, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        RealtimeArrivalList item = data.get(position);
        holder.lineTrains.setText(item.getBstatnNm());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView lineTrains;

        public Holder(View itemView) {
            super(itemView);

            lineTrains = itemView.findViewById(R.id.linetrains);
        }
    }
}
