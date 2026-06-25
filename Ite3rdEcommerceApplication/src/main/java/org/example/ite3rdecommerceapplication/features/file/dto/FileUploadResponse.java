package org.example.ite3rdecommerceapplication.features.file.dto;

import lombok.Builder;

@Builder
public record FileUploadResponse(
        String name,
        String caption,
        Long size,
        String extension,
        String mediaType,
        //http://localhost:8080/file/
        String uri
) {
}
