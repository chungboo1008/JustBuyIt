package com.example.balaginjo.finalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mImageIDs;

    ViewPagerAdapter(Context context, int index){
        mContext = context;
        switch (index){
            case 0:
                mImageIDs = new int[]{ R.drawable.mypicture1, R.drawable.mypicture2,
                        R.drawable.mypicture3, R.drawable.mypicture4, R.drawable.mypicture5};
                break;
            case 1:
                mImageIDs = new int[]{ R.drawable.product1, R.drawable.product1_1,
                        R.drawable.product1_2};
                break;
            case 2:
                mImageIDs = new int[]{ R.drawable.product2, R.drawable.product2_1,
                        R.drawable.product2_2, R.drawable.product2_3, R.drawable.product2_4};
                break;
            case 3:
                mImageIDs = new int[]{ R.drawable.product3, R.drawable.product3_1,
                        R.drawable.product3_2, R.drawable.product3_3, R.drawable.product3_4};
                break;
            case 4:
                mImageIDs = new int[]{ R.drawable.product4, R.drawable.product4_1,
                        R.drawable.product4_2, R.drawable.product4_3, R.drawable.product4_4};
                break;
            case 5:
                mImageIDs = new int[]{ R.drawable.product5, R.drawable.product5_1,
                        R.drawable.product5_2};
                break;
            case 6:
                mImageIDs = new int[]{ R.drawable.product6, R.drawable.product6_1,
                        R.drawable.product6_2, R.drawable.product6_3, R.drawable.product6_4,
                        R.drawable.product6_5, R.drawable.product6_6};
                break;
        }
    }

    @Override
    public int getCount() {
        return mImageIDs.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obejct) {
        return view == obejct;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIDs[position]);

        imageView.setAdjustViewBounds(true);

        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
