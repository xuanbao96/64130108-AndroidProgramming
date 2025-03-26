package xuanbao.edu.ontap_gk;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class activityCau3 extends AppCompatActivity {
    ImageListAdapter imgListAdapter;
    ArrayList<ImageList> recyclerVLstData;
    RecyclerView recyclerViewGK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau3);
        recyclerVLstData = getDataForRecyclerView();
        recyclerViewGK = findViewById(R.id.RecycleListView);

        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
        recyclerViewGK.setLayoutManager(layoutLinear);

        imgListAdapter = new ImageListAdapter(this,recyclerVLstData);
        recyclerViewGK.setAdapter(imgListAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    ArrayList<ImageList> getDataForRecyclerView(){
        ArrayList<ImageList> dsDL = new ArrayList<ImageList>();
        ImageList imageList1 = new ImageList("english_lg","Ngôn ngữ anh");
        dsDL.add(imageList1);
        dsDL.add(new ImageList("japan_lg","Ngôn ngữ nhật"));
        dsDL.add(new ImageList("vietnam_lg","Ngôn ngữ việt"));
        dsDL.add(new ImageList("chinese_lg","Ngôn ngữ trung"));
        return dsDL;
    }
}