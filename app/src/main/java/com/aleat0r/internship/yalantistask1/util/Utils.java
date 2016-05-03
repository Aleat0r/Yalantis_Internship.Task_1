package com.aleat0r.internship.yalantistask1.util;

import android.content.Context;

import com.aleat0r.internship.yalantistask1.R;
import com.aleat0r.internship.yalantistask1.data.Issue;
import com.aleat0r.internship.yalantistask1.data.State;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Aleksandr Kovalenko on 22.04.2016.
 */
public class Utils {

    public static final String KEY_STATE = "key_state";
    private static final int MAX_RANDOM = 999999999;
    private static final int MAX_LIKE_RANDOM = 100;
    private static final int DAYS_IN_MONTH = 31;

    private static DateFormat sFormatter;

    public static List<Issue> getModel(Context context, State state) {

        List<Issue> result = new ArrayList<>(10);

        Random random = new Random(System.currentTimeMillis());

        for (int i = 1; i <= 10; i++) {
            int randomInt = random.nextInt(MAX_RANDOM);
            String number = String.valueOf(randomInt);

            String category = "";
            String responsible = "";
            int iconId = 0;

            switch (randomInt % 2) {
                case 0:
                    category = context.getString(R.string.category_1);
                    responsible = context.getString(R.string.responsibile_1);
                    iconId = R.drawable.ic_doc;
                    break;
                case 1:
                    category = context.getString(R.string.category_2);
                    responsible = context.getString(R.string.responsibile_2);
                    iconId = R.drawable.ic_trash;
                    break;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.DAY_OF_MONTH, random.nextInt(DAYS_IN_MONTH) + 1);
            Date dtCreated = calendar.getTime();

            calendar.set(Calendar.MONTH, Calendar.MARCH);
            calendar.set(Calendar.DAY_OF_MONTH, random.nextInt(DAYS_IN_MONTH) + 1);
            Date dtReg = calendar.getTime();

            result.add(new Issue(i, category, state, dtCreated, dtReg, new Date(), responsible, iconId,
                    random.nextInt(MAX_LIKE_RANDOM), context.getString(R.string.full_description) + number));
        }

        return result;
    }

    public static DateFormat getFormatter(Context context) {
        if (sFormatter == null) {
            sFormatter = new SimpleDateFormat(context.getString(R.string.date_format), Locale.getDefault());
        }
        return sFormatter;
    }
}
