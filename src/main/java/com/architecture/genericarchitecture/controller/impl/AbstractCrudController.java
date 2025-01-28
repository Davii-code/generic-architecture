package com.architecture.genericarchitecture.controller.impl;


import com.architecture.genericarchitecture.controller.IAbstractCrudController;
import com.architecture.genericarchitecture.domain.GenericModel;
import com.architecture.genericarchitecture.mapper.GenericMapper;
import com.architecture.genericarchitecture.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

public class AbstractCrudController<DTORequest, DTOResponse, DTOList, MODEL extends GenericModel<TYPE_PK>, SERVICE extends IAbstractService<DTORequest, MODEL, TYPE_PK>, MAPPER extends GenericMapper<DTORequest, DTOResponse, DTOList, MODEL, TYPE_PK>, TYPE_PK>
        implements IAbstractCrudController<DTORequest, DTOResponse, DTOList, TYPE_PK> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected SERVICE service;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected MAPPER mapper;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOResponse> create(@RequestBody DTORequest dtoCreate){
        DTOResponse resultDTO = mapper.toDTO(service.createFromDTO(dtoCreate));
        return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<DTOResponse> update(@PathVariable TYPE_PK id, @RequestBody DTORequest dto) {
        DTOResponse modelSaved = mapper.toDTO(service.updateFromDTO(id, dto));
        return ResponseEntity.ok(modelSaved);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<DTOResponse> delete(@PathVariable TYPE_PK id){
        DTOResponse deleteDTO = mapper.toDTO(service.deleteById(id));
        return ResponseEntity.ok(deleteDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DTOList>> listAll(Pageable pageable){
        Page<DTOList> listDTO = service.listAll(pageable).map(obj -> mapper.toDTOList(obj));
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DTOResponse> getById(@PathVariable TYPE_PK id){
        DTOResponse dtoResult = mapper.toDTO(service.getById(id));
        return ResponseEntity.ok(dtoResult);
    }


}
