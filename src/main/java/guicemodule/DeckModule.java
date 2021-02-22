package guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import controllers.DeckCreationController;
import controllers.DeckCreationControllerImpl;
import javafx.stage.Stage;
import models.Account.Account;
import models.Account.AccountImp;
import views.DeckCreationView;
import views.DeckCreationViewImpl;

public final class DeckModule extends AbstractModule {

    private Stage stage;

    public DeckModule(final Stage stage) {
        this.stage = stage;
    }

    @Provides
    DeckCreationController provideDeckController(final Account account, final DeckCreationView deckView) {
        return new DeckCreationControllerImpl(account, deckView);
    }

    @Provides
    Account provideAccount() {
        return new AccountImp();
    }

    @Provides
    DeckCreationView provideWorldMapView() {
        return new DeckCreationViewImpl(this.stage);
    }


}
