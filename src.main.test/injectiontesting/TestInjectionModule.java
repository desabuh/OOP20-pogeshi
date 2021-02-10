package injectiontesting;


import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;

import controllers.BattleController;
import controllers.BattleControllerImpl;
import guicemodule.BattleModule;
import guicemodule.ControllerModule;
import models.Battle;
import views.BattleView;
import views.BattleViewImpl;

/**
 * JUnit test for {@link ControllerModule} injection.
 */
public class TestInjectionModule {

    @Test
    public void injectBattleController() {
        Injector injector = Guice.createInjector(new BattleModule());
        try {
            BattleView battleController  = injector.getInstance(BattleViewImpl.class);
        } catch (ProvisionException e) {
        }

    }

}
