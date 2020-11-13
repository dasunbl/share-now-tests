package com.dasun.registration.pages;

import com.dasun.registration.pages.components.CookiePopUp;
import com.dasun.registration.pages.components.Header;
import lombok.Getter;

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
