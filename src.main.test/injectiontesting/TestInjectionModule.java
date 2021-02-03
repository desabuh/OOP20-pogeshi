package injectiontesting;


import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;

import controllers.BattleController;
import controllers.BattleControllerImpl;
import guicemodule.ControllerModule;

/**
 * JUnit test for {@link ControllerModule} injection.
 */
public class TestInjectionModule {

    @Test
    public void injectBattleController() {
        Injector injector = Guice.createInjector(new ControllerModule());
        try {
            BattleController battleController  = injector.getInstance(BattleControllerImpl.class);
        } catch (ProvisionException e) {
        }

    }

}
