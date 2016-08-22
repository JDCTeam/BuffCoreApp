/*
 * Copyright (C) 2015-2016 Willi Ye <williye97@gmail.com>
 *
 * This file is part of Kernel Adiutor.
 *
 * Kernel Adiutor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kernel Adiutor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Kernel Adiutor.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.grarak.kerneladiutor.views.recyclerview.overallstatistics;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.view.ViewPropertyAnimator;

import com.grarak.kerneladiutor.R;
import com.grarak.kerneladiutor.utils.ViewUtils;
import com.grarak.kerneladiutor.views.recyclerview.RecyclerViewItem;

/**
 * Created by willi on 30.04.16.
 */
public class FrequencyButtonView extends RecyclerViewItem {

    private View.OnClickListener mRefreshListener;
    private View.OnClickListener mResetListener;
    private View.OnClickListener mRestoreListener;

    private Bitmap mRefreshImage;
    private Bitmap mResetImage;

    @Override
    public int getLayoutRes() {
        return R.layout.rv_frequencytable_buttons_view;
    }

    @Override
    public void onCreateView(View view) {
        AppCompatImageButton refresh = (AppCompatImageButton) view.findViewById(R.id.frequency_refresh);
        AppCompatImageButton reset = (AppCompatImageButton) view.findViewById(R.id.frequency_reset);
        AppCompatImageButton restore = (AppCompatImageButton) view.findViewById(R.id.frequency_restore);

        if (mRefreshImage == null) {
            mRefreshImage = ViewUtils.drawableToBitmap(ContextCompat.getDrawable(view.getContext(),
                    R.drawable.ic_refresh));
        }
        refresh.setImageBitmap(mRefreshImage);

        if (mResetImage == null) {
            Matrix matrix = new Matrix();
            matrix.postRotate(180);
            matrix.preScale(-1.0f, 1.0f);
            mResetImage = Bitmap.createBitmap(mRefreshImage, 0, 0, mRefreshImage.getWidth(),
                    mRefreshImage.getHeight(), matrix, true);
        }
        reset.setImageBitmap(mResetImage);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotate(v, false);
                if (mRefreshListener != null) {
                    mRefreshListener.onClick(v);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotate(v, true);
                if (mResetListener != null) {
                    mResetListener.onClick(v);
                }
            }
        });
        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotate(v, true);
                if (mRestoreListener != null) {
                    mRestoreListener.onClick(v);
                }
            }
        });

        setFullSpan(true);
        super.onCreateView(view);
    }

    public void setRefreshListener(View.OnClickListener onClickListener) {
        mRefreshListener = onClickListener;
    }

    public void setResetListener(View.OnClickListener onClickListener) {
        mResetListener = onClickListener;
    }

    public void setRestoreListener(View.OnClickListener onClickListener) {
        mRestoreListener = onClickListener;
    }

    private void rotate(final View v, boolean reverse) {
        ViewPropertyAnimator animator = v.animate().rotation(reverse ? -360 : 360);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                v.setRotation(0);
            }
        });
        animator.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRefreshImage != null) {
            mRefreshImage.recycle();
            mRefreshImage = null;
        }
        if (mResetImage != null) {
            mResetImage.recycle();
            mResetImage = null;
        }
    }
}
