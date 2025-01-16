package com.example.schooloperationsystem.service.impl;

import com.example.schooloperationsystem.entity.PupilInClass;
import com.example.schooloperationsystem.repository.PupilInClassRepository;
import com.example.schooloperationsystem.service.PupilInClassService;
import com.example.schooloperationsystem.service.params.CreatePupilInClassParams;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
class PupilInClassServiceImpl implements PupilInClassService {
    private final PupilInClassRepository repository;

    @Override
    public List<PupilInClass> getPupilInClasses() {
        return repository.findAll();
    }

    @Override
    public PupilInClass addPupil(CreatePupilInClassParams params) {
        PupilInClass pupilInClass = new PupilInClass();
        pupilInClass.setPupil(params.getPupil());
        pupilInClass.setSchoolClass(params.getSchoolClass());
        return repository.save(pupilInClass);
    }

    @Override
    @Transactional
    public PupilInClass deletePupilById(Long id) {
        Optional<PupilInClass> pupilInClassOptional = repository.findById(id);
        if (pupilInClassOptional.isPresent()) {
            PupilInClass pupilInClass = pupilInClassOptional.get();
            repository.delete(pupilInClass);
            return pupilInClass;
        }
        return null;
    }

    @Override
    @Transactional
    public List<PupilInClass> getPupilsBySchoolClassId(Long schoolClassId) {
        return repository.getBySchoolClassId(schoolClassId);
    }
}
