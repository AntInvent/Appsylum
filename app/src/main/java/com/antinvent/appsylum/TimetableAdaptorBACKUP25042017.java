package com.antinvent.appsylum;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by antinvent on 21/04/2017.
 */

public class TimetableAdaptorBACKUP25042017 extends BaseExpandableListAdapter {


    private List<String> daysList;
    private HashMap<String, List<String>> eventsHashMap;
    private Context context;

    TimetableAdaptorBACKUP25042017(Context context, List<String> daysList, HashMap<String, List<String>> eventsHashMap){
        this.context = context;
        this.daysList = daysList;
        this.eventsHashMap = eventsHashMap;
    }

    @Override
    public int getGroupCount() {
        return daysList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return eventsHashMap.get(daysList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return daysList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return eventsHashMap.get(daysList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String dayName = (String) this.getGroup(groupPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.timetable_day, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.dayNameTextView);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(dayName);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String eventTitle = (String) this.getChild(groupPosition, childPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.timetable_event, null); //complex layout
        }

        TextView textView = (TextView) convertView.findViewById(R.id.ttEventTitle);
        textView.setTypeface(null, Typeface.ITALIC);
        textView.setText(eventTitle);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
