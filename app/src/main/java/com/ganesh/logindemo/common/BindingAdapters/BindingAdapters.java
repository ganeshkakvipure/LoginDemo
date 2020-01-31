package com.ganesh.logindemo.common.BindingAdapters;


import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter({"imgFileName"})
    public static void setProfilePic(AppCompatImageView imageView, String imgProfile) {

        if(!TextUtils.isEmpty(imgProfile)){
            imgProfile=imgProfile.replaceAll(".jpg","");
        }

      /*  Glide.with(imageView.getContext()).load(BuildConfig.BASE_URL + BuildConfig.IMAGE_PATH + imgProfile + ".jpg")
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_avatar)
                .centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(imageView.getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });*/

    }


}
