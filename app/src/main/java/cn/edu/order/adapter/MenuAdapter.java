package cn.edu.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.edu.order.R;
import cn.edu.order.bean.FoodBean;

public class MenuAdapter extends BaseAdapter {

    private Context context;
    private List<FoodBean> beans = new ArrayList<>();

    public MenuAdapter (Context context){
        this.context = context;
    }

    public  void setData(List<FoodBean> beans){
        this.beans.clear();
        this.beans.addAll(beans);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.menu_item,null);
            vh = new ViewHolder();
            vh.tvFoodName = convertView.findViewById(R.id.tv_food_name);
            vh.tvTaste = convertView.findViewById(R.id.tv_taste);
            vh.tvSaleNum = convertView.findViewById(R.id.tv_sale_num);
            vh.tvPrice = convertView.findViewById(R.id.tv_price);
            vh.btnAddCar = convertView.findViewById(R.id.btn_add_car);
            vh.tvFoodPic = convertView.findViewById(R.id.tv_food_pic);

            convertView.setTag(vh);

        }else {
            vh = (ViewHolder) convertView.getTag();

        }
        FoodBean foodBean = beans.get(position);
        if(foodBean != null){
            vh.tvFoodName.setText(foodBean.getFoodName());
            Glide.with(context).load(foodBean.getFoodPic()).error(R.mipmap.ic_launcher)
                    .into(vh.tvFoodPic);
            vh.tvPrice.setText("￥"+foodBean.getPrice());
            vh.tvSaleNum.setText("月售"+foodBean.getSaleNum());
            vh.tvTaste.setText(foodBean.getTaste());
        }


        return convertView;
    }

    class ViewHolder{
        public TextView tvFoodName,tvTaste,tvSaleNum,tvPrice;
        public ImageView tvFoodPic;
        public Button btnAddCar;

    }
}
