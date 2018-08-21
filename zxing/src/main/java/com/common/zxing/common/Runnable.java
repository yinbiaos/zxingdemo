package com.common.zxing.common;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 兼容低版本的子线程开启任务
 *
 * @author hugo
 */
public class Runnable {

    @SuppressLint("NewApi")
    @SuppressWarnings("unchecked")
    public static void execAsync(AsyncTask<?, ?, ?> task) {
        if (Build.VERSION.SDK_INT >= 11) {
            //task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            task.executeOnExecutor(Executors.newCachedThreadPool());
            task.executeOnExecutor(new ThreadPoolExecutor(5, 10,
                    60L, TimeUnit.SECONDS,
                    new SynchronousQueue<java.lang.Runnable>(), Executors.defaultThreadFactory()));
        } else {
            task.execute();
        }

    }

}
