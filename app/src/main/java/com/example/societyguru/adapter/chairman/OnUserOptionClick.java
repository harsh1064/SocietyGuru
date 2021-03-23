package com.example.societyguru.adapter.chairman;

import com.example.societyguru.adapter.admin.OnSocietyOptionClick;
import com.example.societyguru.model.SocietyModel;
import com.example.societyguru.model.UserModel;

public interface OnUserOptionClick {
    void showoptions(UserModel model, OnUserOptionClick view);

    void showSocietyInfo(int position);
}
