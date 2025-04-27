package xuanbao.edu.quanlytruyentranh;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import xuanbao.edu.quanlytruyentranh.adapter.adapterTruyen;
import xuanbao.edu.quanlytruyentranh.database.databasedoctruyen;
import xuanbao.edu.quanlytruyentranh.model.Truyen;

public class ManTimKiem extends AppCompatActivity {

    ListView listView;

    EditText edt;

    ArrayList<Truyen> TruyenArrayList;
    ArrayList<Truyen> arrayList;

    adapterTruyen adapterTruyen;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_tim_kiem);

        listView = findViewById(R.id.listviewTimKiem);
        edt = findViewById(R.id.timkiem);

        initList();

        //bật click cho item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManTimKiem.this, ManNoiDung.class);
                String tent = TruyenArrayList.get(position).getTenTruyen();
                String noidungtr = TruyenArrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen", tent);
                intent.putExtra("noidung", noidungtr);
                startActivity(intent);
            }
        });
    }

    //phương thức lấy dữ liệu, gắn vào listview
    private void initList() {
        TruyenArrayList = new ArrayList<>();

        arrayList = new ArrayList<>();

        databasedoctruyen = new databasedoctruyen(this);

        Cursor cursor = databasedoctruyen.getData2();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            TruyenArrayList.add(new Truyen(id, tentruyen, noidung, anh, id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(), TruyenArrayList);

            listView.setAdapter(adapterTruyen);
        }
        cursor.moveToFirst();
        cursor.close();
    }
}