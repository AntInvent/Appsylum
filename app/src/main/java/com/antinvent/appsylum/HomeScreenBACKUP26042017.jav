package com.antinvent.appsylum;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeScreenBACKUP26042017 extends Activity {

    private static final String FILE_NAME = "EventsData.txt";
    TimetableAdaptor timetableAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        TextView footerView = (TextView) getLayoutInflater().inflate(R.layout.timetable_footer, null);
        if(null==footerView){
            return;
        }

        ExpandableListView ttList = (ExpandableListView) findViewById(R.id.ttList);
        /*List<String> dayNames = new ArrayList<>();
        List<String> mondayEvents = new ArrayList<>();
        List<String> tuesdayEvents = new ArrayList<>();
        List<String> wednesdayEvents = new ArrayList<>();
        List<String> thursdayEvents = new ArrayList<>();
        List<String> fridayEvents = new ArrayList<>();
        List<String> saturdayEvents = new ArrayList<>();
        List<String> sundayEvents = new ArrayList<>();
        HashMap<String, List<String>> eventsHashMap = new HashMap<>();*/  //commented out 25/04/17

        List<EventListing> dayNames = new ArrayList<>();
        List<EventListing> mondayEvents = new ArrayList<>();
        List<EventListing> tuesdayEvents = new ArrayList<>();
        List<EventListing> wednesdayEvents = new ArrayList<>();
        List<EventListing> thursdayEvents = new ArrayList<>();
        List<EventListing> fridayEvents = new ArrayList<>();
        List<EventListing> saturdayEvents = new ArrayList<>();
        List<EventListing> sundayEvents = new ArrayList<>();
        HashMap<String, List<EventListing>> eventsHashMap = new HashMap<>();

        String dayNamesArray[] = getResources().getStringArray(R.array.days);
        String mondayEventsArray[] = getResources().getStringArray(R.array.monday_events);
        String tuesdayEventsArray[] = getResources().getStringArray(R.array.tuesday_events);
        String wednesdayEventsArray[] = getResources().getStringArray(R.array.wednesday_events);
        String thursdayEventsArray[] = getResources().getStringArray(R.array.thursday_events);
        String fridayEventsArray[] = getResources().getStringArray(R.array.friday_events);
        String saturdayEventsArray[] = getResources().getStringArray(R.array.saturday_events);
        String sundayEventsArray[] = getResources().getStringArray(R.array.sunday_events);

        /*for(String dayName : dayNamesArray){
            dayNames.add(dayName);
        }*/
        for(String dayName : dayNamesArray){
            EventListing eventListing;
            EventListing.Day dayConvert;
            switch (dayName){
                case "Monday":
                    dayConvert = EventListing.Day.MONDAY;
                    eventListing(dayConvert, 12, 30, );
                    break;
                case "Tuesday":
                    dayConvert = EventListing.Day.TUESDAY;
                    break;
                case "Wednesday":
                    dayConvert = EventListing.Day.WEDNESDAY;
                    break;
                case "Thursday":
                    dayConvert = EventListing.Day.THURSDAY;
                    break;
                case "Friday":
                    dayConvert = EventListing.Day.FRIDAY;
                    break;
                case "Saturday":
                    dayConvert = EventListing.Day.SATURDAY;
                    break;
                case "Sunday":
                    dayConvert = EventListing.Day.SUNDAY;
                    break;
            }
            dayNames.add(eventListing);
        }

        for(String eventName : mondayEventsArray){
            mondayEvents.add(eventName);
        }

        for(String eventName : tuesdayEventsArray){
            tuesdayEvents.add(eventName);
        }

        for(String eventName : wednesdayEventsArray){
            wednesdayEvents.add(eventName);
        }

        for(String eventName : thursdayEventsArray){
            thursdayEvents.add(eventName);
        }

        for(String eventName : fridayEventsArray){
            fridayEvents.add(eventName);
        }

        for(String eventName : saturdayEventsArray){
            saturdayEvents.add(eventName);
        }

        for(String eventName : sundayEventsArray){
            sundayEvents.add(eventName);
        }

        eventsHashMap.put(dayNames.get(0), mondayEvents);
        eventsHashMap.put(dayNames.get(1), tuesdayEvents);
        eventsHashMap.put(dayNames.get(2), wednesdayEvents);
        eventsHashMap.put(dayNames.get(3), thursdayEvents);
        eventsHashMap.put(dayNames.get(4), fridayEvents);
        eventsHashMap.put(dayNames.get(5), saturdayEvents);
        eventsHashMap.put(dayNames.get(6), sundayEvents);

        timetableAdaptor = new TimetableAdaptor(this, dayNames, eventsHashMap);
        ttList.setAdapter(timetableAdaptor);

        /*ListView ttList = (ListView) findViewById(R.id.ttList);
        String[] events = getResources().getStringArray(R.array.days);//new String[]{"Basketball, HBF", "English Class, Newham Library", "Code Your Future, Code Bar" };
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < events.length; i++){
            list.add(events[i]);
        }

        arrayAdapter = new ArrayAdapter(this, R.layout.timetable_item, list);
        ttList.setAdapter(arrayAdapter);*/

        //ttList.addFooterView(footerView);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItems();
                Toast.makeText(getApplicationContext(), "Items Saved", Toast.LENGTH_SHORT).show();

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if (item.getItemId() == R.id.delete) {
            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    // Load stored ToDoItems
    /*private void loadItems() {
        BufferedReader reader = null;
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));

            String title = null;
            String priority = null;
            String status = null;
            Date date = null;

            while (null != (title = reader.readLine())) {
                priority = reader.readLine();
                status = reader.readLine();
                date = ToDoItem.FORMAT.parse(reader.readLine());
                mAdapter.add(new ToDoItem(title, Priority.valueOf(priority),
                        Status.valueOf(status), date));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    // Save ToDoItems to file
    private void saveItems() {
        PrintWriter writer = null;
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    fos)));

            for (int groupID = 0; groupID < timetableAdaptor.getGroupCount(); groupID++) {
                writer.println(timetableAdaptor.getGroup(groupID));
                for (int childID = 0; childID < timetableAdaptor.getChildrenCount(groupID); childID++){
                    writer.println(timetableAdaptor.getChild(groupID, childID));
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }
}
