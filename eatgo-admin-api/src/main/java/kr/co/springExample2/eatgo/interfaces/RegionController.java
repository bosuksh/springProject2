package kr.co.springExample2.eatgo.interfaces;


import kr.co.springExample2.eatgo.application.RegionService;
import kr.co.springExample2.eatgo.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list() {
        return regionService.getRegions();
    }
    @PostMapping("/regions")
    public ResponseEntity<?> create(@RequestBody Region request) throws URISyntaxException {

        Region region = regionService.addRegion(request.getName());
        String url = "/regions/"+region.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
