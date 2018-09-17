package com.itfitness.snaphelperdemo;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecycleview;
    private BaseQuickAdapter<TestBean,BaseViewHolder> mBaseQuickAdapter;
    private ArrayList<TestBean> mDatas;
    private int[]imgs={R.mipmap.a1,R.mipmap.a2,R.mipmap.a3,R.mipmap.a4,R.mipmap.a5,R.mipmap.a6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleview = (RecyclerView) findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycleview.setLayoutManager(linearLayoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecycleview);
        mDatas=new ArrayList<>();
        for(int x=0;x<imgs.length;x++){
            TestBean testBean = new TestBean();
            testBean.setText("我已经厌倦了嫌恶别人、憎恨别人的生活，厌倦了无法爱任何人的生活。我连一个朋友也没有，哪怕是一个。最重要的是，我甚至连自己都爱不起来。");
            testBean.setImgs(imgs[x]);
            mDatas.add(testBean);
        }
        mBaseQuickAdapter=new BaseQuickAdapter<TestBean, BaseViewHolder>(R.layout.item,mDatas) {
            @Override
            protected void convert(BaseViewHolder helper, TestBean item) {
                ImageView img=helper.getView(R.id.img);
                final TextView tv=helper.getView(R.id.tv);
                img.setImageResource(item.getImgs());
                tv.setText(item.getText());
                Palette.from(BitmapFactory.decodeResource(getResources(),item.getImgs())).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        int vibrantColor = palette.getVibrantColor(Color.BLUE);
                        tv.setTextColor(vibrantColor);
                    }
                });
            }
        };
        mRecycleview.setAdapter(mBaseQuickAdapter);
    }
}
