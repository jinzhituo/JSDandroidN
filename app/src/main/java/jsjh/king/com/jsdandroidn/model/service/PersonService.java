package jsjh.king.com.jsdandroidn.model.service;

import java.util.List;

import io.reactivex.Observable;
import jsjh.king.com.salesmanapp.base.BaseEntity;
import jsjh.king.com.salesmanapp.model.bean.AddressBookBean;
import jsjh.king.com.salesmanapp.model.bean.UserBean;
import jsjh.king.com.salesmanapp.model.bean.WorkSinginBean;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ShaoGeng on 2018/5/25.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public interface PersonService {

    /*
    * 登入接口 POST
    * */
    @POST("sales/logins")
    Observable<BaseEntity<UserBean>> logins (@Body RequestBody requestBody);

    /*
    * 打卡接口 POST
    * */
    @POST("sales/signin")
    Observable<BaseEntity<WorkSinginBean>> signin (@Body RequestBody requestBody);

    /*
    * 公司通讯录接口 POST
    * */
    @POST("sales/maillist")
    Observable<BaseEntity<List<AddressBookBean>>> maillist (@Body RequestBody requestBody);
}
