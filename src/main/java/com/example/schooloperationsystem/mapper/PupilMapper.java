package com.example.schooloperationsystem.mapper;
import com.example.schooloperationsystem.entity.Pupil;
import com.example.schooloperationsystem.rest.dto.response.PupilDetailsDto;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Gurgen Bayburdyan
 */
@Component
public class PupilMapper {
    public PupilDetailsDto map(Pupil pupil) {
        PupilDetailsDto detailsDto = new PupilDetailsDto();
        detailsDto.setFirstName(pupil.getFirstName());
        detailsDto.setLastName(pupil.getLastName());
        detailsDto.setDateOfBirth(pupil.getDateOfBirth());
        return detailsDto;
    }
    public List<PupilDetailsDto> mapList(List<Pupil> pupils) {
        List<PupilDetailsDto> detailsDtos = new ArrayList<>();
        for (Pupil pupil : pupils) {
            detailsDtos.add(map(pupil));
        }
        return detailsDtos;
    }
}