package xuanbao.edu.ontap_gk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ItemImageHolder> {
    Context context;
    ArrayList<ImageList> lstData;

    public ImageListAdapter(Context context, ArrayList<ImageList> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @NonNull
    @Override
    public ItemImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater LuyenGK = LayoutInflater.from(context);
        View vItem = LuyenGK.inflate(R.layout.item_image_lv, parent, false);
        ItemImageHolder vholder = new ItemImageHolder(vItem);
        return vholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemImageHolder holder, int position) {
        ImageList imageListHT = lstData.get(position);
        String cption = imageListHT.getGKCaption();
        String picture = imageListHT.getGKImageFileName();
        holder.tvCaption.setText(cption);
        String packageName = holder.itemView.getContext().getPackageName();
        int imgID = holder.itemView.getResources().getIdentifier(picture,"mipmap",packageName);
        holder.ivImageList.setImageResource(imgID);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, activityCau4.class);
            intent.putExtra("CAPTION", cption);
            intent.putExtra("IMAGE", picture);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    class ItemImageHolder extends RecyclerView.ViewHolder{
        TextView tvCaption;
        ImageView ivImageList;

        public ItemImageHolder(@NonNull View itemView) {
            super(itemView);
            tvCaption = itemView.findViewById(R.id.textViewCaption);
            ivImageList = itemView.findViewById(R.id.imageViewGK);
        }
    }
}
