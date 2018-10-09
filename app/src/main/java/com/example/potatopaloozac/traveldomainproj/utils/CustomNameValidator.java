package com.example.potatopaloozac.traveldomainproj.utils;

import android.util.Log;
import android.widget.EditText;

import com.github.phajduk.rxvalidator.RxValidationResult;
import com.github.phajduk.rxvalidator.Validator;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import rx.Observable;

public class CustomNameValidator implements Validator<EditText> {

    private static final String TAG = "RxValidator";

    @Override
    public Observable<RxValidationResult<EditText>> validate(String text, EditText item) {
        RxValidationResult<EditText> result;
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3,20}$");

        if (pattern.matcher(text).matches()) {
            result = RxValidationResult.createSuccess(item);
        } else {
            result = RxValidationResult.createImproper(item, "Improper Name");
        }

        return Observable.just(result).delay(1, TimeUnit.SECONDS);
    }
}