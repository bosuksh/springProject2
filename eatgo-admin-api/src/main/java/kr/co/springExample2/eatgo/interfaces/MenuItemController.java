package kr.co.springExample2.eatgo.interfaces;

import kr.co.springExample2.eatgo.application.MenuItemService;
import kr.co.springExample2.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PutMapping("/restaurants/{id}/menuitems")
    public String bulkUpdate(@PathVariable Long id, @RequestBody List<MenuItem> requests) {
        //List<MenuItem> menuItems = new ArrayList<>();
        menuItemService.bulkUpdate(id,requests);
        return "";
    }
}
