package guicemodules;



import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Provides {@link Controller} and derived bindings.
 *
 *
 * <p>The following bindings are provided:
 *
 * <ul>
 *   <li>{@link WorldMapController}
 *   <li>{@link BattleController}
 *   <li>{@link AccountController}
 * </ul>
 */
public final class ControllerModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(Controller.class).to(WorldMapController.class);
        bind(Controller.class).to(BattleController.class);
        bind(Controller.class).to(AccountController.class);
    }

    @Provides
    @Singleton
    @WorldMapController
    Controller provideMapController(final @WorldMap WorldMap model) {
        return new WorldMapController(model);
    }

    @Provides
    @Singleton
    @BattleController
    Controller provideBattleController(final @Battle Battle model) {
        return new WorldMapController(model);
    }

    @Provides
    @Singleton
    @AccountController
    Controller provideMapController(final @Account Account model) {
        return new WorldMapController(model);
    }

    @Provides
    @Singleton
    @MainController
    Controller provideMainController() {
        return new MainController();
    }

}
