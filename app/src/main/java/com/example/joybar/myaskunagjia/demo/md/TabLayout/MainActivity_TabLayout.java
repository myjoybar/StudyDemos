package com.example.joybar.myaskunagjia.demo.md.TabLayout;



        import android.content.Context;
        import android.os.Bundle;
        import android.support.design.widget.TabLayout;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.view.ViewPager;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.Window;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;


        import com.example.joybar.myaskunagjia.R;

        import java.util.ArrayList;
        import java.util.List;


public class MainActivity_TabLayout extends FragmentActivity implements OnClickListener
{
    private String TAG = MainActivity_TabLayout.class.getName();
    private Context context;
    private ImageView imv_back;
    private TextView tv_title;
    private Button btn;


    private ViewPager viewPager;
    private  TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_tablayout);

        initView();
        initValue();
        setLinstener();
        fillData();

    }

    private void  initView()
    {
//    	imv_back = (ImageView) this.findViewById(R.id.imv_back);
//    	tv_title =(TextView) this.findViewById(R.id.tv_title);
        //	btn = (Button) this.findViewById(R.id.btn);

        viewPager = (ViewPager) this.findViewById(R.id.viewPager);


        tabLayout = (TabLayout) this.findViewById(R.id.tabs);

        //有这么一种情况，当Tabs中的内容超过了手机屏幕的宽度时，
        // Tabs选项卡中的tab为什么不支持水平滑动？其实TabLayout是支持水平滑动的，只需要你在代码中添加如下一行即可：
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式
    }

    private void initValue()
    {

        context = this;
    }



    private void setLinstener()
    {
        //imv_back.setOnClickListener(this);
        //	btn.setOnClickListener(this);
    }

    private void fillData()
    {

        List<String> tabList = new ArrayList<>();
        tabList.add("Tab1");
        tabList.add("Tab2");
        tabList.add("Tab3");
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));

        List<Fragment> fragmentList = new ArrayList<>();

/*

 */
        for (int i = 0; i < tabList.size(); i++) {
            Fragment f1 = new subfragment();
            Bundle bundle = new Bundle();
            bundle.putString("content", "Content"+i);
            f1.setArguments(bundle);
            fragmentList.add(f1);
        }








        TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
        viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

    }





    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {

//        case R.id.imv_back:
//
//			 this.finish();
//             break;

//      case R.id.btn:
//			 Intent intent11 = new Intent(MainActivity.this, MyActionbar1.class);
            //startActivity(intent11);
//           break;

            default:
                break;
        }

    }

}
