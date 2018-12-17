package grabbuddy.infobite.grabbuddy.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import grabbuddy.infobite.grabbuddy.R;
import grabbuddy.infobite.grabbuddy.adapter.PopularStoresAdapter;
import grabbuddy.infobite.grabbuddy.adapter.SlidingImage_Adapter;
import grabbuddy.infobite.grabbuddy.adapter.TodaysOfferAdapter;
import grabbuddy.infobite.grabbuddy.constant.Constant;
import grabbuddy.infobite.grabbuddy.interfaces.FragmentChangeListener;
import grabbuddy.infobite.grabbuddy.modal.Coupon;
import grabbuddy.infobite.grabbuddy.utils.BaseFragment;

public class CouponsFragment extends BaseFragment {

    private View rootView;
    private CirclePageIndicator indicator;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.img_a, R.drawable.img_b, R.drawable.img_c, R.drawable.img_d};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private RecyclerView recyclerViewPopularStore, recyclerViewTopOffer;
    private TodaysOfferAdapter todaysOfferAdapter;
    private PopularStoresAdapter popularStoresAdapter;
    private List<Coupon> todaysOfferArrayList = new ArrayList<>();
    private List<Coupon> popularStoresArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_coupons, container, false);
        mContext = getActivity();
        init((new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)),
                (new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)));
        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        indicator = (CirclePageIndicator) rootView.findViewById(R.id.indicator);
        init1();

        return rootView;
    }

    private void init1() {

        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        mPager.setAdapter(new SlidingImage_Adapter(getActivity(), ImagesArray));
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = IMAGES.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    private void init(RecyclerView.LayoutManager layout, RecyclerView.LayoutManager layoutB) {
        recyclerViewPopularStore = rootView.findViewById(R.id.recyclerViewPopularStore);
        recyclerViewTopOffer = rootView.findViewById(R.id.recyclerViewTopOffer);

        String[] desc = {mContext.getString(R.string.desc_a), mContext.getString(R.string.desc_b),
                mContext.getString(R.string.desc_c), mContext.getString(R.string.desc_d),
                mContext.getString(R.string.desc_a),
                mContext.getString(R.string.desc_b)};

        for (int i = 0; i < desc.length; i++) {
            todaysOfferArrayList.add(new Coupon(desc[i], Constant.images[i], Constant.exclusiveImage[i]));
        }

        for (int i = 0; i < desc.length; i++) {
            popularStoresArrayList.add(new Coupon(Constant.images[i]));
        }

        todaysOfferAdapter = new TodaysOfferAdapter(todaysOfferArrayList, mContext);
        recyclerViewTopOffer.setLayoutManager(layout);
        recyclerViewTopOffer.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTopOffer.setAdapter(todaysOfferAdapter);
        todaysOfferAdapter.notifyDataSetChanged();

        popularStoresAdapter = new PopularStoresAdapter(popularStoresArrayList, mContext);
        recyclerViewPopularStore.setLayoutManager(layoutB);
        recyclerViewPopularStore.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPopularStore.setAdapter(popularStoresAdapter);
        popularStoresAdapter.notifyDataSetChanged();
    }
}
