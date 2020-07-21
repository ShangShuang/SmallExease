package com.example.smallexease;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smallexease.adapter.ListAdapter;
import com.example.smallexease.bean.CollectionBean;
import com.example.smallexease.bean.ListBean;
import com.example.smallexease.presenter.ImpListPresenter;
import com.example.smallexease.utils.DbHelpter;
import com.example.smallexease.utils.SharedPrefence;
import com.example.smallexease.view.CallView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CallView {

    private Toolbar tool_bar;
    private RecyclerView rv;
    private ImpListPresenter presenter;
    private ListAdapter adapter;
    private ArrayList<ListBean.DatasBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new ImpListPresenter(this);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onItemClick(int position) {
                ListBean.DatasBean datasBean = list.get(position);
                CollectionBean collectionBean = new CollectionBean();
                collectionBean.setId((long) position);
                collectionBean.setAuthor(datasBean.getAuthor());
                collectionBean.setPic(datasBean.getThumbnail());
                collectionBean.setTitle(datasBean.getTitle());
                long insert = DbHelpter.getInstance().insert(collectionBean);
                if (insert >= 0) {
                    SharedPrefence.setParam(MainActivity.this, position + "", true);
                    /*SharedPreferences sp = getSharedPreferences("a", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("b", true);
                    edit.commit();*/
                    Toast.makeText(MainActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                } else {
                    DbHelpter.getInstance().delete(collectionBean);
                    SharedPrefence.setParam(MainActivity.this, position + "", false);
                    /*SharedPreferences sp = getSharedPreferences("a", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("b", false);
                    edit.commit();*/
                    Toast.makeText(MainActivity.this, "取消关注", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initData() {
        presenter.getData();
    }

    private void initView() {
        tool_bar = (Toolbar) findViewById(R.id.tool_bar);
        rv = (RecyclerView) findViewById(R.id.rv);

        setSupportActionBar(tool_bar);
        tool_bar.setTitle("列表");
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        list = new ArrayList<>();
        adapter = new ListAdapter(this, list);
        rv.setAdapter(adapter);
    }

    @Override
    public void onSuccess(ListBean listBean) {
        String json = new Gson().toJson(listBean);
        Log.i("tag", json);
        list.addAll(listBean.getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
