package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.Region;
import kr.co.springExample2.eatgo.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
    public List<Region> getRegions() {
        return regionRepository.findAll();
    }

}
