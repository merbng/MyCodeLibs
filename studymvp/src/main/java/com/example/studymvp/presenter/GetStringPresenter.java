package com.example.studymvp.presenter;

import com.example.studymvp.datamodel.IGetString;
import com.example.studymvp.datamodel.IGetStringIml;
import com.example.studymvp.view.IGetStringView;

/**
 * Created by Merbng on 2017/2/16.
 */

public class GetStringPresenter {
    private IGetString getString;
    private IGetStringView getStringView;
    public GetStringPresenter(IGetStringView view){
        this.getStringView=view;
        getString=new IGetStringIml();
    }
    public void  saveName(){
        getString.saveName(getStringView.getName());
    }
    public void showName(){
        getStringView.showName(getString.getName());
    }
}
