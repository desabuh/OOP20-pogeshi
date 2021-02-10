package guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;

import controllers.BattleControllerImpl;
import controllers.Controller;
import guicemodule.annotation.BattleController;
import models.Battle;
import models.BattleImpl;
import views.BattleView;
import views.BattleViewImpl;

public final class BattleModule extends AbstractModule {

        @Provides
        @Singleton
        @BattleController
        controllers.BattleController provideBattleController(final Battle model) {
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
