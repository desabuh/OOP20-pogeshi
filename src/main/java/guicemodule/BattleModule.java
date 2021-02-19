package guicemodule;

import com.google.inject.AbstractModule;

import com.google.inject.Provides;

import controllers.BattleControllerImpl;
import controllers.Controller;
import javafx.stage.Stage;
import models.Battle;
import models.BattleImpl;
import views.BattleView;
import views.BattleViewImpl;
import views.View;

public final class BattleModule extends AbstractModule {

    private Stage stage;

    public BattleModule(final Stage stage) {
        this.stage = stage;
    }

    @Provides
    Controller provideBattleController(final Battle model, final BattleView view) {
        return new BattleControllerImpl(model, view);
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
