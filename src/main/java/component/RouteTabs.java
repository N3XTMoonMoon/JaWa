package component;

import java.util.HashMap;
import java.util.Map;

import com.iu.JaWa.service.LoginService;
import com.iu.JaWa.ui.ArticleUi;
import com.iu.JaWa.ui.OrderUi;
import com.iu.JaWa.ui.SellUi;
import com.iu.JaWa.ui.StoringUi;
import com.iu.JaWa.ui.UserAdministrationUI;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import constants.UserRoleConstant;

public class RouteTabs extends Tabs implements BeforeEnterObserver {
	
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
    	
    	String role = LoginService.getRoleFromLoggedinUser();
    	RouteTabs routeTabs = new RouteTabs();
    	
    	switch(role) {
    	case(UserRoleConstant.USER):
    		
    		routeTabs.add(new RouterLink("Einkauf", SellUi.class));
    		
    		break;
    	case(UserRoleConstant.ADMIN):
    		
    		routeTabs.add(new RouterLink("Einkauf", SellUi.class));
			routeTabs.add(new RouterLink("Nutzer-Stammdaten", UserAdministrationUI.class));
			routeTabs.add(new RouterLink("Artikel-Stammdaten", ArticleUi.class));
			routeTabs.add(new RouterLink("Lagerverwaltung",StoringUi.class));
			routeTabs.add(new RouterLink("Bestellungen",OrderUi.class));
    		
    		break;
    	case(UserRoleConstant.NOT_SET):
    		break;
    	default:
    		
    		
    		break;
    	}
//    	
//		routeTabs.add(new RouterLink("Einkauf", SellUi.class));
//		routeTabs.add(new RouterLink("Nutzer-Stammdaten", UserAdministrationUI.class));
//		routeTabs.add(new RouterLink("Artikel-Stammdaten", ArticleUi.class));
//		routeTabs.add(new RouterLink("Bestellungen",OrderUi.class));
    	
    	return routeTabs;
    }
}