package xuanbao.edu.listbaihatyeuthich;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Hiển thị dữ liệu lên ListView
        //B1: Chuẩn bị nguồn dữ liệu (có thể tạo sẵn(hardcode), lấy từ tệp, csdl
        ArrayList<String> nguonDuLieu = new ArrayList<String>();
        nguonDuLieu.add("Miền đất hứa");
        nguonDuLieu.add("Đừng xấu hổ");
        nguonDuLieu.add("Tiến vào màn đêm");
        nguonDuLieu.add("Tái sinh");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}