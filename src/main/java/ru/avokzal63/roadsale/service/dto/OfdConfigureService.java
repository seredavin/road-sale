package ru.avokzal63.roadsale.service.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.avokzal63.roadsale.domain.OfdConfigure;
import ru.avokzal63.roadsale.repos.dto.OfdConfigureRepo;

import java.util.List;

@Service
public class OfdConfigureService {
    @Autowired
    private OfdConfigureRepo ofdConfigureRepo;

    public OfdConfigure getOfdConfigureById(int id) {
        return ofdConfigureRepo.findById(id);
    }

    public List<OfdConfigure> listConfigure() {
        return ofdConfigureRepo.findAll();
    }

    public boolean addConfigure(OfdConfigure ofdConfigure) {
        OfdConfigure ofdConfigureFromDb = ofdConfigureRepo.findByName(ofdConfigure.getName());
        if (ofdConfigureFromDb != null) {
            return false;
        }
        ofdConfigureRepo.save(ofdConfigure);
        return true;
    }

    public boolean updateConfigure(OfdConfigure ofdConfigure, String ofdConfigureId) {
        ofdConfigure.setId(Integer.parseInt(ofdConfigureId));
        ofdConfigureRepo.save(ofdConfigure);
        return true;
    }
}
