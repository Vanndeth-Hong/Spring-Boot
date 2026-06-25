package org.example.ite3rdecommerceapplication.features.file;

import org.example.ite3rdecommerceapplication.features.file.dto.FileUploadResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    FileUploadResponse upload(MultipartFile file);

    FileUploadResponse findByName(String name);

    Page<FileUploadResponse> findAll(int pageNumber, int pageSize );

    List<FileUploadResponse> uploadMultiple(List<MultipartFile> files);

    void deleteByName(String name);

}
