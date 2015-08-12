package legency.june.testaddremove;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by legency on 2015/8/12.
 */
@EActivity(R.layout.twolayout)
public class TwoLayoutActivity extends Activity implements View.OnClickListener {
    @ViewById
    LinearLayout options;

    @ViewById
    LinearLayout results;

    @ViewById
    LinearLayout main;

    @AfterViews
    void init(){
        build(5);
        main.setOnClickListener(this);
    }

    void build(int size){
        int[] colors= {Color.RED, Color.BLUE, Color.CYAN, Color.GRAY,Color.YELLOW};
        for(int i=0;i<size;i++){
            View view = null;
            view  = new View(this);
            view.setTag("item");
            view.setId(i);
            view.setLayoutParams(new FrameLayout.LayoutParams(dp(100), dp(100)));
            view.setBackgroundColor(colors[i]);
            view.setClickable(true);
            view.setOnClickListener(this);
            options.addView(view);
        }
    }

    private int dp(int i){
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (i * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        Log.e("clicked","ID:"+v.getId());
        Log.e("clicked", "ClassName:" + v.getClass().getName());
        Log.e("clicked", "Tag:" + v.getTag());
        Log.e("clicked", "Parent:" + ((View)v.getParent()).getId());

        if (v.getParent() == results){
            results.removeView(v);
            options.addView(v);
        }else if(v.getParent() == options){
            options.removeView(v);
            results.addView(v);
        }
    }
}
