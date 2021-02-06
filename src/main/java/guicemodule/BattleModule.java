package guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import controllers.BattleControllerImpl;
import controllers.Controller;
import guicemodule.annotation.BattleController;
import models.Battle;
import models.BattleImpl;
import views.BattleView;
import views.BattleViewImpl;

public final class BattleModule extends AbstractModule {
    /*
    @Override
    protected void configure() {
        bind(Controller.class).to(BattleControllerImpl.class);
        bind(Battle.class).to(BattleImpl.class);
        bind(BattleView.class).to(BattleViewImpl.class);
    }*/

    @Provides
    @Singleton
    @BattleController
    Controller provideBattleController(final Battle model) {
        return new BattleControllerImpl();
    }

    @Provides
    @Singleton
    Battle provideBattleController() {
        return new BattleImpl();
    }

    @Provides
    @Singleton
    BattleView provideBattleView() {
        return new BattleViewImpl();
    }
}
