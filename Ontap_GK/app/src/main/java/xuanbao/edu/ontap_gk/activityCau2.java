package xuanbao.edu.ontap_gk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class activityCau2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau2);

        ArrayList<String> nguonDL = new ArrayList<String>();
        nguonDL.add("Tiếng Anh");
        nguonDL.add("Tiếng Việt");
        nguonDL.add("Tiếng Nhật");
        nguonDL.add("Tiếng Trung");

        ListView listViewNN = (ListView) findViewById(R.id.lvIndexLanguage);

        ArrayAdapter<String> NgonNguAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nguonDL);
        listViewNN.setAdapter(NgonNguAdapter);

        listViewNN.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = NgonNguAdapter.getItem(position);
                Intent intent = new Intent(activityCau2.this, activityCau4.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}