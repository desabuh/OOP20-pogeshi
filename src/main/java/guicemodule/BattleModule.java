package guicemodule;

import com.google.inject.AbstractModule;

import com.google.inject.Provides;

import controllers.Controller;
import controllers.battle.BattleControllerImpl;
import javafx.stage.Stage;
import models.battle.Battle;
import models.battle.BattleImpl;
import views.View;
import views.battle.BattleView;
import views.battle.BattleViewImpl;

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
