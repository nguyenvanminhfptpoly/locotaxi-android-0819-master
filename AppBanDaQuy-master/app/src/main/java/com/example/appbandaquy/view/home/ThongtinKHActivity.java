package com.example.appbandaquy.view.home;

import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbandaquy.R;
import com.example.appbandaquy.Service.APIUltil;
import com.example.appbandaquy.Service.DataClient;
import com.example.appbandaquy.model.home.Chitietdonhang;
import com.example.appbandaquy.model.home.Khachhang;
import com.example.appbandaquy.view.signin.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongtinKHActivity extends AppCompatActivity {

    private TextView mTextView11;
    private EditText mEditText;
    private EditText mEditText2;
    private EditText mEditText3;
    private Button mButton;
    private BottomAppBar mBtnThongtinkh;
    private FloatingActionButton mFabGiohang;
    private DataClient  dataClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_kh);
        Khaibao();
        PostThongtinKH();

    }
    private void Khaibao(){
        mTextView11 = findViewById(R.id.textView11);
        mEditText = findViewById(R.id.editText);
        mEditText2 = findViewById(R.id.editText2);
        mEditText3 = findViewById(R.id.editText3);
        mButton = findViewById(R.id.button);
        mBtnThongtinkh = findViewById(R.id.btn_thongtinkh);
        mFabGiohang = findViewById(R.id.fab_thongtinkh);
        mBtnThongtinkh.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

    }
    private void PostThongtinKH(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenkh = mEditText.getText().toString();
                String sdt = mEditText2.getText().toString();
                String email = mEditText3.getText().toString();


                APIUltil.getData().thongtinkh(tenkh,sdt,email).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> madonhang) {
                        Log.d("///", madonhang.toString());
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < HomeActivity.manggiohang.size(); i++){
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("madonhang",madonhang.toString());
                                jsonObject.put("masanpham",HomeActivity.manggiohang.get(i).getIdsp());
                                jsonObject.put("tensanpham",HomeActivity.manggiohang.get(i).getTensp());
                                jsonObject.put("giasanpham",HomeActivity.manggiohang.get(i).getGiasp());
                                jsonObject.put("soluongsanpham",HomeActivity.manggiohang.get(i).getSoluongsp());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            jsonArray.put(jsonObject);
                        }
                        Map<String,String> hashMap1 = new HashMap<>();
                        hashMap1.put("json",jsonArray.toString());
                        APIUltil.getData().donhangchitiet(hashMap1).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(ThongtinKHActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(ThongtinKHActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("///", t.getMessage());
                    }
                });
            }
        });

    }
}
