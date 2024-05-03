package com.iu.JaWa.ui;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

class RouteTabs extends Tabs implements BeforeEnterObserver {
	private static final long serialVersionUID = 1L;
	private final Map<RouterLink, Tab> routerLinkTabMap = new HashMap<>();

    public void add(RouterLink routerLink) {
        routerLink.setHighlightCondition(HighlightConditions.sameLocation());
        routerLink.setHighlightAction(
            (link, shouldHighlight) -> {
                if (shouldHighlight) setSelectedTab(routerLinkTabMap.get(routerLink));
            }
        );
        routerLinkTabMap.put(routerLink, new Tab(routerLink));
        add(routerLinkTabMap.get(routerLink));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // In case no tabs will match
        setSelectedTab(null);
    }
    
    public static RouteTabs createTabs() {
    	
    	RouteTabs routeTabs = new RouteTabs();
		routeTabs.add(new RouterLink("Einkauf", SellUi.class));
		routeTabs.add(new RouterLink("Nutzer-Stammdaten", UserAdministrationUI.class));
		routeTabs.add(new RouterLink("Artikel-Stammdaten", ArticleUi.class));
    	
    	return routeTabs;
    }
}