package xuanbao.edu.quanlytruyentranh;


import xuanbao.edu.quanlytruyentranh.adapter.adapterTruyen;
import xuanbao.edu.quanlytruyentranh.adapter.adapterchuyenmuc;
import xuanbao.edu.quanlytruyentranh.adapter.adapterthongtin;
import xuanbao.edu.quanlytruyentranh.database.databasedoctruyen;
import xuanbao.edu.quanlytruyentranh.model.TaiKhoan;
import xuanbao.edu.quanlytruyentranh.model.Truyen;
import xuanbao.edu.quanlytruyentranh.model.chuyenmuc;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView, listViewNew, listViewThongTin;
    DrawerLayout drawerLayout;

    String email;
    String tentaikhoan;

    ArrayList<Truyen> TruyenArraylist;

    adapterTruyen adapterTruyen;

    ArrayList<chuyenmuc> chuyenmucArrayList;

    ArrayList<TaiKhoan> taiKhoanArrayList;

    databasedoctruyen databasedoctruyen;

    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        databasedoctruyen = new databasedoctruyen(this);

        //nhận dữ liệu ở màn đăng nhập gửi
        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra("phanquyen",0);
        int id = intentpq.getIntExtra("id",0);
        email = intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");

        AnhXa();
        ActionBar();
        ActionViewFlipper();

        //bắt sk click item
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ManNoiDung.class);

                String tent = TruyenArraylist.get(position).getTenTruyen();
                String noidung = TruyenArraylist.get(position).getNoiDung();
                intent.putExtra("tentruyen", tent);
                intent.putExtra("noidung", noidung);
                startActivity(intent);
            }
        });

        //bắt click item cho listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //đăng bài
                if (position == 0){
                    if (i == 2){
                        Intent intent = new Intent(MainActivity.this, ManAdmin.class);
                        //gửi id tài khoản qua màn admin
                        intent.putExtra("Id", id);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài : ", "Bạn không có quyền");
                    }
                }
                //nếu vị trí ấn vào là thông tin thì sẽ chuyển qua ứng dụng màn thông tin
                else if (position == 1){
                    Intent intent =  new Intent(MainActivity.this, ManThongTin.class);
                    startActivity(intent);
                }
                //đăng xuất
                else if (position == 2){
                    finish();
                }
            }
        });
    }

    //thanh actionbar với toolbar
    private void ActionBar() {
        //hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);

        //thiết lập nút cho actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //tạo icon cho toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    //phương thức cho chạy quảng cáo với ViewFlipper
    private void ActionViewFlipper() {

        int[] imageResources = {
                R.drawable.rua_tho,
                R.drawable.cu_cai_trang,
                R.drawable.deo_chuong_meo,
                R.drawable.de_den_de_trang
        };
        // Lặp qua danh sách các ảnh và thêm vào ViewFlipper
        for (int imageResource : imageResources){
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(imageResource);
            // Scale ảnh cho vừa khung (có thể làm méo ảnh nếu tỷ lệ khác)
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //thêm ảnh từ imageview vào ViewFlipper
            viewFlipper.addView(imageView);
        }
        //thiết lập tự động chạy cho viewFlipper chạy trong 4 giây
        viewFlipper.setFlipInterval(3000);
        //chạy tự động viewFlipper
        viewFlipper.setAutoStart(true);

        //gọi animation cho vào và ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);

        //gọi Animation vào viewFlipper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    //phương thức ánh xạ
    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listViewNew = findViewById(R.id.lvnew);
        listView = findViewById(R.id.lvmanhinhchinh);
        listViewThongTin = findViewById(R.id.lvthongtin);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);

        TruyenArraylist = new ArrayList<>();

        Cursor cursor1 = databasedoctruyen.getData1();
        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArraylist.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(), TruyenArraylist);
            listViewNew.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

        //thông tin
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan, email));

        adapterthongtin = new adapterthongtin(this, R.layout.navigation_thongtin, taiKhoanArrayList);
        listViewThongTin.setAdapter(adapterthongtin);

        //chuyên mục
        chuyenmucArrayList = new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Đăng bài",R.drawable.ic_post_add));
        chuyenmucArrayList.add(new chuyenmuc("Thông tin",R.drawable.ic_face));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất",R.drawable.ic_logout));

        adapterchuyenmuc = new adapterchuyenmuc(this, R.layout.chuyenmuc, chuyenmucArrayList);
        listView.setAdapter(adapterchuyenmuc);
    }

    //nạp 1 menu tìm kiếm vào Actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //nếu click vào icon tìm kiếm sẽ chuyển sang màn hình tìm kiếm
        if (item.getItemId() == R.id.menu1) {
            Toast.makeText(this, "Bạn đã bấm tìm kiếm!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, ManTimKiem.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}