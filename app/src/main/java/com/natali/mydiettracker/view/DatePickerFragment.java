package com.natali.mydiettracker.view;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
       DatePickerDialog datePickerDialog= new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getTargetFragment(), year, month, day);
       datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, null, (DialogInterface.OnClickListener) null);
  //    datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, null, (DialogInterface.OnClickListener) null);

      return datePickerDialog;
    }
}