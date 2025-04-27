package xuanbao.edu.quanlytruyentranh;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import xuanbao.edu.quanlytruyentranh.database.databasedoctruyen;
import xuanbao.edu.quanlytruyentranh.model.TaiKhoan;

public class RegisterActivity extends AppCompatActivity {

    EditText edtTaiKhoanDK, edtMatKhauDK, edtEmailDK;
    Button btnDangKyDK, btnDangNhapDK;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        databasedoctruyen = new databasedoctruyen(this);

        AnhXa();
        //click cho button đăng ký
        btnDangKyDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edtTaiKhoanDK.getText().toString();
                String matkhau = edtMatKhauDK.getText().toString();
                String email = edtEmailDK.getText().toString();

                TaiKhoan taiKhoan1 = CreateTaiKhoan();
                if (taikhoan.equals("") || matkhau.equals("") || email.equals("")) {
                    Log.e("Thông báo :", "Chưa nhập đầy đủ thông tin");
                }
                //Nếu đầy đủ thông tin nhập vào thì add tài khoản vào database
                else {
                    databasedoctruyen.AddTaiKhoan(taiKhoan1);
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                }
            }
        });

        //trở về đăng nhập
        btnDangNhapDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //phương thức tạo tài khoản
    private TaiKhoan CreateTaiKhoan() {
        String taikhoan = edtTaiKhoanDK.getText().toString();
        String matkhau = edtMatKhauDK.getText().toString();
        String email = edtEmailDK.getText().toString();
        int phanquyen = 1;

        TaiKhoan tk = new TaiKhoan(taikhoan, matkhau, email, phanquyen);
        return tk;
    }

    private void AnhXa() {
        edtEmailDK = findViewById(R.id.emaildk);
        edtMatKhauDK = findViewById(R.id.matkhaudk);
        edtTaiKhoanDK = findViewById(R.id.taikhoandk);
        btnDangKyDK = findViewById(R.id.btndangkydk);
        btnDangNhapDK = findViewById(R.id.btndangnhapdk);
    }
}

