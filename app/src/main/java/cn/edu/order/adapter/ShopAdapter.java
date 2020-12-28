package cn.edu.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.edu.order.R;
import cn.edu.order.activity.ShopDetailActivity;
import cn.edu.order.bean.ShopBean;

public class ShopAdapter extends BaseAdapter {
    private Context context;
    private List<ShopBean> data = new ArrayList<>();

    public ShopAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ShopBean> data) {
        this.data.clear();
        this.data.addAll(data); //不能改变this.data的地址，否则会导致无法刷新数据
        //数据有变化要让我们的数据刷新
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.shop_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tvOfferPrice = convertView.findViewById(R.id.tv_offer_price);
            viewHolder.tvShopName = convertView.findViewById(R.id.tv_shop_name);
            viewHolder.tvSaleNum = convertView.findViewById(R.id.tv_sale_num);
            viewHolder.tvWelfare = convertView.findViewById(R.id.tv_welfare);
            viewHolder.tvTime = convertView.findViewById(R.id.tv_time);
            viewHolder.ivShopPic = convertView.findViewById(R.id.iv_shop_Pic);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ShopBean shopBean = data.get(position);
        Glide.with(context).load(shopBean.getShopPic()).error(R.mipmap.ic_launcher).into(viewHolder.ivShopPic);
        viewHolder.tvTime.setText(shopBean.getTime());
        viewHolder.tvWelfare.setText(shopBean.getWelfare());
        viewHolder.tvSaleNum.setText("月售"+shopBean.getSaleNum());
        viewHolder.tvShopName.setText(shopBean.getShopName());
        viewHolder.tvOfferPrice.setText("起送￥"+shopBean.getOfferPrice()+"配送￥"+shopBean.getDistributionCost());
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ShopDetailActivity.class);
                intent.putExtra("shop",shopBean);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView tvShopName,tvSaleNum,tvOfferPrice,tvWelfare,tvTime;
        ImageView ivShopPic;
    }

}
