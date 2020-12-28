package cn.edu.order.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import cn.edu.order.R;
import cn.edu.order.adapter.MenuAdapter;
import cn.edu.order.bean.ShopBean;

public class ShopDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvShopName,tvTime,tvNotice,tvTitle;
    private ImageView ivShopPic,ivBack;
    private ListView lvList;
    private MenuAdapter adapter;
    private ShopBean bean;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定布局文件 把布局文件当作这个activuty界面
        setContentView(R.layout.activity_shop_detail);

        bean = (ShopBean) getIntent().getSerializableExtra("shop");
        if(bean == null){
            return;
        }


        initView();
        initAdapter();
        setData();

    }

    private void initAdapter() {
        adapter = new MenuAdapter(this);
        lvList.setAdapter(adapter);
    }


    private void initView() {
        tvShopName = findViewById(R.id.tv_shop_name);
        tvTime = findViewById(R.id.tv_time);
        tvNotice = findViewById(R.id.tv_notice);
        tvTitle = findViewById(R.id.tv_title);
        ivShopPic = findViewById(R.id.iv_shop_pic);
        ivBack = findViewById(R.id.iv_back);
        lvList = findViewById(R.id.lv_list);

        tvTitle.setText("店铺详情");
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
    }

    private void setData() {
        tvShopName.setText(bean.getShopName());
        tvTime.setText(bean.getTime());
        tvNotice.setText(bean.getShopNotice());
        Glide.with(this).load(bean.getShopPic())
                .error(R.mipmap.ic_launcher)
                .into(ivShopPic);
        adapter.setData(bean.getFoodList());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
