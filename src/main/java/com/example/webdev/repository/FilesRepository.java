package com.example.webdev.repository;

import com.example.webdev.model.FilesModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends CrudRepository<FilesModel, Long> {
}
