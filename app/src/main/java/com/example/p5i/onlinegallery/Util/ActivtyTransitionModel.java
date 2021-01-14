package com.example.p5i.onlinegallery.Util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class ActivtyTransitionModel
{
    private Context context;
    private Intent intent;
    private ActivityOptions options;

    public ActivtyTransitionModel(Context context) {
        this.context = context;
    }
    public void startringBrowserToGetAutheticate(String url)
    {
        intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }
    public void startActivity(Activity destinationActivity)
    {
        intent=new Intent(context,destinationActivity.getClass());
        context.startActivity(intent,ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
    }
    public void startsharedActivity(Activity destinationActivity, View view)
    {
        intent=new Intent(context,destinationActivity.getClass());
        options=ActivityOptions.makeSceneTransitionAnimation((Activity) context,view,view.getTransitionName());
        context.startActivity(intent,options.toBundle());
    }
}
