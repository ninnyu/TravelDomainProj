package com.example.potatopaloozac.traveldomainproj.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.potatopaloozac.traveldomainproj.R;
import com.example.potatopaloozac.traveldomainproj.utils.CustomNameValidator;
import com.example.potatopaloozac.traveldomainproj.utils.MySharedPreference;
import com.github.phajduk.rxvalidator.RxValidationResult;
import com.github.phajduk.rxvalidator.RxValidator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PassengerDetailsAdapter extends RecyclerView.Adapter<PassengerDetailsAdapter.MyViewHolder> {

    private static final String TAG = "PassengerTAG";

    private Context context;
    private int count;
    private static final String dateFormat = "dd-MM-yyyy";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

    public PassengerDetailsAdapter(Activity activity, int count) {
        context = activity;
        this.count = count;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.passenger_details_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        String s = "Passenger Number " + (position+1);
        holder.tv_passengerNum.setText(s);

        RxValidator.createFor(holder.et_fname)
                .nonEmpty()
                .minLength(3, "Name is too short")
                .with(new CustomNameValidator())
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override public void call(RxValidationResult<EditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        if (result.isProper())
                            MySharedPreference.writeBoolean(MySharedPreference.PASSENGER_VALIDATED, true);
                        else
                            MySharedPreference.writeBoolean(MySharedPreference.PASSENGER_VALIDATED, false);
                        Log.i(TAG, "Validation result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });

        RxValidator.createFor(holder.et_lname)
                .nonEmpty()
                .minLength(3, "Name is too short")
                .with(new CustomNameValidator())
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override public void call(RxValidationResult<EditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        if (result.isProper())
                            MySharedPreference.writeBoolean(MySharedPreference.PASSENGER_VALIDATED, true);
                        else
                            MySharedPreference.writeBoolean(MySharedPreference.PASSENGER_VALIDATED, false);
                        Log.i(TAG, "Validation result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });

        RxValidator.createFor(holder.et_dob)
                .nonEmpty()
                .age("You have to be 18y old", 18, sdf)
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override public void call(RxValidationResult<EditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        if (result.isProper())
                            MySharedPreference.writeBoolean(MySharedPreference.PASSENGER_VALIDATED, true);
                        else
                            MySharedPreference.writeBoolean(MySharedPreference.PASSENGER_VALIDATED, false);
                        Log.i(TAG, "Validation result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });

        holder.et_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd =
                        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Date selectedDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
                                holder.et_dob.setText(sdf.format(selectedDate));
                            }
                        }, 2000, 0, 1);
                dpd.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_passengerNum;
        EditText et_fname, et_lname, et_dob;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_passengerNum = itemView.findViewById(R.id.tv_passengerNum);
            et_fname = itemView.findViewById(R.id.et_passengerFname);
            et_lname = itemView.findViewById(R.id.et_passengerLname);
            et_dob = itemView.findViewById(R.id.et_passengerDob);
        }
    }
}
