package xuanbao.edu.project_figma;

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

        Button btnLogin = (Button) findViewById(R.id.loginButton);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtTenDN = (EditText) findViewById(R.id.username);
                EditText edtPass = (EditText) findViewById(R.id.password);
                String tenDangNhap = edtTenDN.getText().toString();
                String mk = edtPass.getText().toString();

                if(tenDangNhap.equals("dinhxuanbao") && mk.equals("123")){
                    Intent iSkip = new Intent(LoginActivity.this,HomeActivity.class);
                    iSkip.putExtra("ten_dang_nhap",tenDangNhap);
                    iSkip.putExtra("mk_dang_nhap",mk);
                    startActivity(iSkip);
                }else{
                    Toast.makeText(LoginActivity.this,"BẠN NHẬP SAI THÔNG TIN",Toast.LENGTH_LONG).show();
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