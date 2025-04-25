package xuanbao.edu.quanlytruyentranh;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView, listViewNew, listViewThongTin;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        AnhXa();
        ActionViewFlipper();
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
    }
}