package com.example.clicker;

import androidx.lifecycle.ViewModel;

public class ExerciseViewModel extends ViewModel {
    public String bodyPart = "there";

    public String getBodyPart(){
        return this.bodyPart;
    }
}

