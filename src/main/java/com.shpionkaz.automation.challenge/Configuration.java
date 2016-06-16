package com.shpionkaz.automation.challenge;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Configuration {

    private Configuration() {}

    private final static Config config = ConfigFactory.load().getConfig("berlinPragueChallenge");

    public final static String BASE_URL = config.getString("baseUrl");

    public final static int IMPLICIT_WAIT_TIMEOUT = config.getInt("implicitWaitTimeout");
    public final static int PAGE_LOAD_TIMEOUT = config.getInt("pageLoadTimeout");
    public final static int AJAX_LOCATOR_FACTORY_TIMEOUT = config.getInt("ajaxLocationTimeout");

    public final static int FLUENT_WAIT_TIMEOUT = config.getInt("fluentWait.timeout");
    public final static int FLUENT_WAIT_INTERVAL = config.getInt("fluentWait.interval");

    public final static String LANDING_PAGE_URL = config.getString("pages.landing");
    public final static String SEARCH_RESULTS_URL = config.getString("pages.searchResults");


}
