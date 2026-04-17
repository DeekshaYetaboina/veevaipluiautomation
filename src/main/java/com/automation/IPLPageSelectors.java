package com.automation;
/**
 * IPLPageSelectors contains all locator strings used in the framework.
 *
 * This centralizes locators for:
 * - Easy maintenance
 * - Better readability
 * - Reduced duplication
 */
public class IPLPageSelectors {
    public static String headers = ".site-menu li a[data-element_text='%s']";
    public static String footerSection ="div[class*='ap-footer']";
    public static String footerMenuSections = ".ap-foot-menu";
    public static String footerLinks = ".ap-foot-menu a";
    public static String teamLogo=".vn-teamsInnerWrp .vn-team-logo img";
    public static String teamCards=".vn-teamsInnerWrp li";
    public static String teamWinningYears=".team-on-hover";
    public static String teams = ".ih-pt-cont";
    public static String matches = "td[class='ng-binding']";
    public static String points =".bt.ng-binding";
    public static String searchButton ="button.search-icon-header-menu";
    public static String searchTab ="input#searchInputForHeader";
    public static String newsSection ="div.vn-latest-news";
    public static String allArticles="li.textTwoLine";
    public static String pointsTable ="section.w-100.pull-left";


}