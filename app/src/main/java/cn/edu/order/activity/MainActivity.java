package cn.edu.order.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.order.R;
import cn.edu.order.adapter.ShopAdapter;
import cn.edu.order.bean.ShopBean;
import cn.edu.order.utils.Constants;
import cn.edu.order.utils.JsonParse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rlTitleBar;
    private TextView tvTitle;
    private ListView lvShopList;
    private ShopAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }



    private void initView(){
        rlTitleBar = findViewById(R.id.rl_title_bar);
        tvTitle = findViewById(R.id.tv_title);
        lvShopList = findViewById(R.id.lv_shop_list);
        adapter = new ShopAdapter(this);
        lvShopList.setAdapter(adapter);
        tvTitle.setText("店铺");
        rlTitleBar.setBackgroundColor(getResources().getColor(R.color.blue_color));

    }

    private void initData() {
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder().url(Constants.WEB_SITE+Constants.REQUEST_SHOP_DATA).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.fillInStackTrace());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final List<ShopBean> list = JsonParse.getInstance().getShopList(json);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(list);
                    }
                });
            }
        });
    }
}