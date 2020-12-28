package cn.edu.order.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.edu.order.bean.ShopBean;

public class JsonParse {
    //3.定义一个静态对象
    private static JsonParse instance;

    //1.将构造方法单例化,让别人没法new
    private JsonParse(){}

    //2.提供一个方法获取实例
    public synchronized  static JsonParse getInstance(){
        if (instance == null){
            instance = new JsonParse();
        }
        return instance;
    }

    public List<ShopBean> getShopList(String json){
        return new Gson().fromJson(json,new TypeToken<List<ShopBean>>(){}.getType());
    }
}
