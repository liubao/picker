package com.liubao.picker.data;

import com.liubao.picker.widget.IPedigree;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * * Created by liubao on 2018/11/16.
 */
public class PickDateModel {
    public List<IPedigree> data = new ArrayList<>();
    private int suggestDefaultYear;

    public PickDateModel(int startYear,
                         int startMonth,
                         int startDay,
                         int endYear,
                         int endMonth,
                         int endDay) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, startYear);
        c.set(Calendar.MONTH, startMonth - 1);
        c.set(Calendar.DAY_OF_MONTH, startDay);
        startYear = c.get(Calendar.YEAR);
        startMonth = c.get(Calendar.MONTH) + 1;
        startDay = c.get(Calendar.DAY_OF_MONTH);

        c.set(Calendar.YEAR, endYear);
        c.set(Calendar.MONTH, endMonth - 1);
        c.set(Calendar.DAY_OF_MONTH, endDay);
        endYear = c.get(Calendar.YEAR);
        endMonth = c.get(Calendar.MONTH) + 1;
        endDay = c.get(Calendar.DAY_OF_MONTH);

        suggestDefaultYear = endYear - (endYear - startYear) / 2 - 1;
        Calendar calendar = Calendar.getInstance();
        for (int i = startYear; i <= endYear; i++) {
            YearModel yearModel = new YearModel(i);
            yearModel.initChildren(calendar, startYear, startMonth, startDay,
                    endYear, endMonth, endDay);
            data.add(yearModel);
        }
    }

    public int getSuggestDefaultYear() {
        return suggestDefaultYear;
    }

    private static class YearModel implements IPedigree<MonthModel> {

        List<MonthModel> children = new ArrayList<>();
        public int year;

        public YearModel(int year) {
            this.year = year;
        }

        @Override
        public String getText() {
            return String.valueOf(year) + "年";
        }

        @Override
        public String getKey() {
            return String.valueOf(year);
        }

        @Override
        public List<MonthModel> getChildren() {
            return children;
        }

        public void initChildren(Calendar calendar,
                                 int startYear,
                                 int startMonth,
                                 int startDay,
                                 int endYear,
                                 int endMonth,
                                 int endDay) {

            int start = 1;
            if (year == startYear) {
                start = startMonth;
            }
            int end = 12;
            if (year == endYear) {
                end = endMonth;
            }
            for (int i = start; i <= end; i++) {
                MonthModel monthModel = new MonthModel(year, i);
                monthModel.initChildren(calendar, startYear, startMonth, startDay,
                        endYear, endMonth, endDay);
                children.add(monthModel);
            }
        }
    }

    private static class MonthModel implements IPedigree<DayModel> {


        List<DayModel> children = new ArrayList<>();
        public int year;
        public int month;

        public MonthModel(int year, int month) {
            this.year = year;
            this.month = month;
        }

        public void initChildren(Calendar calendar,
                                 int startYear,
                                 int startMonth,
                                 int startDay,
                                 int endYear,
                                 int endMonth,
                                 int endDay) {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            int start = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
            if (year == startYear) {
                if (month == startMonth) {
                    start = startDay;
                }
            }

            int end = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            if (year == endYear) {
                if (month == endMonth) {
                    end = endDay;
                }
            }

            for (int i = start; i <= end; i++) {
                DayModel dayModel = new DayModel(i);
                children.add(dayModel);
            }
        }

        @Override
        public String getText() {
            return String.valueOf(month) + "月";
        }

        @Override
        public String getKey() {
            return String.valueOf(month);
        }

        @Override
        public List<DayModel> getChildren() {
            return children;
        }

    }

    private static class DayModel implements IPedigree<DayModel> {

        public int day;

        public DayModel(int i) {
            day = i;
        }

        @Override
        public String getText() {
            return String.valueOf(day) + "日";
        }

        @Override
        public String getKey() {
            return String.valueOf(day);
        }

        @Override
        public List<DayModel> getChildren() {
            return null;
        }

    }
}