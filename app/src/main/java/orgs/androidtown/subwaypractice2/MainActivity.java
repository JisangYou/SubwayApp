package orgs.androidtown.subwaypractice2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import orgs.androidtown.subwaypractice2.Adapter.ViewPagerAdapter;
import orgs.androidtown.subwaypractice2.model.RealtimeArrivalList;
import orgs.androidtown.subwaypractice2.model.Subway;
import orgs.androidtown.subwaypractice2.network.Remote;


public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    List<RealtimeArrivalList> realtimeArrivalLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();

        initTabs();
        initListener();

        load("http://swopenapi.seoul.go.kr/api/subway/624b4e4a746a697334364845756567/json/realtimeStationArrival/0/10/%EC%84%9C%EC%9A%B8");

    }


    private void load(String url) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... args) {
                String param1 = args[0];
                String str = Remote.getData(param1);
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                realtimeArrivalLists = parse(s);
                Log.d("realtimeArrivalLists", realtimeArrivalLists + "데이터가 잘 들어오나?");
                setViewPager();

            }
        }.execute(url);
    }

    private List<RealtimeArrivalList> parse(String str) {
        Gson gson = new Gson();
        Subway subway = gson.fromJson(str, Subway.class);
        RealtimeArrivalList Row[] = subway.getRealtimeArrivalList();
        List<RealtimeArrivalList> result = Arrays.asList(Row);
        return result;
    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);

    }

    private void setViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(this, realtimeArrivalLists);
        viewPager.setAdapter(adapter);

    }

    private void initTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("1호선"));
        tabLayout.addTab(tabLayout.newTab().setText("2호선"));
        tabLayout.addTab(tabLayout.newTab().setText("3호선"));
        tabLayout.addTab(tabLayout.newTab().setText("4호선"));
        tabLayout.addTab(tabLayout.newTab().setText("5호선"));
    }

    private void initListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


}