package com.example.schooloperationsystem.service.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Getter
@Setter
@ToString
public class CreateSchoolParams {

    private Integer number;
    private String namedAfter;
    private String address;
    private String photoUrl;

    public CreateSchoolParams(Integer number, String namedAfter, String address, String photoUrl) {
        Assert.notNull(number, "the number should not be null");
        this.number = number;
        Assert.notNull(namedAfter, "the named after should not be null");
        this.namedAfter = namedAfter;
        Assert.notNull(address, "the address should not be null");
        this.address = address;
        Assert.notNull(number, "the photo url should not be null");
        this.photoUrl = photoUrl;
    }
}
