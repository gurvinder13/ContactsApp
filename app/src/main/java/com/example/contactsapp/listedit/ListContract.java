package com.example.contactsapp.listedit;

import com.example.contactsapp.BasePresenter;
import com.example.contactsapp.BaseView;
import com.example.contactsapp.data.db.entity.Person;

import java.util.List;


public interface ListContract {

    interface Presenter extends BasePresenter {

        void addNewPerson();

        void result(int requestCode, int resultCode);

        void populatePeople();

        void openEditScreen(Person person);

        void openConfirmDeleteDialog(Person person);

        void delete(long personId);
    }

    interface View extends BaseView<Presenter> {

        void showAddPerson();

        void setPersons(List<Person> persons);

        void showEditScreen(long id);

        void showDeleteConfirmDialog(Person person);

        void showEmptyMessage();
    }

    interface OnItemClickListener {

        void clickItem(Person person);

        void clickLongItem(Person person);
    }

    interface DeleteListener {

        void setConfirm(boolean confirm, long personId);

    }
}
