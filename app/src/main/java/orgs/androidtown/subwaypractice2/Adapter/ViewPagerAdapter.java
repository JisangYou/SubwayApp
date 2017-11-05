package orgs.androidtown.subwaypractice2.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import orgs.androidtown.subwaypractice2.R;
import orgs.androidtown.subwaypractice2.model.RealtimeArrivalList;

/**
 * Created by Jisang on 2017-10-23.
 */

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
   List<RealtimeArrivalList> realtimeArrivalLists;
    RecyclerView recyclerView;


    public ViewPagerAdapter(Context context, List<RealtimeArrivalList> realtimeArrivalLists) {
        this.context = context;
        this.realtimeArrivalLists = realtimeArrivalLists;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewpager_list, null);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerAdapter adapter= new RecyclerAdapter(context, realtimeArrivalLists);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return realtimeArrivalLists.size(); // 이부분이 데이터 정제가 필요한 부분?
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View)object);
    }
}
