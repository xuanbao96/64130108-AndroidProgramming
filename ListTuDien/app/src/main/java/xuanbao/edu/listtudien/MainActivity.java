package xuanbao.edu.listtudien;

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

        ArrayList<String> nguonDL = new ArrayList<String>();
        nguonDL.add("Tiếng Anh");
        nguonDL.add("Tiếng Việt");
        nguonDL.add("Tiếng Nhật");
        nguonDL.add("Tiếng Nga");
        nguonDL.add("Tiếng Pháp");

        ListView listViewNN = (ListView) findViewById(R.id.lvTuDien);

        ArrayAdapter<String> NgonNguAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nguonDL);
        listViewNN.setAdapter(NgonNguAdapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}