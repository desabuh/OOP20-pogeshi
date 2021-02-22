package guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import controllers.DeckCreationController;
import controllers.DeckCreationControllerImpl;
import controllers.MainMenuController;
import controllers.MainMenuControllerImp;
import controllers.StatisticsController;
import controllers.StatisticsControllerImp;
import javafx.stage.Stage;
import models.Account.Account;
import models.Account.AccountImp;
import views.DeckCreationView;
import views.DeckCreationViewImpl;

public final class MenuModule extends AbstractModule {

    private Stage stage;

    public MenuModule(final Stage stage) {
        this.stage = stage;
    }

    @Provides
    DeckCreationController provideDeckController(final Account account, final DeckCreationView deckView) {
        return new DeckCreationControllerImpl(account, deckView);
    }
    
    @Provides
    MainMenuController provideMainMenuController(final Account account) {
        return new MainMenuControllerImp();
    }
    
    @Provides
    StatisticsController provideStatisticsController(final Account account) {
        return new StatisticsControllerImp();
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