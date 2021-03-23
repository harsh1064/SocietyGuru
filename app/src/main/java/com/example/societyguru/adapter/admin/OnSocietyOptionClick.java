package com.example.societyguru.adapter.admin;

import com.example.societyguru.model.SocietyModel;

public interface OnSocietyOptionClick {
    void showoptions(SocietyModel model, OnSocietyOptionClick view);

    void showSocietyInfo(int position);

}
