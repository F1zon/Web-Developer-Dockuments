package com.example.webdev.service;

import com.example.webdev.db.dao.financeStagesDao;
import com.example.webdev.db.dto.financeStagesDto;
import com.example.webdev.repository.financeStagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class financeStagesService {

    @Autowired
    private financeStagesRepo repo;

    private financeStagesDao convertToDao(financeStagesDto dto) {
        return new financeStagesDao(
                dto.getId(),
                dto.getFinance_id(),
                dto.getStage_name(),
                dto.getStart_date_work(),
                dto.getCompletion_date(),
                dto.getAmount_stage(),
                dto.getAccount_number(),
                dto.getAccount_date(),
                dto.getPlanned_payment_date(),
                dto.getActual_payment_date(),
                dto.getAct_number(),
                dto.getAct_date(),
                dto.getAct_amount()
        );
    }

    private financeStagesDto convertToDto(financeStagesDao dao) {
        return  new financeStagesDto(
                dao.getId(),
                dao.getFinance_id(),
                dao.getStage_name(),
                dao.getStart_date_work(),
                dao.getCompletion_date(),
                dao.getAmount_stage(),
                dao.getAccount_number(),
                dao.getAccount_date(),
                dao.getPlanned_payment_date(),
                dao.getActual_payment_date(),
                dao.getAct_number(),
                dao.getAct_date(),
                dao.getAct_amount()
        );
    }

    public void save(financeStagesDto dto) {
        repo.save(convertToDao(dto));
    }

    public financeStagesDto findById(int id) {
        return convertToDto(repo.findById(id).get());
    }

    public  void delete(int id) {
        repo.deleteById(id);
    }

    public void update(int id, financeStagesDto dto) {
        repo.save(convertToDao(dto));
    }
}
