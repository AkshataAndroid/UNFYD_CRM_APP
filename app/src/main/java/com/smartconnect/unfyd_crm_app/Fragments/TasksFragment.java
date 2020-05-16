package com.smartconnect.unfyd_crm_app.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.PagerTitleStrip;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smartconnect.unfyd_crm_app.Activity.FirstActivity;
import com.smartconnect.unfyd_crm_app.Adapter.OpportunitiesAdapter;
import com.smartconnect.unfyd_crm_app.Adapter.TaskAdapter;
import com.smartconnect.unfyd_crm_app.Adapter.TaskDetailsAdapter;
import com.smartconnect.unfyd_crm_app.Adapter.TaskPendingAdapter;
import com.smartconnect.unfyd_crm_app.Model.OpportunityList;
import com.smartconnect.unfyd_crm_app.Model.TaskCurrentModel;
import com.smartconnect.unfyd_crm_app.Model.TaskPendingmodel;
import com.smartconnect.unfyd_crm_app.Model.ViewPagerModel;
import com.smartconnect.unfyd_crm_app.R;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Vector;

public class TasksFragment extends Fragment  {
    View view;
 Context mContext;
   TaskDetailsAdapter taskcurrentAdapter;
   TaskPendingAdapter taskPendingAdapter;
   TaskCurrentModel newCurrentList;
   TaskPendingmodel newPendingList;
    ArrayList<String> tasklist = new ArrayList<String>();
    ArrayList<TaskCurrentModel> arraytaskcurrentlist= new ArrayList<TaskCurrentModel>();
    ArrayList<TaskCurrentModel> listcurrent= new ArrayList<TaskCurrentModel>();

    ArrayList<TaskPendingmodel> arraytaskpendinglist = new ArrayList<TaskPendingmodel>();
    ArrayList<TaskPendingmodel> listpening = new ArrayList<TaskPendingmodel>();

    FloatingActionButton addtaskButton;
    ListView currentlist,pendinglist;
    TextView mTotal,mText;
    String subject,description,date,assignto,status;
    int  totalcurrent,totalpending;
    PagerTitleStrip pagerTitleStrip;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.fragment_tasks, container, false);
            SQLiteDatabase.loadLibs(getContext());
            mContext = getActivity();
            addtaskButton = view.findViewById(R.id.addtaskbutton);
            ViewPager vp = (ViewPager) view.findViewById(R.id.viewPager);
           // pagerTitleStrip=view.findViewById(R.id.pagertitle);

            currentlist = new ListView(mContext);
            pendinglist = new ListView(mContext);

            // ListView listview3 = new ListView(mContext);
            Vector<View> pages = new Vector<>();

            pages.add(currentlist);
            pages.add(pendinglist);
            // pages.add(listview3);


            vp.setClipToPadding(false);
            vp.setPadding(10, 0, 140, 0);
//            pagerTitleStrip.setClipToPadding(true);
//            pagerTitleStrip.setPadding(10, 50, 140, 0);


            TaskAdapter adapter = new TaskAdapter(mContext, pages);
            vp.setAdapter(adapter);



            FirstActivity activity = (FirstActivity) getActivity();
            if (activity != null)
                activity.hideBottomBar(false);
            if (getArguments() != null) {
                subject = this.getArguments().getString("Subject");
                Log.d("Subject:", subject);
                description = this.getArguments().getString("Description");
                Log.d("Description ", description);
                date = this.getArguments().getString("Date");
                Log.d("date:", date);
                assignto = this.getArguments().getString("Assign");
                Log.d("Assign to : ", assignto);
                status = this.getArguments().getString("Status");
                if(status.equals("Current")){
                    newCurrentList=new TaskCurrentModel(description,date,assignto,subject);


                }else{
                    newPendingList=new TaskPendingmodel(description,date,assignto,subject);

                }
                assert activity != null;
                listcurrent=activity.taskCurrentModels(newCurrentList);
                taskcurrentAdapter = new TaskDetailsAdapter(getContext(), listcurrent);
                taskcurrentAdapter.notifyDataSetChanged();
                currentlist.setSaveFromParentEnabled(false);
                currentlist.setAdapter(taskcurrentAdapter);
               // assert activity != null;
                listpening=activity.taskPendingmodels(newPendingList);
                taskPendingAdapter = new TaskPendingAdapter(getContext(), listpening);
                taskPendingAdapter.notifyDataSetChanged();
                pendinglist.setSaveFromParentEnabled(false);
                pendinglist.setAdapter(taskPendingAdapter);


//                totalcurrent=currentlist.getAdapter().getCount();
//                totalpending=currentlist.getAdapter().getCount();
//                String pendingtask=String.valueOf(totalpending);
//                 String currenttasks=String.valueOf(totalcurrent);





//                taskAdapter = new TaskDetailsAdapter(getContext(), tasklist);
//                taskPendingAdapter = new TaskPendingAdapter(getContext(), tasklist);
//
//                tasklist.add("");
//                taskAdapter.notifyDataSetChanged();
//                taskPendingAdapter.notifyDataSetChanged();
//                currentlist.setAdapter(taskAdapter);
//                pendinglist.setAdapter(taskPendingAdapter);



            }else {
//                currentlist = new ListView(mContext);
//                pendinglist = new ListView(mContext);
                // ListView listview3 = new ListView(mContext);



//                taskAdapter = new TaskDetailsAdapter(getContext(), tasklist);
//                taskPendingAdapter = new TaskPendingAdapter(getContext(), tasklist);
//
//                tasklist.add("");
//                taskAdapter.notifyDataSetChanged();
//                taskPendingAdapter.notifyDataSetChanged();
//                currentlist.setAdapter(taskAdapter);
//                pendinglist.setAdapter(taskPendingAdapter);

                TaskCurrentModel taskCurrentModel=null;
                TaskPendingmodel taskPendingmodel=null;
                listcurrent=activity.taskCurrentModels(taskCurrentModel);
                listpening=activity.taskPendingmodels(taskPendingmodel);
                taskcurrentAdapter = new TaskDetailsAdapter(getContext(), listcurrent);
                taskPendingAdapter = new TaskPendingAdapter(getContext(), listpening);
                taskcurrentAdapter.notifyDataSetChanged();
                taskPendingAdapter.notifyDataSetChanged();
                currentlist.setSaveFromParentEnabled(false);
                pendinglist.setSaveFromParentEnabled(false);

                currentlist.setAdapter(taskcurrentAdapter);
                pendinglist.setAdapter(taskPendingAdapter);

            }


        }catch (Exception ex){
            ex.printStackTrace();

        }
       // listview3.setAdapter(new ArrayAdapter<String>(mContext,R.layout.list_view,new String[]{"A3","B3","C3","D3"}));
        addtaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment addTasksFragment = new AddTaskFragment();
                //   bottomNavigationView.setVisibility(View.GONE);
                addtaskButton.setVisibility(View.GONE);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.task_container, addTasksFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }

        try{
            if(arraytaskcurrentlist!=null &&arraytaskcurrentlist.size()>0) {
                taskcurrentAdapter = new TaskDetailsAdapter(getContext(),arraytaskcurrentlist);
                currentlist.setAdapter(taskcurrentAdapter);
                taskcurrentAdapter.notifyDataSetChanged();
            }
            if(arraytaskpendinglist!=null &&arraytaskpendinglist.size()>0) {
                taskPendingAdapter = new TaskPendingAdapter(getContext(),arraytaskpendinglist);
                pendinglist.setAdapter(taskPendingAdapter);
                taskPendingAdapter.notifyDataSetChanged();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

