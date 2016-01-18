package com.example.joybar.myaskunagjia.demo.github.listviewCanlender;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.example.joybar.library_canlender.DatePickerController;
import com.example.joybar.library_canlender.DayPickerView;
import com.example.joybar.library_canlender.SimpleMonthAdapter;
import com.example.joybar.myaskunagjia.R;


public class MainActivity extends Activity implements DatePickerController {

    private DayPickerView dayPickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_canlender);

        dayPickerView = (DayPickerView) findViewById(R.id.pickerView);
        dayPickerView.setController(this);
    }




    @Override
    public int getMaxYear()
    {
        return 2015;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day)
    {
        Log.e("Day Selected", day + " / " + month + " / " + year);
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays)
    {

        Log.e("Date range selected", selectedDays.getFirst().toString() + " --> " + selectedDays.getLast().toString());
    }
}
