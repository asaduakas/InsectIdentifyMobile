package com.example.insectidentify;

import androidx.databinding.BaseObservable;

public class QuestionViewModel extends BaseObservable {
    public String title;
    public String getTitle() {
        return title;
    }

    public String question1description;
    public String getQuestion1description() {
        return question1description;
    }

    public String question2description;
    public String getQuestion2description() {
        return question2description;
    }

    public String question3description;
    public String getQuestion3description() {
        return question3description;
    }

    public String question1image;
    public String getQuestion1image() {
        return question1image;
    }

    public String question2image;
    public String getQuestion2image() {
        return question2image;
    }

    public String question3image;
    public String getQuestion3image() {
        return question3image;
    }

    public String question1button;
    public String getQuestion1button() {
        return question1button;
    }

    public String question2button;
    public String getQuestion2button() {
        return question2button;
    }

    public String question3button;
    public String getQuestion3button() {
        return question3button;
    }

    public int question1reference;
    public int getQuestion1reference() {
        return question1reference;
    }

    public int question2reference;
    public int getQuestion2reference() {
        return question2reference;
    }

    public int question3reference;
    public int getQuestion3reference() {
        return question3reference;
    }

    public int previousQuestionReference;
    public int getPreviousQuestionReference() {
        return previousQuestionReference;
    }

    public int progress;
    public int getProgress() {
        return progress;
    }

    public String order;
    public String getOrder() {
        return order;
    }

    public String suborder;
    public String getSuborder() {
        return suborder;
    }

    public String image;
    public String getImage() {
        return image;
    }

    public String description;
    public String getDescription() {
        return description;
    }

    public String key;
    public String getKey(){return key;}

    public QuestionViewModel(String title, String question1description, String question2description,
                             String question1image, String question2image, String question1button,
                             String question2button, int question1reference, int question2reference,
                             int previousQuestionReference, int progress){
        this.title = title;
        this.question1description = question1description;
        this.question2description = question2description;
        this.question1image = question1image;
        this.question2image = question2image;
        this.question1button = question1button;
        this.question2button = question2button;
        this.question1reference = question1reference;
        this.question2reference = question2reference;
        this.previousQuestionReference = previousQuestionReference;
        this.progress = progress;

        this.question3description = null;
        this.question3image = null;
        this.question3button = null;
        this.question3reference = 0;
        this.order = null;
        this.suborder = null;
        this.description = null;
        this.image = null;
    }

    public QuestionViewModel(String title, String question1description, String question2description,
                             String question3description, String question1image, String question2image,
                             String question3image, String question1button, String question2button,
                             String question3button, int question1reference, int question2reference,
                             int question3reference, int previousQuestionReference, int progress){
        this.title = title;
        this.question1description = question1description;
        this.question2description = question2description;
        this.question1image = question1image;
        this.question2image = question2image;
        this.question1button = question1button;
        this.question2button = question2button;
        this.question1reference = question1reference;
        this.question2reference = question2reference;
        this.previousQuestionReference = previousQuestionReference;
        this.progress = progress;

        this.question3description = question3description;
        this.question3image = question3image;
        this.question3button = question3button;
        this.question3reference = question3reference;
        this.order = null;
        this.suborder = null;
        this.description = null;
        this.image = null;
    }

    public QuestionViewModel(String order, String suborder, String description, String image, int previousQuestionReference, String key){
        this.title = null;
        this.question1description = null;
        this.question2description = null;
        this.question1image = null;
        this.question2image = null;
        this.question1button = null;
        this.question2button = null;
        this.question1reference = 0;
        this.question2reference = 0;
        this.previousQuestionReference = previousQuestionReference;
        this.progress = 100;

        this.key = key;

        this.question3description = null;
        this.question3image = null;
        this.question3button = null;
        this.question3reference = 0;
        this.order = order;
        this.suborder = suborder;
        this.description = description;
        this.image = image;
    }
}
