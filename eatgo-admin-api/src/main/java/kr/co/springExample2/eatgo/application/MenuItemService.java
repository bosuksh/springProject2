package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.MenuItem;
import kr.co.springExample2.eatgo.domain.MenuItemRepository;
import kr.co.springExample2.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public void bulkUpdate(Long id, List<MenuItem> menuItems) {
        //TODO: bulk update
        for(MenuItem menuItem: menuItems) {
            update(id, menuItem);
        }
    }

    private void update(Long id, MenuItem menuItem) {
        if(menuItem.isDestroy()) {
            //TODO: delete
            menuItemRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(id);
        menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getMenuItems(Long restaurantId) {
        //TODO:
        return menuItemRepository.findAllByRestaurantId(restaurantId);

    }
}
