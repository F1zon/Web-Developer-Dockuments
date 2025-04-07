package com.example.webdev.service;

import com.example.webdev.db.dao.financeContractDao;
import com.example.webdev.db.dto.financeContractDto;
import com.example.webdev.repository.financeContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class financeContractService {

    @Autowired
    private financeContractRepo repo;

    private financeContractDao convertToDao(financeContractDto dto) {
        return new financeContractDao(
                dto.getId(),
                dto.getDate_formation(),
                dto.getNumber(),dto.getItem(),
                dto.getResponsible_department(),
                dto.getAmount(),
                dto.getContract_id()
        );
    }

    private financeContractDto convertToDto(financeContractDao dao) {
        return new financeContractDto(
                dao.getId(),
                dao.getDate_formation(),
                dao.getNumber(),dao.getItem(),
                dao.getResponsible_department(),
                dao.getAmount(),
                dao.getContract_id()
        );
    }

    public void save(financeContractDto fc) {
        repo.save(convertToDao(fc));
    }


    public financeContractDto findById(int id) {
        return convertToDto(repo.findById(id).get());
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public void update(int id,financeContractDto dto) {
        repo.save(convertToDao(dto));
    }
}
