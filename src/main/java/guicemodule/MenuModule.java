package guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import controllers.MainMenuController;
import controllers.MainMenuControllerImp;
import controllers.deckcreation.DeckCreationController;
import controllers.deckcreation.DeckCreationControllerImpl;
import controllers.maincontroller.MainController;
import controllers.maincontroller.Request;
import javafx.stage.Stage;
import models.Account.Account;
import models.Account.AccountImp;
import notifier.EventBus;
import views.AccountView;
import views.deckcreation.DeckCreationView;
import views.deckcreation.DeckCreationViewImpl;
import views.scene.layout.LAYOUT;

public final class MenuModule extends AbstractModule {

    private Stage stage;

    public MenuModule(final Stage stage) {
        this.stage = stage;
    }

    @Provides
    DeckCreationController provideDeckController(final Account account, final DeckCreationView deckView, final MainController mainController, final EventBus<Request<LAYOUT, ? extends Object>> notifier) {
        return new DeckCreationControllerImpl(account, deckView, mainController, notifier);
    }

    @Provides
    MainMenuController provideMainMenuController(final Account account, final AccountView accountView, final MainController mainController, final EventBus<Request<LAYOUT, ? extends Object>> notifier) {
        return new MainMenuControllerImp(account, accountView, mainController, notifier);
    }

    @Provides
    AccountView provideAccountView() {
        return new AccountView(this.stage);

    }

    @Provides
    Account provideAccount() {
        return new AccountImp();
    }

    @Provides
    DeckCreationView provideDeckView() {
        return new DeckCreationViewImpl(this.stage);
    }

}
