package org.example.ite3rdecommerceapplication.features.file;

import org.example.ite3rdecommerceapplication.features.file.dto.FileUploadResponse;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public class FileUploadMapper {

    @Value("${file.base-uri}")
    private String baseUri;

    public FileUploadResponse mapFileUploadToFileUploadResponse(FileUpload fileUpload){
        return FileUploadResponse.builder()
                .name(fileUpload.getName())
                .extension(fileUpload.getExtension())
                .caption(fileUpload.getCaption())
                .size(fileUpload.getSize())
                .mediaType(fileUpload.getMediaType())
                .uri(baseUri + fileUpload.getName() + "." + fileUpload.getExtension())
                .build();
    }
}
