package com.ntil.habiture;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.habiture.Habiture;

import java.util.List;

/**
 * Created by GawinHsu on 5/7/15.
 */
public class HabitListFragment extends Fragment {

    private Listener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (Listener) activity;
    }

    private ListView item_list;
    private static List<Habiture> habitures;
    private HabitListAdapter habitListAdapter;

    private static final boolean DEBUG = true;
    private void trace(String message) {
        if(DEBUG)
            Log.d("HabitListFragment", message);
    }

    public static HabitListFragment newInstance(List<Habiture> habitures){
        HabitListFragment fragment = new HabitListFragment();

        HabitListFragment.habitures = habitures;

        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        trace("onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        item_list = (ListView) getActivity().findViewById(R.id.lvHabitureList);

        habitListAdapter = new HabitListAdapter(getActivity(), habitures);
        item_list.setAdapter(habitListAdapter);
        item_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pid = ((Habiture)habitListAdapter.getItem(position)).getId();
                listener.onClickHabitSingleItem(pid);
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        trace("onCreateView");
        return inflater.inflate(R.layout.fragment_habiture, container, false);
    }

    public interface Listener {
        public void onClickHabitSingleItem(int pid);
    }
}
