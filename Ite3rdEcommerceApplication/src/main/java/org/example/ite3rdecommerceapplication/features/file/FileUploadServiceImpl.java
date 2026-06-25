package org.example.ite3rdecommerceapplication.features.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ite3rdecommerceapplication.features.file.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final FileUploadMapper fileUploadMapper;
    private final FileUploadRepository fileUploadRepository;

    @Value("${file.storage-location}")
    private String storageLocation;

    @Override
    public FileUploadResponse findByName(String name) {

        return fileUploadRepository.findByName(name)
                .map(fileUploadMapper::mapFileUploadToFileUploadResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File has not been found"));
    }

    @Override
    public Page<FileUploadResponse> findAll(int pageName, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageName, pageSize, sortById);

        Page<FileUpload> fileUploadResponse = fileUploadRepository.findAll(pageable);
        return fileUploadResponse.map(fileUploadMapper::mapFileUploadToFileUploadResponse);
    }

    @Override
    public List<FileUploadResponse> uploadMultiple(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            return Collections.emptyList();
        }

        // 1. Validate total file count (Max 10 files)
        if (files.size() > 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You can upload a maximum of 10 files at once.");
        }

        // 2. Validate individual file sizes (Max 10MB per file)
        // 10MB = 10 * 1024 * 1024 bytes = 10,485,760 bytes
        long maxSizeBytes = 10 * 1024 * 1024;

        boolean hasOversizedFile = files.stream()
                .anyMatch(file -> file.getSize() > maxSizeBytes);

        if (hasOversizedFile) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Each individual file size must not exceed 10MB.");
        }

        // 3. Process uploads if all validations pass
        return files.parallelStream()
                .filter(file -> !file.isEmpty())
                .map(this::saveFile)
                .collect(Collectors.toList());
    }


    @Override
    public FileUploadResponse upload(MultipartFile file) {
        return saveFile(file);
    }


    private FileUploadResponse saveFile(MultipartFile file) {
        if (file.getOriginalFilename() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name cannot be null");
        }

        String name = UUID.randomUUID().toString();
        String ext = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        Path path = Paths.get(storageLocation + name + "." + ext);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            log.error("Failed to save file physically", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "File has failed to upload");
        }

        FileUpload fileUpload = new FileUpload();
        fileUpload.setName(name);
        fileUpload.setExtension(ext);
        fileUpload.setCaption("ISTAD - Advanced IT Institute in Cambodia");
        fileUpload.setSize(file.getSize());
        fileUpload.setMediaType(file.getContentType());
        fileUploadRepository.save(fileUpload);

        return fileUploadMapper.mapFileUploadToFileUploadResponse(fileUpload);
    }

    @Override
    public void deleteByName(String name) {
        FileUpload fileUpload = fileUploadRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File has not been found."));

        fileUploadRepository.delete(fileUpload);

        Path path = Paths.get(storageLocation + fileUpload.getName() + "." + fileUpload.getExtension());
        try {
            boolean isExisted = Files.deleteIfExists(path);
            if (!isExisted) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File has not been deleted physically.");
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File has failed to delete.");
        }
    }
}
