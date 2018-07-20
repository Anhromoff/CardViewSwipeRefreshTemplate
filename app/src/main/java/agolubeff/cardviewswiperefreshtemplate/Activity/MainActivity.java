package agolubeff.cardviewswiperefreshtemplate.Activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import agolubeff.cardviewswiperefreshtemplate.Adapter.MyAdapter;
import agolubeff.cardviewswiperefreshtemplate.R;

public class MainActivity extends AppCompatActivity
{
    private List<String> list;
    RecyclerView recycler_view;
    MyAdapter adapter;
    SwipeRefreshLayout swipe_refresh_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        list = GetList();
        InitRecyclerView();
        InitSwipeRefreshLayout();
    }

    private void InitRecyclerView()
    {
        LinearLayoutManager layout_manager = new LinearLayoutManager(this);
        recycler_view = findViewById(R.id.my_recycler_view);
        adapter = new MyAdapter(this, list);

        recycler_view.setLayoutManager(layout_manager);
        recycler_view.setAdapter(adapter);
    }

    private void InitSwipeRefreshLayout()
    {
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);
        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                RefreshContent();
            }
        });
    }

    private void RefreshContent()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                list = GetList();
                adapter = new MyAdapter(MainActivity.this, list);
                recycler_view.setAdapter(adapter);
                swipe_refresh_layout.setRefreshing(false);
            }
        }, 100);
    }


    private List<String> GetList()
    {
        List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        Collections.shuffle(list);
        return list;
    }
}
