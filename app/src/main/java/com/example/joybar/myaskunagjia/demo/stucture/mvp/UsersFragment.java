package com.example.joybar.myaskunagjia.demo.stucture.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.T;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.adapter.UsersAdapter;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.data.User;
import com.example.joybar.myaskunagjia.demo.stucture.mvp.util.CheckUtils;

import java.util.ArrayList;

/**
 * Created by joybar on 06/01/2017.
 */

public class UsersFragment extends Fragment implements UsersContract.View{

    private UsersContract.Presenter mPresenter;

    private UsersAdapter mListAdapter;



    private View mNoUsersView;

    private ImageView mNoUserIcon;

    private TextView mNoUserMainView;

    private TextView mNoUserAddView;

    private LinearLayout mUsersView;

    private TextView mFilteringLabelView;

    public UsersFragment() {
        // Requires empty public constructor
    }

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new UsersAdapter(new ArrayList<User>(0));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View root = inflater.inflate(R.layout.framnet_mvp, container, false);


        // Set up Users view
        RecyclerView listView = (RecyclerView) root.findViewById(R.id.tasks_list);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listView.setAdapter(mListAdapter = new UsersAdapter(){
            @Override
            public void onBindViewHolder(ItemViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.getViewItem().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.showLong(getActivity(),"open");
                    }
                });
            }
        });
        mUsersView = (LinearLayout) root.findViewById(R.id.tasksLL);

        // Set up  no tasks view
        mNoUsersView = root.findViewById(R.id.noTasks);
        mNoUserIcon = (ImageView) root.findViewById(R.id.noTasksIcon);
        mNoUserMainView = (TextView) root.findViewById(R.id.noTasksMain);
        mNoUserAddView = (TextView) root.findViewById(R.id.noTasksAdd);
        mNoUserAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  showAddTask();
                T.showLong(getActivity(),"add");
            }
        });




        // Set up floating action button
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_add_task);

        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  mPresenter.addNewUser();
                T.showLong(getActivity(),"add");
            }
        });

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(listView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadUsers(false);
            }
        });

        setHasOptionsMenu(true);
        
        return root;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear:
                mPresenter.clearUsers();
                break;

            case R.id.menu_refresh:
                mPresenter.loadUsers(true);
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tasks_fragment_menu, menu);
    }
    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showUsers(ArrayList<User> Users) {
        mListAdapter.replaceData(Users);

        mUsersView.setVisibility(View.VISIBLE);
        mNoUsersView.setVisibility(View.GONE);
    }

    @Override
    public void showAddUser() {
//        Intent intent = new Intent(getContext(), AddEditTaskActivity.class);
//        startActivityForResult(intent, AddEditTaskActivity.REQUEST_ADD_TASK);
        T.showLong(getActivity(),"跳转到add页面 ");
    }

    @Override
    public void showUserDetailsUi(String UserId) {

    }

    @Override
    public void showLoadingUsersError() {

    }

    @Override
    public void showNoUsers() {
        showNoUsersViews(
                "no",
                R.drawable.ic_assignment_turned_in_24dp,
                false
        );
    }

    private void showNoUsersViews(String mainText, int iconRes, boolean showAddView) {
        mUsersView.setVisibility(View.GONE);
        mNoUsersView.setVisibility(View.VISIBLE);

        mNoUserMainView.setText(mainText);
        mNoUserIcon.setImageDrawable(getResources().getDrawable(iconRes));
        mNoUserAddView.setVisibility(showAddView ? View.VISIBLE : View.GONE);
    }
    
    

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(UsersContract.Presenter presenter) {
        mPresenter = CheckUtils.checkNotNull(presenter);
    }
}
