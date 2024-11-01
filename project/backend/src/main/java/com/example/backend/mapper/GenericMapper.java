package com.example.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
public class GenericMapper {
    private final ModelMapper modelMapper;

    public GenericMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <R> R mapType(Object source, Class<R> resultClass) {
        return modelMapper.map(source, resultClass);
    }

    /**
     * Maps an object for patch operations, ensuring only non-null fields are
     * updated.
     * This method temporarily enables skipNullEnabled during mapping to achieve
     * this behavior.
     */
    public <R> R mapTypeForPatch(Object source, Class<R> resultClass) {
        Configuration configuration = modelMapper.getConfiguration();
        boolean skipNullEnabled = configuration.isSkipNullEnabled();
        configuration.setSkipNullEnabled(true);

        R r = modelMapper.map(source, resultClass);

        configuration.setSkipNullEnabled(skipNullEnabled);

        return r;
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <S, T> Page<T> mapPage(Page<S> sourcePage, Class<T> targetClass) {
        List<T> content = mapList(sourcePage.getContent(), targetClass);
        return new PageImpl<>(content, sourcePage.getPageable(), sourcePage.getTotalElements());
    }

    // Add the mapStream method here
    public <S, T> Stream<T> mapStream(Stream<S> sourceStream, Class<T> targetClass) {
        return sourceStream.map(source -> modelMapper.map(source, targetClass));
    }
}
