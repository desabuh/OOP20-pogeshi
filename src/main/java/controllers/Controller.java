package controllers;

import views.View;

public interface Controller {

    View getView();

    void callBackAction(Object data);

}
