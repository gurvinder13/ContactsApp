package com.example.contactsapp.edit;

import com.example.contactsapp.BasePresenter;
import com.example.contactsapp.BaseView;
import com.example.contactsapp.data.db.entity.Person;

import java.util.Date;


public interface EditContract {

    interface Presenter extends BasePresenter {
        void save(Person person);

        boolean validate(Person person);

        void getPersonAndPopulate(long id);

        void update(Person person);
    }

    interface View extends BaseView<Presenter> {

        void showErrorMessage(int field);

        void clearPreErrors();


        void close();

        void populate(Person person);
    }

}
