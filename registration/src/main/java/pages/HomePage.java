package pages;

import lombok.Getter;
import pages.components.CookiePopUp;
import pages.components.Header;

@Getter
public class HomePage extends PageBase {

    private Header header;
    private CookiePopUp cookiePopUp;

    public HomePage() {

    }

    public  void openHomePage() {
        openPage("");
        header = new Header();
        cookiePopUp = new CookiePopUp();

    }



}
