package com.example.p5i.onlinegallery.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ActivtyTransitionModel
{
    private Context context;
    private Intent intent;

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
        context.startActivity(intent);
    }
}
