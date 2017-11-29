package com.itheima.takeout.presenter;

import com.itheima.takeout.MyApplication;
import com.itheima.takeout.model.dao.DBHelper;
import com.itheima.takeout.model.net.api.ResponseInfoAPI;
import com.itheima.takeout.model.net.bean.ResponseInfo;
import com.itheima.takeout.utils.Constant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 通用的处理业务操作
 */
public abstract class BasePresenter {
    // 联网工作的管理和数据库管理

    // 联网
    protected Retrofit retrofit;
    protected ResponseInfoAPI responseInfoAPI;

    // 数据库
    protected DBHelper helper;

    public BasePresenter(){
        retrofit = new Retrofit.Builder().
                baseUrl(Constant.HOME).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        responseInfoAPI = retrofit.create(ResponseInfoAPI.class);

        // 获取上下文
        // 问题：如果上下文对应的是某个Activity或Fragment，生命周期过短
        // 此处设置的上下文需要有较长的生命周期
        helper=DBHelper.getInstance(MyApplication.getInstance());
    }

    protected class CallbackAdapter implements Callback<ResponseInfo>{

        private HashMap<String ,String> errorInfo;

        public CallbackAdapter() {
            errorInfo=new HashMap<>();
            errorInfo.put("5","");
        }

        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
            ResponseInfo body = response.body();
            if("0".equals(body.getCode())) {
                // 服务器处理成功，可以解析data数据了
                parserData(body.getData());
            }else{
                String error=errorInfo.get(body.getCode());
                onFailure(call,new RuntimeException(error));
            }
        }

        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {
            // 我们该如何区分异常，其他类型异常（如：网络有问题） 和 服务器处理请求失败的异常（如：登陆时，输入的用户名密码有误）
            // 我们需要创建一个自己的异常类（MyException，偷懒的话使用RuntimeException），当服务器处理失败时，通过该异常包装显示数据
            if(t instanceof RuntimeException)
            {
                failed(((RuntimeException)t).getMessage());
            }

            failed("服务器忙，请稍后重试……");

        }
    }

    /**
     * 服务器处理失败时需要将错误信息提示给用户（如：用户名密码有错误）
     * @param message
     */
    protected abstract void failed(String message);

    /**
     * 解析服务器回复数据
     * @param data
     */
//    protected void parseDestInfo(String data) {
//    }
    protected abstract void parserData(String data);


}
