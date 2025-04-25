package xuanbao.edu.quanlytruyentranh;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import xuanbao.edu.quanlytruyentranh.database.databasedoctruyen;

public class LoginActivity extends AppCompatActivity {

    //tạo biến cho màn hình đăng nhập
    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap, btnDangKy;

    //để tạo đối tượng cho database đọc truyện
    databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        AnhXa();

        databasedoctruyen = new databasedoctruyen(this);

        //tạo sự kiện click button chuyển màn hình đăng ký với Intent
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gán cho các biến là giá trị nhập vào từ editText
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();

                //sử dụng con trỏ để lấy dữ liệu, gọi tới getData() để lấy tất cả tài khoản ở database
                Cursor cursor = databasedoctruyen.getData();

                //thực hiện vòng lặp để lấy dữ liệu từ cursor với moveToNext() di chuyển tiếp
                while (cursor.moveToNext()){

                    //lấy dữ liệu và gắn vào biến, dữ liệu tài khoản ở ô 1 và mật khẩu ở ô 2, ô 0 là idtaikhoan email và 4 là phanquyen
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

                    //nếu tài khoản và mật khẩu nhập vào từ bàn phím khớp với ở database
                    if (datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)){
                        //lấy dữ liệu phanquyen và id
                        int phanquyen = cursor.getInt(4);
                        int id = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        //gửi dữ liệu qua Activity là MainActivity
                        intent.putExtra("phanquyen", phanquyen);
                        intent.putExtra("id", id);
                        intent.putExtra("email", email);
                        intent.putExtra("tentaikhoan", tentk);
                        startActivity(intent);
                    }
                }
                //thực hiện trả cursor về đầu
                cursor.moveToFirst();
                //đóng khi không dùng
                cursor.close();
            }
        });
    }

    private void AnhXa() {
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.btndangky);
        btnDangNhap = findViewById(R.id.btndangnhap);
    }
}