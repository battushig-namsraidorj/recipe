package com.profnaya.recipe.service;

import com.profnaya.recipe.domain.UnitOfMeasure;
import com.profnaya.recipe.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Set<UnitOfMeasure> getUomList() {
        Set<UnitOfMeasure> uomList = new HashSet<>();
        unitOfMeasureRepository.findAll().forEach(uomList::add);
        return uomList;
    }
}
