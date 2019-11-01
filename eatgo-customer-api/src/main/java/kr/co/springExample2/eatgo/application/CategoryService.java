package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.Category;
import kr.co.springExample2.eatgo.domain.CategoryRepository;
import kr.co.springExample2.eatgo.domain.Region;
import kr.co.springExample2.eatgo.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.awt.geom.AreaOp;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

}
