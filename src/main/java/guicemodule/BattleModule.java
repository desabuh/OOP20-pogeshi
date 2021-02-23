package guicemodule;

import com.google.inject.AbstractModule;

import com.google.inject.Provides;

import controllers.Controller;
import controllers.battle.BattleControllerImpl;
import controllers.maincontroller.MainController;
import controllers.maincontroller.Request;
import javafx.stage.Stage;
import models.battle.Battle;
import models.battle.BattleImpl;
import notifier.EventBus;
import views.View;
import views.battle.BattleView;
import views.battle.BattleViewImpl;
import views.scene.layout.LAYOUT;

public final class BattleModule extends AbstractModule {

    private Stage stage;

    public BattleModule(final Stage stage) {
        this.stage = stage;
    }

    @Provides
    Controller provideBattleController(final Battle model, final BattleView view, final MainController mainController, final EventBus<Request<LAYOUT, ? extends Object>> notifier) {
        return new BattleControllerImpl(model, view, mainController, notifier);
    }

    @Provides
    Battle provideBattleController() {
        return new BattleImpl();
    }

    @Provides
    BattleView provideBattleView() {
        return new BattleViewImpl(this.stage);
    }
}
