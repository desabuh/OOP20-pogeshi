package controllers;

import javafx.scene.input.MouseEvent;

public interface AccountController extends Controller {

    void giocaClick();

    void statisticheClick(MouseEvent e);

    void nuovaPartitaClick();

    void formaDeckClick();

    void esciClick();

    void indietroClick(MouseEvent  e);

}
