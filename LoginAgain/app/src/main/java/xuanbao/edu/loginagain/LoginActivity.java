package xuanbao.edu.loginagain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        Button btnXacNhan = (Button) findViewById(R.id.btnOK);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edTenDN = (EditText) findViewById(R.id.edtUsername);
                EditText edPass = (EditText) findViewById(R.id.edtPass);
                String tenDangNhap = edTenDN.getText().toString();
                String mk = edPass.getText().toString();
                
                if (tenDangNhap.equals("xuanbao") && mk.equals("123")){
                    Intent iQuiz = new Intent(LoginActivity.this,HomeActivity.class);
                    iQuiz.putExtra("ten_dang_nhap",tenDangNhap);
                    iQuiz.putExtra("mk_dang_nhap",mk);
                    startActivity(iQuiz);
                }
                else {
                    Toast.makeText(MainActivity.this,"THÔNG TIN ĐÃ NHẬP SAI",Toast.LENGTH_LONG);
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}