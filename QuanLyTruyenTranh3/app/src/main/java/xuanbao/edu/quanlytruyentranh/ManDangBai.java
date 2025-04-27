package xuanbao.edu.quanlytruyentranh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import xuanbao.edu.quanlytruyentranh.database.databasedoctruyen;
import xuanbao.edu.quanlytruyentranh.model.Truyen;

public class ManDangBai extends AppCompatActivity {

    EditText edtTenTruyen, edtNoiDung, edtAnh;
    Button btnDangBai;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_dang_bai);

        edtAnh = findViewById(R.id.dbImg);
        edtTenTruyen = findViewById(R.id.dbTentruyen);
        edtNoiDung = findViewById(R.id.dbNoidung);
        btnDangBai = findViewById(R.id.dbdangbai);

        databasedoctruyen = new databasedoctruyen(this);

        //button đăng bài
        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tentruyen = edtTenTruyen.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                Truyen truyen = CreateTruyen();

                if (tentruyen.equals("") || noidung.equals("") || img.equals("")) {
                    Toast.makeText(ManDangBai.this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("ERR", "Nhập đầy đủ thông tin");
                }
                //nếu nhập đầy đủ thông tin thì thực hiện thêm dữ liệu
                else {
                    databasedoctruyen.AddTruyen(truyen);

                    //chuyển qua màn admin và cập nhật lại dữ liệu
                    Intent intent = new Intent(ManDangBai.this, ManAdmin.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }
    //phương thức tạo truyện
    private Truyen CreateTruyen() {

        String tentruyen = edtTenTruyen.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id", 0);

        Truyen truyen = new Truyen(tentruyen, noidung, img, id);
        return truyen;
    }
}