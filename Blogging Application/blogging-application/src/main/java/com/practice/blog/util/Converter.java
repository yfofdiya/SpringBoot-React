package com.practice.blog.util;

import org.modelmapper.ModelMapper;

public class Converter<Dto, Entity> {

    private final ModelMapper modelMapper;
    private final Class<Dto> dtoType;
    private final Class<Entity> entityType;

    public Converter(Class<Dto> dtoType, Class<Entity> entityType) {
        this.dtoType = dtoType;
        this.entityType = entityType;
        this.modelMapper = new ModelMapper();
    }

    public Entity convertToEntity(Dto dto) {
        return modelMapper.map(dto, entityType);
    }

    public Dto convertToDto(Entity entity) {
        return modelMapper.map(entity, dtoType);
    }
}
