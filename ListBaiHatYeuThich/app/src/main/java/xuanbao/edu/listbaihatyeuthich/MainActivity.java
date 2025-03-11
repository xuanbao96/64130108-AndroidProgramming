package xuanbao.edu.listbaihatyeuthich;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        //B2: Tìm tham chiếu đến list view
        ListView listViewBH= (ListView) findViewById(R.id.lvDSbaihat);

        //B3: Tạo adapter, 3.1 và gán với nguồn
        ArrayAdapter<String> baiHat_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nguonDuLieu);

        //B4: Gắn/nạp dữ liệu từ nguồn vào ListView
        listViewBH.setAdapter(baiHat_Adapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}