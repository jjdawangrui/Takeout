package com.itheima.takeout.model.net.api;



import com.itheima.takeout.model.net.bean.ResponseInfo;
import com.itheima.takeout.utils.Constant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Teacher on 2016/9/2.
 */
public interface ResponseInfoAPI {
    /**
     * 1.@GET 注解描述 API 请求的方式、、、、
     * 2.@GET(Constant.LOGIN) 注解中的参数描述请求 URL 中的路径
     * 3.@Query 注解描述请求 URL 中的参数
     * 方法必须返回 Call<ResponseInfo> 类型对象，其中范型是返回的 json 对应的 JavaBean 类
     *
     * 1.1 @Path - 替换路径上的参数
     * 1.2. @Query - 添加查询参数
     * 1.3. @QueryMap - 把多个查询参数组织成Map
     */

    @GET(Constant.LOGIN)
    Call<ResponseInfo> login(@Query("username") String username, @Query("password") String password);

    @GET(Constant.HOME_URL)
    Call<ResponseInfo> getHomeInfo();

    @GET(Constant.LOGIN)
    Call<ResponseInfo> login(@QueryMap HashMap<String,String> map);

    @GET(Constant.ORDER)
    Call<ResponseInfo> order(@Query("userId") int id);


    @GET(Constant.ADDRESS)
    Call<ResponseInfo> address(@Query("userId") int userId);

}
